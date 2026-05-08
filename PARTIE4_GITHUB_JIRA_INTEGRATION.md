# 🔗 PARTIE 4 - Q4: Intégration GitHub ↔ Jira

## 📋 Objectif

Intégrer GitHub avec Jira pour assurer la traçabilité complète entre:
- Les commits Git et les tickets Jira
- Les branches et les User Stories
- Les Pull Requests et les tâches

---

## 🎯 Étape 1: Installation de GitHub for Jira

### 1.1 Accéder au Jira Marketplace

1. **Connectez-vous à votre instance Jira**
   - URL: `https://votre-domaine.atlassian.net`

2. **Accédez au Marketplace**
   - Cliquez sur l'icône ⚙️ (Paramètres) en haut à droite
   - Sélectionnez **"Apps"** ou **"Applications"**
   - Cliquez sur **"Find new apps"** ou **"Trouver de nouvelles applications"**

3. **Recherchez "GitHub for Jira"**
   - Dans la barre de recherche, tapez: `GitHub for Jira`
   - Sélectionnez l'application officielle **"GitHub for Jira"** par Atlassian

### 1.2 Installer l'Application

1. **Cliquez sur "Get it now"** ou **"Obtenir maintenant"**
2. **Acceptez les conditions**
3. **Attendez l'installation** (quelques secondes)
4. **Confirmez l'installation réussie**

### 1.3 Configurer GitHub for Jira

1. **Accédez aux paramètres de l'app**
   - ⚙️ Paramètres → Apps → Manage apps
   - Trouvez "GitHub for Jira"
   - Cliquez sur **"Configure"**

2. **Connectez votre compte GitHub**
   - Cliquez sur **"Get started"**
   - Sélectionnez **"GitHub Cloud"** (ou GitHub Enterprise si applicable)
   - Cliquez sur **"Connect GitHub account"**

3. **Autorisez l'accès**
   - Vous serez redirigé vers GitHub
   - Connectez-vous à votre compte GitHub
   - Autorisez l'application Jira à accéder à vos dépôts
   - Sélectionnez les dépôts à connecter (sélectionnez votre dépôt `projet-etudiants`)

4. **Confirmez la connexion**
   - Retournez sur Jira
   - Vérifiez que le dépôt apparaît dans la liste des dépôts connectés

---

## 🎯 Étape 2: Vérifier le Projet Jira Existant

### 2.1 Contexte du Projet

**⚠️ IMPORTANT**: Le projet Jira existe déjà depuis la Partie 2!

**Historique Jira du projet:**
- **Partie 2 - Q14**: Création du projet Scrum "Gestion des Étudiants"
  - Epic: "Gestion des Étudiants"
  - Sprint 1: User Stories de la Partie 1
  - Sprint 2: User Stories de la Partie 2
  - Capture d'écran du board dans README

- **Partie 3 - Q1**: Création du Sprint 3
  - Décomposition des questions en User Stories et Tasks
  - Organisation du backlog pour la Partie 3

- **Partie 4**: Création du Sprint 4 (cette partie)
  - Tests et qualité logicielle
  - Intégrations (GitHub, Xray)
  - Microservice d'authentification

### 2.2 Vérifier le Projet Existant

1. **Accédez à votre projet Jira**
   - Ouvrez Jira: `https://votre-domaine.atlassian.net`
   - Sélectionnez le projet **"Gestion Étudiants"**
   - Clé du projet: `PROJ` (ou la clé que vous avez choisie)

2. **Vérifiez les Sprints Existants**
   - Cliquez sur **"Backlog"** dans le menu de gauche
   - Vous devriez voir:
     - ✅ Sprint 1 (Partie 1) - Complété
     - ✅ Sprint 2 (Partie 2) - Complété
     - ✅ Sprint 3 (Partie 3) - Complété
     - ⏳ Sprint 4 (Partie 4) - À créer

3. **Vérifiez l'Epic Existante**
   - Allez dans **"Epics"** ou **"Roadmap"**
   - Vous devriez voir l'Epic: **"Gestion des Étudiants"**
   - Toutes les stories des Parties 1, 2, 3 devraient être liées à cette Epic

### 2.3 Types d'Issues Disponibles

Votre projet devrait déjà avoir:
- ✅ **Epic** - Grandes fonctionnalités
- ✅ **Story** - User Stories
- ✅ **Task** - Tâches techniques
- ✅ **Bug** - Bugs
- ⏳ **Test** - Tests (à ajouter avec Xray si pas déjà fait)

---

## 🎯 Étape 3: Créer le Sprint 4

### 3.1 Accéder au Backlog

1. **Ouvrez votre projet Jira**
2. **Cliquez sur "Backlog"** dans le menu de gauche
3. **Vous verrez la liste des sprints et le backlog**

### 3.2 Créer le Sprint 4

**Note**: Vous avez déjà créé les Sprints 1, 2 et 3 dans les parties précédentes.

1. **Cliquez sur "Create sprint"** en haut du backlog
2. **Nommez le sprint**: `Sprint 4 - Tests et Qualité`
3. **Définissez les dates** (optionnel):
   - Date de début: Date actuelle
   - Date de fin: +2 semaines
   - Durée: 2 semaines (standard)

4. **Cliquez sur "Create"**

**Contexte des Sprints Précédents:**
- ✅ **Sprint 1** (Partie 1): API REST de base, CRUD étudiants/départements
- ✅ **Sprint 2** (Partie 2): Tests BDD, Redis, Docker, Frontend Next.js
- ✅ **Sprint 3** (Partie 3): Microservices (Eureka, Gateway, Grading Service)
- 🆕 **Sprint 4** (Partie 4): Tests complets, Auth Service, Intégrations

### 3.3 Configurer le Sprint

1. **Objectif du Sprint** (Sprint Goal):
   ```
   Atteindre un niveau de qualité logicielle professionnel avec:
   - Tests unitaires, intégration, E2E
   - Couverture de code ≥ 80%
   - Microservice d'authentification
   - Intégrations Jira et Xray
   ```

2. **Cliquez sur "Start sprint"** pour démarrer le sprint

---

## 🎯 Étape 4: Créer les User Stories pour la Partie 4

### 4.1 User Stories à Créer

**⚠️ IMPORTANT**: Les numéros de tickets ci-dessous (PROJ-24 à PROJ-31) sont des exemples.
Utilisez les numéros réels qui suivent vos tickets existants dans Jira.

**Contexte**: Vous avez déjà créé des User Stories pour:
- Sprint 1 (Partie 1): PROJ-1 à PROJ-X
- Sprint 2 (Partie 2): PROJ-X à PROJ-Y
- Sprint 3 (Partie 3): PROJ-Y à PROJ-Z

**Pour le Sprint 4**, créez les User Stories suivantes (ajustez les numéros selon votre projet):

#### Story 1: Tests Unitaires avec Mockito
```
Titre: PROJ-24 - Implémenter les tests unitaires avec Mockito
Type: Story
Description:
En tant que développeur,
Je veux créer des tests unitaires pour la couche Service,
Afin de garantir le bon fonctionnement de la logique métier en isolation.

Critères d'acceptation:
- [ ] Tests pour StudentService créés
- [ ] Tests pour DepartmentService créés
- [ ] Utilisation de Mockito pour les mocks
- [ ] Couverture de tous les cas (succès + erreurs)
- [ ] Tous les tests passent

Story Points: 5
Sprint: Sprint 4
```

#### Story 2: Tests d'Intégration avec Testcontainers
```
Titre: PROJ-25 - Implémenter les tests d'intégration avec Testcontainers
Type: Story
Description:
En tant que développeur,
Je veux créer des tests d'intégration avec une vraie base PostgreSQL,
Afin de valider le comportement complet de l'application.

Critères d'acceptation:
- [ ] Testcontainers configuré avec PostgreSQL
- [ ] Tests de persistance créés
- [ ] Tests de relations JPA créés
- [ ] Tests de requêtes personnalisées créés
- [ ] Tous les tests passent

Story Points: 8
Sprint: Sprint 4
```

#### Story 3: Tests E2E avec Cypress
```
Titre: PROJ-26 - Implémenter les tests E2E avec Cypress
Type: Story
Description:
En tant que développeur,
Je veux créer des tests End-to-End pour l'API REST,
Afin de valider le comportement complet du système.

Critères d'acceptation:
- [ ] Cypress installé et configuré
- [ ] Tests CRUD pour étudiants créés
- [ ] Tests CRUD pour départements créés
- [ ] Tests de performance créés
- [ ] Tous les tests passent

Story Points: 8
Sprint: Sprint 4
```

#### Story 4: Tests de Stress avec Gatling
```
Titre: PROJ-27 - Implémenter les tests de stress avec Gatling
Type: Story
Description:
En tant que développeur,
Je veux créer des tests de charge avec Gatling,
Afin d'évaluer les performances sous charge.

Critères d'acceptation:
- [ ] Gatling configuré dans Maven
- [ ] Scénarios de charge créés
- [ ] Tests de montée en charge créés
- [ ] Assertions sur les temps de réponse
- [ ] Rapport de performance généré

Story Points: 5
Sprint: Sprint 4
```

#### Story 5: Couverture de Code avec JaCoCo
```
Titre: PROJ-28 - Configurer JaCoCo pour la couverture de code
Type: Story
Description:
En tant que développeur,
Je veux mesurer la couverture de code avec JaCoCo,
Afin d'atteindre un minimum de 80% de couverture.

Critères d'acceptation:
- [ ] JaCoCo configuré dans Maven
- [ ] Seuil minimum de 80% défini
- [ ] Rapport de couverture généré
- [ ] Couverture ≥ 80% atteinte

Story Points: 3
Sprint: Sprint 4
```

#### Story 6: Microservice d'Authentification
```
Titre: PROJ-29 - Créer le microservice d'authentification
Type: Story
Description:
En tant que développeur,
Je veux créer un microservice Node.js pour l'authentification,
Afin de sécuriser l'accès aux ressources avec JWT.

Critères d'acceptation:
- [ ] Service Node.js + Express créé
- [ ] MongoDB configuré
- [ ] Endpoint /register implémenté
- [ ] Endpoint /login implémenté avec JWT
- [ ] Endpoint /verify implémenté
- [ ] Mots de passe hashés avec bcrypt
- [ ] Service dockerisé

Story Points: 13
Sprint: Sprint 4
```

#### Story 7: Intégration GitHub ↔ Jira
```
Titre: PROJ-30 - Configurer l'intégration GitHub ↔ Jira
Type: Story
Description:
En tant que chef de projet,
Je veux intégrer GitHub avec Jira,
Afin d'assurer la traçabilité entre le code et les tickets.

Critères d'acceptation:
- [ ] GitHub for Jira installé
- [ ] Dépôt GitHub connecté
- [ ] Conventions de nommage définies
- [ ] Documentation créée

Story Points: 3
Sprint: Sprint 4
```

#### Story 8: Intégration Xray pour Tests
```
Titre: PROJ-31 - Configurer Xray pour la gestion des tests
Type: Story
Description:
En tant que testeur,
Je veux intégrer Xray avec Jira,
Afin de gérer les tests et les résultats dans Jira.

Critères d'acceptation:
- [ ] Xray installé
- [ ] Test cases créés
- [ ] Test plan créé
- [ ] GitHub Actions configuré
- [ ] Résultats publiés automatiquement

Story Points: 5
Sprint: Sprint 4
```

### 4.2 Créer les Stories dans Jira

Pour chaque story ci-dessus:

1. **Cliquez sur "Create"** (bouton + en haut)
2. **Remplissez les champs**:
   - Type: Story
   - Summary: [Titre de la story]
   - Description: [Description complète]
   - Story Points: [Valeur]
   - Sprint: Sprint 4
3. **Cliquez sur "Create"**
4. **Répétez pour toutes les stories**

---

## 🎯 Étape 5: Conventions de Nommage

### 5.1 Branches Git

**Format**: `<type>/<clé-jira>-<description>`

**Types de branches**:
- `feature/` - Nouvelles fonctionnalités
- `bugfix/` - Corrections de bugs
- `hotfix/` - Corrections urgentes
- `test/` - Ajout de tests
- `docs/` - Documentation

**Exemples**:
```bash
feature/PROJ-24-tests-unitaires
feature/PROJ-25-tests-integration
feature/PROJ-26-tests-e2e-cypress
feature/PROJ-27-tests-stress-gatling
feature/PROJ-28-couverture-jacoco
feature/PROJ-29-auth-service
feature/PROJ-30-github-jira-integration
feature/PROJ-31-xray-integration
```

### 5.2 Commits Git

**Format**: `<clé-jira> : <description>`

**Exemples**:
```bash
git commit -m "PROJ-24 : ajout des tests unitaires StudentService"
git commit -m "PROJ-25 : configuration Testcontainers PostgreSQL"
git commit -m "PROJ-26 : création des tests E2E avec Cypress"
git commit -m "PROJ-27 : ajout des scénarios de stress Gatling"
git commit -m "PROJ-28 : configuration JaCoCo avec seuil 80%"
git commit -m "PROJ-29 : création du service d'authentification"
git commit -m "PROJ-30 : documentation intégration GitHub-Jira"
git commit -m "PROJ-31 : configuration Xray et GitHub Actions"
```

**Règles importantes**:
- ✅ Toujours commencer par la clé Jira (ex: `PROJ-24`)
- ✅ Utiliser `:` après la clé
- ✅ Description claire et concise
- ✅ Verbe à l'infinitif ou au passé composé
- ✅ Pas de point final

### 5.3 Pull Requests

**Format**: `<clé-jira> : <titre>`

**Exemples**:
```
PROJ-24 : Tests unitaires avec Mockito
PROJ-25 : Tests d'intégration avec Testcontainers
PROJ-26 : Tests E2E avec Cypress
PROJ-27 : Tests de stress avec Gatling
PROJ-29 : Microservice d'authentification
```

**Description de la PR**:
```markdown
## Description
Implémentation des tests unitaires pour la couche Service avec Mockito.

## Changements
- Ajout de StudentServiceTest avec 11 tests
- Configuration Mockito dans pom.xml
- Tous les tests passent (11/11)

## Tests
- [x] Tests unitaires passent
- [x] Build Maven réussit
- [x] Aucune régression

## Lié à
- Jira: PROJ-24
- Sprint: Sprint 4

## Checklist
- [x] Code testé localement
- [x] Documentation mise à jour
- [x] Pas de conflits
```

---

## 🎯 Étape 6: Utiliser les Conventions

### 6.1 Créer une Branche

```bash
# Depuis la branche version-4
git checkout version-4

# Créer une branche pour une story
git checkout -b feature/PROJ-24-tests-unitaires

# Travailler sur la fonctionnalité
# ...

# Commiter avec la convention
git add .
git commit -m "PROJ-24 : ajout des tests unitaires StudentService"

# Pousser la branche
git push -u origin feature/PROJ-24-tests-unitaires
```

### 6.2 Créer une Pull Request

```bash
# Avec GitHub CLI (gh)
gh pr create \
  --title "PROJ-24 : Tests unitaires avec Mockito" \
  --body "Implémentation des tests unitaires pour StudentService" \
  --base version-4 \
  --head feature/PROJ-24-tests-unitaires

# Ou via l'interface web GitHub
# 1. Aller sur GitHub
# 2. Cliquer sur "Pull requests"
# 3. Cliquer sur "New pull request"
# 4. Sélectionner base: version-4 et compare: feature/PROJ-24-tests-unitaires
# 5. Remplir le titre et la description
# 6. Créer la PR
```

### 6.3 Vérifier l'Intégration

Une fois la PR créée:

1. **Allez sur Jira**
2. **Ouvrez le ticket PROJ-24**
3. **Vérifiez la section "Development"** (à droite)
4. **Vous devriez voir**:
   - La branche créée
   - Les commits associés
   - La Pull Request

---

## 🎯 Étape 7: Workflow Complet

### 7.1 Workflow Standard

```bash
# 1. Créer une story dans Jira (ex: PROJ-24)

# 2. Créer une branche
git checkout version-4
git pull origin version-4
git checkout -b feature/PROJ-24-tests-unitaires

# 3. Développer la fonctionnalité
# ... écrire le code ...

# 4. Commiter régulièrement
git add .
git commit -m "PROJ-24 : ajout des tests unitaires StudentService"
git commit -m "PROJ-24 : ajout des tests pour les cas d'erreur"
git commit -m "PROJ-24 : correction des tests suite à review"

# 5. Pousser la branche
git push -u origin feature/PROJ-24-tests-unitaires

# 6. Créer une Pull Request
gh pr create --title "PROJ-24 : Tests unitaires avec Mockito"

# 7. Attendre la review et merger

# 8. Mettre à jour le ticket Jira
# - Passer le statut à "Done"
# - Ajouter un commentaire avec le lien de la PR
```

### 7.2 Exemple Complet pour la Partie 4

```bash
# Story PROJ-29: Microservice d'authentification

# 1. Créer la branche
git checkout -b feature/PROJ-29-auth-service

# 2. Créer le service
mkdir auth-service
cd auth-service
npm init -y
# ... développement ...

# 3. Commits progressifs
git add auth-service/package.json
git commit -m "PROJ-29 : initialisation du projet Node.js"

git add auth-service/src/models/User.js
git commit -m "PROJ-29 : création du modèle User avec Mongoose"

git add auth-service/src/routes/auth.js
git commit -m "PROJ-29 : implémentation des routes /register et /login"

git add auth-service/Dockerfile docker-compose.yml
git commit -m "PROJ-29 : dockerisation du service et ajout dans docker-compose"

git add auth-service/README.md
git commit -m "PROJ-29 : documentation du service d'authentification"

# 4. Pousser et créer la PR
git push -u origin feature/PROJ-29-auth-service
gh pr create --title "PROJ-29 : Microservice d'authentification"

# 5. Vérifier dans Jira que tout est lié
```

---

## 🎯 Étape 8: Automatisation avec GitHub Actions

### 8.1 Créer un Workflow pour Lier les PRs

Créez `.github/workflows/jira-integration.yml`:

```yaml
name: Jira Integration

on:
  pull_request:
    types: [opened, edited, synchronize]

jobs:
  check-jira-key:
    runs-on: ubuntu-latest
    steps:
      - name: Check PR title has Jira key
        run: |
          PR_TITLE="${{ github.event.pull_request.title }}"
          if [[ ! $PR_TITLE =~ ^PROJ-[0-9]+ ]]; then
            echo "❌ PR title must start with Jira key (ex: PROJ-24)"
            exit 1
          fi
          echo "✅ PR title has valid Jira key"
      
      - name: Extract Jira key
        id: jira
        run: |
          PR_TITLE="${{ github.event.pull_request.title }}"
          JIRA_KEY=$(echo $PR_TITLE | grep -oP 'PROJ-\d+')
          echo "key=$JIRA_KEY" >> $GITHUB_OUTPUT
          echo "Found Jira key: $JIRA_KEY"
      
      - name: Comment on PR
        uses: actions/github-script@v6
        with:
          script: |
            const jiraKey = '${{ steps.jira.outputs.key }}';
            const jiraUrl = `https://votre-domaine.atlassian.net/browse/${jiraKey}`;
            github.rest.issues.createComment({
              issue_number: context.issue.number,
              owner: context.repo.owner,
              repo: context.repo.repo,
              body: `🔗 Lié à Jira: [${jiraKey}](${jiraUrl})`
            });
```

### 8.2 Workflow pour Mettre à Jour Jira

```yaml
name: Update Jira on Merge

on:
  pull_request:
    types: [closed]

jobs:
  update-jira:
    if: github.event.pull_request.merged == true
    runs-on: ubuntu-latest
    steps:
      - name: Extract Jira key
        id: jira
        run: |
          PR_TITLE="${{ github.event.pull_request.title }}"
          JIRA_KEY=$(echo $PR_TITLE | grep -oP 'PROJ-\d+')
          echo "key=$JIRA_KEY" >> $GITHUB_OUTPUT
      
      - name: Transition Jira issue
        run: |
          echo "Would transition ${{ steps.jira.outputs.key }} to Done"
          # Nécessite configuration de l'API Jira
```

---

## 📊 Vérification de l'Intégration

### Checklist de Vérification

- [ ] GitHub for Jira installé dans Jira
- [ ] Dépôt GitHub connecté à Jira
- [ ] Sprint 4 créé dans Jira
- [ ] 8 User Stories créées pour la Partie 4
- [ ] Conventions de nommage documentées
- [ ] Branche créée avec convention (ex: `feature/PROJ-24-...`)
- [ ] Commit créé avec convention (ex: `PROJ-24 : ...`)
- [ ] PR créée avec convention
- [ ] Lien visible dans Jira (section Development)
- [ ] Workflow GitHub Actions configuré (optionnel)

### Test de l'Intégration

1. **Créer une branche de test**:
   ```bash
   git checkout -b feature/PROJ-30-test-integration
   ```

2. **Créer un fichier de test**:
   ```bash
   echo "# Test intégration GitHub-Jira" > TEST_INTEGRATION.md
   git add TEST_INTEGRATION.md
   git commit -m "PROJ-30 : test de l'intégration GitHub-Jira"
   git push -u origin feature/PROJ-30-test-integration
   ```

3. **Créer une PR**:
   ```bash
   gh pr create --title "PROJ-30 : Test intégration GitHub-Jira"
   ```

4. **Vérifier dans Jira**:
   - Ouvrir le ticket PROJ-30
   - Vérifier la section "Development"
   - Vous devriez voir la branche, le commit et la PR

5. **Nettoyer**:
   ```bash
   # Fermer la PR
   gh pr close
   # Supprimer la branche
   git checkout version-4
   git branch -D feature/PROJ-30-test-integration
   git push origin --delete feature/PROJ-30-test-integration
   ```

---

## 📝 Documentation pour l'Équipe

### Guide Rapide pour les Développeurs

Créez un fichier `CONTRIBUTING.md` à la racine du projet:

```markdown
# Guide de Contribution

## Workflow Git + Jira

### 1. Prendre une Story
- Assigner la story à vous-même dans Jira
- Passer le statut à "In Progress"

### 2. Créer une Branche
\`\`\`bash
git checkout version-4
git pull origin version-4
git checkout -b feature/PROJ-XX-description
\`\`\`

### 3. Développer
- Écrire le code
- Écrire les tests
- Vérifier que tout fonctionne

### 4. Commiter
\`\`\`bash
git add .
git commit -m "PROJ-XX : description du changement"
\`\`\`

### 5. Pousser et Créer une PR
\`\`\`bash
git push -u origin feature/PROJ-XX-description
gh pr create --title "PROJ-XX : Titre de la story"
\`\`\`

### 6. Review et Merge
- Attendre la review
- Corriger si nécessaire
- Merger la PR

### 7. Mettre à Jour Jira
- Passer le statut à "Done"
- Ajouter un commentaire
```

---

## 🎉 Résultat Final

Une fois l'intégration complète, vous aurez:

✅ **Traçabilité Complète**
- Chaque commit lié à un ticket Jira
- Chaque PR liée à une story
- Historique complet visible dans Jira

✅ **Visibilité**
- Voir le code directement depuis Jira
- Voir les tickets depuis GitHub
- Suivi de l'avancement en temps réel

✅ **Automatisation**
- Mise à jour automatique des tickets
- Validation des conventions
- Notifications automatiques

✅ **Collaboration**
- Équipe alignée sur les conventions
- Communication facilitée
- Moins d'erreurs

---

**Date:** 8 Mai 2026  
**Branche:** version-4  
**Status:** 📝 GUIDE COMPLET  
**Partie:** 4 - Question 4

---

## 🔗 Ressources

- [GitHub for Jira Documentation](https://support.atlassian.com/jira-cloud-administration/docs/integrate-with-github/)
- [Jira Smart Commits](https://support.atlassian.com/jira-software-cloud/docs/process-issues-with-smart-commits/)
- [GitHub CLI Documentation](https://cli.github.com/manual/)
