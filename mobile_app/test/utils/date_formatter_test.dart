import 'package:flutter_test/flutter_test.dart';
import 'package:mobile_app/utils/date_formatter.dart';

void main() {
  group('formatDate', () {
    test('formats valid ISO 8601 date to French format', () {
      expect(formatDate('2000-05-15'), equals('15/05/2000'));
      expect(formatDate('1999-08-22'), equals('22/08/1999'));
      expect(formatDate('2024-01-01'), equals('01/01/2024'));
    });

    test('returns "Date invalide" for empty string', () {
      expect(formatDate(''), equals('Date invalide'));
    });

    test('returns "Date invalide" for invalid date strings', () {
      expect(formatDate('invalid'), equals('Date invalide'));
      expect(formatDate('not-a-date'), equals('Date invalide'));
      expect(formatDate('abc-def-ghij'), equals('Date invalide'));
      expect(formatDate('hello world'), equals('Date invalide'));
      expect(formatDate('2024/13/45'), equals('Date invalide'));
      expect(formatDate('random text'), equals('Date invalide'));
    });

    test('handles edge case dates correctly', () {
      expect(formatDate('2000-01-01'), equals('01/01/2000'));
      expect(formatDate('2000-12-31'), equals('31/12/2000'));
      expect(formatDate('2024-02-29'), equals('29/02/2024')); // Leap year
    });
  });
}
