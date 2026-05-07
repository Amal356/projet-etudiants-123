/// Environment configuration for API endpoints across different platforms.
///
/// This class provides platform-specific base URLs for connecting to the
/// Spring Boot API from different environments:
/// - Android emulator: Uses 10.0.2.2 to access host machine's localhost
/// - iOS simulator: Can use localhost directly
/// - Physical devices: Requires the actual local network IP address
class EnvironmentConfig {
  /// Base URL for Android emulator.
  ///
  /// Android emulators use 10.0.2.2 as a special alias to access the
  /// host machine's localhost (127.0.0.1). Use this when running the
  /// app on an Android emulator with the API running on your development machine.
  static const String androidEmulatorBaseUrl = 'http://10.0.2.2:8080';

  /// Base URL for iOS simulator.
  ///
  /// iOS simulators can access the host machine's localhost directly.
  /// Use this when running the app on an iOS simulator with the API
  /// running on your development machine.
  static const String iosSimulatorBaseUrl = 'http://localhost:8080';

  /// Base URL for physical devices.
  ///
  /// Physical devices (both Android and iOS) need to connect via the
  /// local network IP address of your development machine.
  /// Replace 'YOUR_LOCAL_IP' with your actual IP address (e.g., 192.168.1.100).
  ///
  /// To find your local IP:
  /// - Windows: Run 'ipconfig' in Command Prompt, look for IPv4 Address
  /// - macOS/Linux: Run 'ifconfig' or 'ip addr' in Terminal
  /// - Ensure your device and development machine are on the same network
  static const String physicalDeviceBaseUrl = 'http://YOUR_LOCAL_IP:8080';

  /// Default base URL used by the application.
  ///
  /// Configure this based on your current development environment:
  /// - Set to [androidEmulatorBaseUrl] when testing on Android emulator
  /// - Set to [iosSimulatorBaseUrl] when testing on iOS simulator or web browser
  /// - Set to [physicalDeviceBaseUrl] when testing on physical devices
  ///
  /// Default is set to iOS simulator/web for convenience.
  static const String baseUrl = iosSimulatorBaseUrl;

  /// API endpoint path for fetching students.
  ///
  /// This path is appended to the base URL to form the complete API endpoint.
  static const String studentsEndpoint = '/api/etudiants';

  /// Complete URL for the students API endpoint.
  ///
  /// This getter combines the [baseUrl] and [studentsEndpoint] to provide
  /// the full URL for fetching student data from the Spring Boot API.
  ///
  /// Example: 'http://10.0.2.2:8080/api/etudiants'
  static String get studentsUrl => '$baseUrl$studentsEndpoint';
}
