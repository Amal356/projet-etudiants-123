import 'package:intl/intl.dart';

/// Formats an ISO 8601 date string (YYYY-MM-DD) to French format (DD/MM/YYYY).
///
/// Returns "Date invalide" if the date string is empty or cannot be parsed.
///
/// Example:
/// ```dart
/// formatDate('2000-05-15'); // Returns '15/05/2000'
/// formatDate(''); // Returns 'Date invalide'
/// formatDate('invalid'); // Returns 'Date invalide'
/// ```
String formatDate(String isoDate) {
  try {
    if (isoDate.isEmpty) return 'Date invalide';
    
    // Parse ISO 8601 date string (YYYY-MM-DD)
    final date = DateTime.parse(isoDate);
    
    // Format to French format (DD/MM/YYYY)
    final formatter = DateFormat('dd/MM/yyyy');
    return formatter.format(date);
  } catch (e) {
    // Return error message for any parsing errors
    return 'Date invalide';
  }
}
