# language: fr

Fonctionnalité: Gestion des notes des étudiants
  En tant qu'administrateur
  Je veux gérer les notes des étudiants
  Afin de suivre leurs performances académiques

  Scénario: Créer une nouvelle note
    Étant donné que le service de notation est disponible
    Quand je crée une note avec studentId "1", matiere "Mathématiques" et valeur "15.5"
    Alors la note est créée avec succès
    Et la note contient la matiere "Mathématiques"
    Et la note contient la valeur "15.5"

  Scénario: Récupérer toutes les notes
    Étant donné que le service de notation est disponible
    Et qu'il existe des notes dans la base de données
    Quand je récupère toutes les notes
    Alors je reçois une liste de notes

  Scénario: Récupérer une note par ID
    Étant donné que le service de notation est disponible
    Et qu'une note existe avec l'ID "1"
    Quand je récupère la note avec l'ID "1"
    Alors je reçois la note avec l'ID "1"

  Scénario: Mettre à jour une note existante
    Étant donné que le service de notation est disponible
    Et qu'une note existe avec l'ID "1"
    Quand je mets à jour la note avec l'ID "1" avec la valeur "18.0"
    Alors la note est mise à jour avec succès
    Et la note contient la valeur "18.0"

  Scénario: Supprimer une note
    Étant donné que le service de notation est disponible
    Et qu'une note existe avec l'ID "1"
    Quand je supprime la note avec l'ID "1"
    Alors la note est supprimée avec succès

  Scénario: Validation - valeur de note invalide
    Étant donné que le service de notation est disponible
    Quand je crée une note avec une valeur invalide "25.0"
    Alors je reçois une erreur de validation
