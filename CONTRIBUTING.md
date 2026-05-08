# 🤝 Guide de Contribution

## Workflow Git + Jira

Ce projet utilise une intégration GitHub ↔ Jira pour assurer la traçabilité complète du code.

---

## 📚 Contexte du Projet

### Historique Jira

Ce projet utilise Jira depuis la **Partie 1**:

- **Sprint 1** (Partie 1): API REST de base - 6 stories (PROJ-1 à PROJ-6)
- **Sprint 2** (Partie 2): Enrichissement - 12 stories (PROJ-7 à PROJ-18)
- **Sprint 3** (Partie 3): Architecture Micro Services - 9 stories (PROJ-19 à PROJ-27)
- **Sprint 4** (Partie 4): Tests et Qualité - 9 stories (PROJ-28 à PROJ-36)

**Total**: 36 User Stories réparties sur 4 Sprints

📖 **Voir**: [GUIDE_JIRA_COMPLET.md](./GUIDE_JIRA_COMPLET.md) pour toutes les User Stories

---

## 📋 Avant de Commencer

### Prérequis
- Accès au projet Jira "Gestion Étudiants"
- Accès au dépôt GitHub
- Git configuré localement
- GitHub CLI installé (optionnel mais recommandé)

### Conventions
- **Clé Jira**: `PROJ` (ex: PROJ-1, PROJ-2, etc.)
- **Branches actuelles**: `main`, `version-2`, `version-3`, `version-4`
- **Format des branches**: `feature/PROJ-XX-description`
- **Format des commits**: `PROJ-XX : description`

---

## 🔄 Workflow Standard

### 1. Prendre une Story dans Jira

1. Allez sur le board Jira du Sprint 4
2. Choisissez une story dans la colonne "To Do"
3. Assignez-vous la story
4. Passez le statut à **"In Progress"**
5. Notez la clé Jira (ex: PROJ-24)

### 2. Créer une Branche

```bash
# Mettre à jour la branche principale
git checkout version-4
git pull origin version-4

# Créer votre branche de travail
git checkout -b feature/PROJ-29-tests-unitaires
```

**Types de branches**:
- `feature/` - Nouvelles fonctionnalités
- `bugfix/` - Corrections de bugs
- `test/` - Ajout de tests
- `docs/` - Documentation

### 3. Développer

- Écrivez votre code
- Écrivez les tests
- Vérifiez que tout fonctionne localement
- Suivez les bonnes pratiques du projet

### 4. Commiter Régulièrement

```bash
# Ajouter les fichiers modifiés
git add .

# Commiter avec la convention
git commit -m "PROJ-29 : ajout des tests unitaires StudentService"
```

**Règles des commits**:
- ✅ Commencer par la clé Jira (ex: `PROJ-29`)
- ✅ Utiliser `:` après la clé
- ✅ Description claire et concise
- ✅ Verbe à l'infinitif ou au passé composé
- ❌ Pas de point final

**Exemples de bons commits**:
```bash
git commit -m "PROJ-1 : création entité Etudiant"
git commit -m "PROJ-8 : ajout méthode age()"
git commit -m "PROJ-21 : création grading-service"
git commit -m "PROJ-29 : tests unitaires JUnit Mockito"
```

### 5. Pousser et Créer une Pull Request

```bash
# Pousser la branche
git push -u origin feature/PROJ-29-tests-unitaires

# Créer une PR avec GitHub CLI
gh pr create \
  --title "PROJ-29 : Tests unitaires avec JUnit et Mockito" \
  --body "Implémentation des tests unitaires pour StudentService" \
  --base version-4
```

**Ou via l'interface web**:
1. Allez sur GitHub
2. Cliquez sur "Pull requests"
3. Cliquez sur "New pull request"
4. Sélectionnez:
   - Base: `version-4`
   - Compare: `feature/PROJ-24-tests-unitaires`
5. Remplissez le titre: `PROJ-24 : Tests unitaires avec Mockito`
6. Remplissez la description (voir template ci-dessous)
7. Créez la PR

### 6. Template de Pull Request

```markdown
## Description
Brève description des changements apportés.

## Changements
- Liste des principaux changements
- Fichiers ajoutés/modifiés
- Fonctionnalités implémentées

## Tests
- [ ] Tests unitaires passent
- [ ] Tests d'intégration passent
- [ ] Build Maven réussit
- [ ] Aucune régression

## Lié à
- Jira: PROJ-XX
- Sprint: Sprint 4

## Checklist
- [ ] Code testé localement
- [ ] Documentation mise à jour
- [ ] Pas de conflits avec version-4
- [ ] Conventions de code respectées
```

### 7. Review et Merge

1. **Attendez la review** d'un autre développeur
2. **Répondez aux commentaires** si nécessaire
3. **Corrigez** les problèmes identifiés
4. **Mergez** une fois approuvé

### 8. Mettre à Jour Jira

1. Retournez sur Jira
2. Ouvrez votre ticket (ex: PROJ-24)
3. Vérifiez que la PR apparaît dans la section "Development"
4. Passez le statut à **"Done"**
5. Ajoutez un commentaire avec le lien de la PR

---

## 📝 Conventions de Nommage

### Branches

**Format**: `<type>/<clé-jira>-<description>`

**Exemples**:
```
feature/PROJ-24-tests-unitaires
feature/PROJ-25-tests-integration
feature/PROJ-26-tests-e2e-cypress
feature/PROJ-29-auth-service
bugfix/PROJ-32-fix-validation-email
docs/PROJ-33-update-readme
```

### Commits

**Format**: `<clé-jira> : <description>`

**Exemples**:
```
PROJ-24 : ajout des tests unitaires StudentService
PROJ-25 : configuration Testcontainers PostgreSQL
PROJ-26 : création des tests E2E avec Cypress
PROJ-29 : implémentation de l'endpoint /register
```

### Pull Requests

**Format du titre**: `<clé-jira> : <titre>`

**Exemples**:
```
PROJ-24 : Tests unitaires avec Mockito
PROJ-25 : Tests d'intégration avec Testcontainers
PROJ-26 : Tests E2E avec Cypress
PROJ-29 : Microservice d'authentification
```

---

## 🔍 Vérifier l'Intégration

### Dans Jira

1. Ouvrez votre ticket
2. Regardez la section **"Development"** (à droite)
3. Vous devriez voir:
   - 🌿 Branches créées
   - 📝 Commits associés
   - 🔀 Pull Requests

### Dans GitHub

1. Ouvrez votre Pull Request
2. Vous devriez voir un lien vers Jira dans la description
3. Les commits doivent tous commencer par la clé Jira

---

## ⚠️ Erreurs Courantes

### ❌ Commit sans clé Jira

**Mauvais**:
```bash
git commit -m "ajout des tests"
```

**Bon**:
```bash
git commit -m "PROJ-24 : ajout des tests unitaires"
```

### ❌ Branche sans clé Jira

**Mauvais**:
```bash
git checkout -b tests-unitaires
```

**Bon**:
```bash
git checkout -b feature/PROJ-24-tests-unitaires
```

### ❌ PR sans clé Jira

**Mauvais**:
```
Titre: Tests unitaires
```

**Bon**:
```
Titre: PROJ-24 : Tests unitaires avec Mockito
```

---

## 🛠️ Commandes Utiles

### Git

```bash
# Voir l'état des fichiers
git status

# Voir l'historique des commits
git log --oneline

# Voir les branches
git branch -a

# Mettre à jour depuis version-4
git checkout version-4
git pull origin version-4
git checkout feature/PROJ-24-tests-unitaires
git merge version-4

# Annuler le dernier commit (garder les changements)
git reset --soft HEAD~1

# Modifier le dernier commit
git commit --amend -m "PROJ-24 : nouveau message"
```

### GitHub CLI

```bash
# Créer une PR
gh pr create --title "PROJ-24 : Tests unitaires"

# Voir les PRs
gh pr list

# Voir le statut d'une PR
gh pr status

# Merger une PR
gh pr merge

# Voir les détails d'une PR
gh pr view 123
```

---

## 📚 Ressources

### Documentation
- [Guide d'intégration GitHub-Jira](./PARTIE4_GITHUB_JIRA_INTEGRATION.md)
- [Documentation Jira](https://support.atlassian.com/jira/)
- [Documentation GitHub](https://docs.github.com/)

### Outils
- [GitHub CLI](https://cli.github.com/)
- [Git Documentation](https://git-scm.com/doc)

---

## 🤝 Besoin d'Aide?

- **Problème avec Git**: Demandez à l'équipe
- **Problème avec Jira**: Contactez le Scrum Master
- **Problème avec le code**: Créez une issue GitHub

---

## ✅ Checklist Avant de Commiter

- [ ] Le code compile sans erreur
- [ ] Les tests passent
- [ ] Le code suit les conventions du projet
- [ ] La documentation est à jour
- [ ] Le commit commence par la clé Jira
- [ ] Le message de commit est clair

---

**Merci de contribuer au projet! 🎉**

---

**Dernière mise à jour**: 8 Mai 2026  
**Version**: 1.0.0
