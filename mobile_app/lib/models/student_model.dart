class StudentModel {
  final int id;
  final String cin;
  final String nom;
  final String dateNaissance;

  StudentModel({
    required this.id,
    required this.cin,
    required this.nom,
    required this.dateNaissance,
  });

  // Deserialize from JSON
  factory StudentModel.fromJson(Map<String, dynamic> json) {
    return StudentModel(
      id: json['id'] as int,
      cin: json['cin'] as String,
      nom: json['nom'] as String,
      dateNaissance: json['dateNaissance'] as String? ?? '',
    );
  }

  // Serialize to JSON
  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'cin': cin,
      'nom': nom,
      'dateNaissance': dateNaissance,
    };
  }
}
