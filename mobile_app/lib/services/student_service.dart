import 'dart:async';
import 'dart:convert';
import 'dart:io';
import 'package:http/http.dart' as http;
import '../config/environment_config.dart';
import '../models/student_model.dart';

/// Service class responsible for all API communication related to students.
///
/// This service handles fetching student data from the Spring Boot API
/// with comprehensive error handling and timeout management.
class StudentService {
  /// HTTP client for making network requests.
  /// Can be injected for testing purposes.
  final http.Client client;

  /// Timeout duration for API requests (10 seconds).
  static const Duration timeout = Duration(seconds: 10);

  /// Creates a StudentService instance.
  ///
  /// [client] is optional and defaults to a new http.Client instance.
  /// This allows for dependency injection, particularly useful for testing
  /// with mocked HTTP clients.
  StudentService({http.Client? client}) : client = client ?? http.Client();

  /// Fetches the list of students from the Spring Boot API.
  ///
  /// Returns a [Future] that completes with a [List<StudentModel>] on success.
  /// Throws an [Exception] with a descriptive French error message on failure.
  ///
  /// Error handling:
  /// - Timeout (10s): "Délai d'attente dépassé. Vérifiez votre connexion."
  /// - Connection error: "Impossible de se connecter au serveur. Vérifiez que l'API est démarrée."
  /// - Server error (5xx): "Erreur du serveur. Veuillez réessayer plus tard."
  /// - Client error (4xx): "Erreur de requête. Veuillez contacter le support."
  /// - JSON parsing error: "Erreur de parsing JSON: [details]"
  /// - Other errors: "Erreur réseau: [details]"
  Future<List<StudentModel>> fetchStudents() async {
    try {
      final response = await client
          .get(
            Uri.parse(EnvironmentConfig.studentsUrl),
            headers: {'Content-Type': 'application/json'},
          )
          .timeout(timeout);

      if (response.statusCode == 200) {
        final List<dynamic> jsonList = json.decode(response.body);
        return jsonList.map((json) => StudentModel.fromJson(json)).toList();
      } else if (response.statusCode >= 500) {
        throw Exception('Erreur du serveur. Veuillez réessayer plus tard.');
      } else if (response.statusCode >= 400) {
        throw Exception('Erreur de requête. Veuillez contacter le support.');
      } else {
        throw Exception('Erreur inattendue: ${response.statusCode}');
      }
    } on TimeoutException {
      throw Exception('Délai d\'attente dépassé. Vérifiez votre connexion.');
    } on SocketException {
      throw Exception(
          'Impossible de se connecter au serveur. Vérifiez que l\'API est démarrée.');
    } on FormatException catch (e) {
      throw Exception('Erreur de parsing JSON: ${e.message}');
    } catch (e) {
      throw Exception('Erreur réseau: $e');
    }
  }
}
