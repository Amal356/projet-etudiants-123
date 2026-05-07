# language: fr
Fonctionnalité: Calcul de l'âge d'un étudiant
  En tant que système de gestion des étudiants
  Je veux calculer automatiquement l'âge d'un étudiant
  Afin d'afficher son âge actuel basé sur sa date de naissance

  Scénario: Étudiant né il y a 22 ans
    Étant donné un étudiant avec la date de naissance "2002-04-07"
    Quand on calcule son âge
    Alors l'âge retourné doit être 23

  Scénario: Étudiant né il y a 20 ans
    Étant donné un étudiant avec la date de naissance "2004-01-15"
    Quand on calcule son âge
    Alors l'âge retourné doit être 21

  Scénario: Étudiant né il y a 1 an
    Étant donné un étudiant avec la date de naissance "2024-06-20"
    Quand on calcule son âge
    Alors l'âge retourné doit être 0

  Scénario: Étudiant né dans l'année courante
    Étant donné un étudiant avec la date de naissance "2025-01-01"
    Quand on calcule son âge
    Alors l'âge retourné doit être 0

  Scénario: Cas limite - Étudiant né un jour bissextile
    Étant donné un étudiant avec la date de naissance "2000-02-29"
    Quand on calcule son âge
    Alors l'âge retourné doit être 24

  Plan du Scénario: Calcul de l'âge pour différentes dates de naissance
    Étant donné un étudiant avec la date de naissance "<dateNaissance>"
    Quand on calcule son âge
    Alors l'âge retourné doit être <ageAttendu>

    Exemples:
      | dateNaissance | ageAttendu |
      | 2004-01-15    | 21         |
      | 2005-06-20    | 19         |
      | 2025-01-01    | 0          |
      | 2000-02-29    | 24         |
      | 1995-12-31    | 29         |
      | 2010-03-10    | 14         |
