# Student Management Mobile App

A cross-platform Flutter mobile application for managing and viewing student information. This app displays a list of students fetched from a Spring Boot API with comprehensive state management, error handling, and a modern Material Design 3 interface.

## Overview

This Flutter application replaces the existing React Native app and provides:

- **Cross-platform support**: Runs on both Android (API 21+) and iOS (12.0+)
- **Clean architecture**: Separation of concerns with distinct layers for models, services, screens, and configuration
- **Robust state management**: Explicit handling of loading, error, empty, and data states
- **Modern UI**: Material Design 3 components with French localization
- **Comprehensive error handling**: User-friendly error messages with retry functionality

## Prerequisites

Before you begin, ensure you have the following installed:

- **Flutter SDK 3.0.0 or higher**
  - Download from: https://flutter.dev/docs/get-started/install
  - Verify installation: `flutter --version`

- **Android Studio** (for Android development)
  - Download from: https://developer.android.com/studio
  - Install Android SDK and Android Emulator
  - Minimum Android API level: 21 (Android 5.0)

- **Xcode** (for iOS development, macOS only)
  - Download from Mac App Store
  - Install iOS Simulator
  - Minimum iOS version: 12.0

- **Spring Boot API** (backend)
  - The backend API must be running at `http://localhost:8080`
  - Ensure the `/api/etudiants` endpoint is accessible

## Installation

1. **Clone the repository** (if not already done):
   ```bash
   git clone <repository-url>
   cd mobile_app
   ```

2. **Install Flutter dependencies**:
   ```bash
   flutter pub get
   ```

3. **Verify Flutter installation**:
   ```bash
   flutter doctor
   ```
   
   This command checks your environment and displays a report of the status of your Flutter installation. Fix any issues reported before proceeding.

## Configuration

### API URL Configuration

The app needs to connect to the Spring Boot API running on your local machine. The API URL configuration varies depending on your development environment:

#### 1. Android Emulator

Android emulators use a special IP address to access the host machine's localhost:

- **API URL**: `http://10.0.2.2:8080`
- **Configuration**: This is the default setting in `lib/config/environment_config.dart`
- **No changes needed** if using Android emulator

#### 2. iOS Simulator

iOS simulators can access the host machine's localhost directly:

- **API URL**: `http://localhost:8080`
- **Configuration**: Update `baseUrl` in `lib/config/environment_config.dart`:
  ```dart
  static const String baseUrl = iosSimulatorBaseUrl;
  ```

#### 3. Physical Device

Physical devices need your computer's local network IP address:

- **Find your local IP**:
  - **macOS/Linux**: Run `ifconfig` and look for `inet` under your network interface (usually `en0` or `wlan0`)
  - **Windows**: Run `ipconfig` and look for `IPv4 Address`
  - Example: `192.168.1.100`

- **API URL**: `http://YOUR_LOCAL_IP:8080`
- **Configuration**: Update `lib/config/environment_config.dart`:
  ```dart
  static const String physicalDeviceBaseUrl = 'http://192.168.1.100:8080';
  static const String baseUrl = physicalDeviceBaseUrl;
  ```

- **Important**: Ensure your device and computer are on the same Wi-Fi network

### Environment Configuration File

Edit `lib/config/environment_config.dart` to set the appropriate base URL:

```dart
class EnvironmentConfig {
  // Choose the appropriate base URL for your environment
  static const String baseUrl = androidEmulatorBaseUrl; // Default
  // static const String baseUrl = iosSimulatorBaseUrl;
  // static const String baseUrl = physicalDeviceBaseUrl;
  
  // ... rest of the configuration
}
```

## Running the Application

### Start the Backend API

Before running the mobile app, ensure the Spring Boot API is running:

```bash
# Navigate to the Spring Boot API directory
cd ../spring-boot-api

# Run the API (using Maven)
./mvnw spring-boot:run

# Or using Docker Compose (from project root)
cd ..
docker-compose up
```

Verify the API is accessible at `http://localhost:8080/api/etudiants`

### Run the Flutter App

1. **List available devices**:
   ```bash
   flutter devices
   ```

2. **Run on a specific device**:
   ```bash
   # Run on Android emulator
   flutter run -d <android-device-id>
   
   # Run on iOS simulator
   flutter run -d <ios-device-id>
   
   # Run on the first available device
   flutter run
   ```

3. **Run in debug mode** (default):
   ```bash
   flutter run
   ```

4. **Run in release mode** (optimized performance):
   ```bash
   flutter run --release
   ```

### Hot Reload

While the app is running in debug mode, you can use hot reload to see changes instantly:

- Press `r` in the terminal to hot reload
- Press `R` in the terminal to hot restart
- Press `q` to quit

## Testing

### Run All Tests

```bash
flutter test
```

### Run Tests with Coverage

```bash
flutter test --coverage
```

Coverage reports are generated in the `coverage/` directory.

### Run Static Analysis

```bash
flutter analyze
```

This checks for code quality issues, potential bugs, and style violations.

### Test Types

The project includes:

- **Unit tests**: Test individual functions and classes (services, models)
- **Widget tests**: Test UI components and user interactions
- **Property-based tests**: Validate universal correctness properties
- **Integration tests**: Test end-to-end functionality

## Building for Production

### Build for Android

1. **Build APK** (for testing):
   ```bash
   flutter build apk
   ```
   
   Output: `build/app/outputs/flutter-apk/app-release.apk`

2. **Build App Bundle** (for Google Play Store):
   ```bash
   flutter build appbundle
   ```
   
   Output: `build/app/outputs/bundle/release/app-release.aab`

3. **Build for specific architecture**:
   ```bash
   # ARM 64-bit (most modern devices)
   flutter build apk --target-platform android-arm64
   
   # Split APKs by architecture (smaller file sizes)
   flutter build apk --split-per-abi
   ```

### Build for iOS

1. **Build for iOS** (requires macOS and Xcode):
   ```bash
   flutter build ios
   ```

2. **Build IPA for distribution**:
   ```bash
   flutter build ipa
   ```
   
   Output: `build/ios/ipa/mobile_app.ipa`

3. **Open in Xcode** (for advanced configuration):
   ```bash
   open ios/Runner.xcworkspace
   ```

## Troubleshooting

### Common Issues

#### 1. API Connection Failed

**Error**: "Impossible de se connecter au serveur. Vérifiez que l'API est démarrée."

**Solutions**:
- Verify the Spring Boot API is running at `http://localhost:8080`
- Check the API URL configuration in `lib/config/environment_config.dart`
- For Android emulator, ensure you're using `http://10.0.2.2:8080`
- For iOS simulator, ensure you're using `http://localhost:8080`
- For physical devices, ensure you're using your local network IP and both devices are on the same network
- Test the API endpoint in a browser: `http://localhost:8080/api/etudiants`

#### 2. Android Emulator Not Starting

**Solutions**:
- Open Android Studio → AVD Manager → Create or start a virtual device
- Ensure virtualization is enabled in BIOS (VT-x for Intel, AMD-V for AMD)
- Check available disk space (emulators require several GB)
- Run `flutter doctor` to check for Android toolchain issues

#### 3. iOS Simulator Not Found

**Solutions**:
- Open Xcode → Preferences → Components → Download a simulator
- Run `sudo xcode-select --switch /Applications/Xcode.app/Contents/Developer`
- Run `flutter doctor` to check for iOS toolchain issues
- Ensure Xcode Command Line Tools are installed: `xcode-select --install`

#### 4. Dependencies Not Installing

**Error**: "pub get failed"

**Solutions**:
- Clear Flutter cache: `flutter clean`
- Delete `pubspec.lock` and run `flutter pub get` again
- Check internet connection
- Try `flutter pub cache repair`

#### 5. Build Failures

**Solutions**:
- Run `flutter clean` to remove build artifacts
- Delete `build/` directory
- Run `flutter pub get` to reinstall dependencies
- For Android: Check `android/app/build.gradle` for correct `minSdkVersion` (21+)
- For iOS: Run `cd ios && pod install` to update CocoaPods dependencies

#### 6. Timeout Errors

**Error**: "Délai d'attente dépassé. Vérifiez votre connexion."

**Solutions**:
- Check network connectivity
- Verify the API is responding (test in browser)
- Increase timeout in `lib/services/student_service.dart` if needed
- Check firewall settings that might block connections

#### 7. Date Formatting Issues

**Error**: "Date invalide" displayed for dates

**Solutions**:
- Verify the API returns dates in ISO 8601 format (YYYY-MM-DD)
- Check the `dateNaissance` field in the API response
- Ensure the `intl` package is properly installed: `flutter pub get`

## Project Structure

```
mobile_app/
├── lib/
│   ├── config/
│   │   └── environment_config.dart    # API URL configuration
│   ├── models/
│   │   └── student_model.dart         # Student data model
│   ├── services/
│   │   └── student_service.dart       # API communication
│   ├── screens/
│   │   └── student_list_screen.dart   # Main UI screen
│   ├── utils/
│   │   └── date_formatter.dart        # Date formatting utility
│   └── main.dart                      # Application entry point
├── test/
│   ├── models/
│   ├── services/
│   ├── screens/
│   └── utils/
├── android/                           # Android-specific files
├── ios/                               # iOS-specific files
├── pubspec.yaml                       # Dependencies configuration
└── README.md                          # This file
```

## Dependencies

- **flutter**: Flutter SDK
- **http** (^1.2.0): HTTP client for API communication
- **intl** (^0.19.0): Internationalization and date formatting
- **cupertino_icons** (^1.0.8): iOS-style icons

### Dev Dependencies

- **flutter_test**: Testing framework
- **flutter_lints** (^6.0.0): Linting rules for code quality

## Features

- ✅ Display list of students from API
- ✅ Loading state with progress indicator
- ✅ Empty state when no students found
- ✅ Error state with user-friendly messages
- ✅ Retry functionality for failed requests
- ✅ French date formatting (DD/MM/YYYY)
- ✅ Material Design 3 UI components
- ✅ Responsive layout for different screen sizes
- ✅ Comprehensive error handling
- ✅ Cross-platform support (Android & iOS)

## Support

For issues or questions:

1. Check the troubleshooting section above
2. Run `flutter doctor` to diagnose environment issues
3. Check the API is running and accessible
4. Review the configuration in `lib/config/environment_config.dart`

## License

[Add your license information here]
