import 'package:flutter/material.dart';
import '../models/student_model.dart';
import '../services/student_service.dart';
import '../utils/date_formatter.dart';

/// Enum representing the different states of the Student List Screen.
enum ScreenState { loading, data, empty, error }

/// Screen that displays a list of students fetched from the API.
///
/// This screen manages four distinct states:
/// - Loading: Initial state while fetching data
/// - Data: Successfully loaded student list
/// - Empty: API returned an empty list
/// - Error: Network or API error occurred
class StudentListScreen extends StatefulWidget {
  const StudentListScreen({super.key});

  @override
  State<StudentListScreen> createState() => _StudentListScreenState();
}

class _StudentListScreenState extends State<StudentListScreen> {
  /// Current state of the screen
  ScreenState _state = ScreenState.loading;

  /// List of students fetched from the API
  List<StudentModel> _students = [];

  /// Error message to display in error state
  String _errorMessage = '';

  /// Service instance for API communication
  final StudentService _service = StudentService();

  @override
  void initState() {
    super.initState();
    _fetchStudents();
  }

  /// Fetches students from the API and updates the screen state accordingly.
  ///
  /// State transitions:
  /// - Always starts with loading state
  /// - On success with data: transitions to data state
  /// - On success with empty list: transitions to empty state
  /// - On error: transitions to error state with error message
  Future<void> _fetchStudents() async {
    setState(() {
      _state = ScreenState.loading;
    });

    try {
      final students = await _service.fetchStudents();
      setState(() {
        if (students.isEmpty) {
          _state = ScreenState.empty;
        } else {
          _state = ScreenState.data;
          _students = students;
        }
      });
    } catch (e) {
      setState(() {
        _state = ScreenState.error;
        // Remove "Exception: " prefix for cleaner error messages
        _errorMessage = e.toString().replaceFirst('Exception: ', '');
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Liste des Étudiants'),
      ),
      body: _buildBody(),
    );
  }

  /// Builds the body content based on the current screen state.
  Widget _buildBody() {
    switch (_state) {
      case ScreenState.loading:
        return _buildLoadingState();
      case ScreenState.empty:
        return _buildEmptyState();
      case ScreenState.error:
        return _buildErrorState();
      case ScreenState.data:
        return _buildDataState();
    }
  }

  /// Builds the loading state UI with a centered progress indicator.
  Widget _buildLoadingState() {
    return const Center(
      child: CircularProgressIndicator(),
    );
  }

  /// Builds the empty state UI with an icon and message.
  Widget _buildEmptyState() {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Icon(
            Icons.inbox_outlined,
            size: 64,
            color: Theme.of(context).colorScheme.secondary,
          ),
          const SizedBox(height: 16),
          Text(
            'Aucun étudiant trouvé',
            style: Theme.of(context).textTheme.titleLarge,
          ),
        ],
      ),
    );
  }

  /// Builds the error state UI with error message and retry button.
  Widget _buildErrorState() {
    return Center(
      child: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(
              Icons.error_outline,
              size: 64,
              color: Theme.of(context).colorScheme.error,
            ),
            const SizedBox(height: 16),
            Text(
              _errorMessage,
              style: Theme.of(context).textTheme.bodyLarge,
              textAlign: TextAlign.center,
            ),
            const SizedBox(height: 24),
            ElevatedButton(
              onPressed: _fetchStudents,
              child: const Text('Réessayer'),
            ),
          ],
        ),
      ),
    );
  }

  /// Builds the data state UI with a scrollable list of student cards.
  Widget _buildDataState() {
    return ListView.builder(
      itemCount: _students.length,
      itemBuilder: (context, index) {
        return _buildStudentCard(_students[index]);
      },
    );
  }

  /// Builds a card widget for a single student.
  ///
  /// The card displays:
  /// - Student name (bold, 18sp)
  /// - CIN with label
  /// - Formatted date of birth with label
  Widget _buildStudentCard(StudentModel student) {
    return Card(
      margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
      child: ListTile(
        title: Text(
          student.nom,
          style: const TextStyle(
            fontSize: 18,
            fontWeight: FontWeight.bold,
          ),
        ),
        subtitle: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const SizedBox(height: 4),
            Text(
              'CIN: ${student.cin}',
              style: const TextStyle(fontSize: 14),
            ),
            Text(
              'Date de naissance: ${formatDate(student.dateNaissance)}',
              style: const TextStyle(fontSize: 14),
            ),
          ],
        ),
      ),
    );
  }
}
