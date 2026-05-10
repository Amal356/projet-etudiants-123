# 🧪 Intégration Xray - Gestion des Tests

**Date d'installation** : 10 mai 2026  
**Status** : ✅ En cours d'installation  
**Application** : Xray Test Management for Jira (Advanced Edition)

---

## 📋 Configuration

### Installation

- **Édition** : Advanced
- **Prix** : 12 USD/mois (après essai gratuit de 30 jours)
- **Essai gratuit** : 30 jours
- **Instance Jira** : https://amalyousef356.atlassian.net
- **Projet** : PROJ (Gestion Etudiants)
- **Éditeur** : Xblend
- **Certification** : CLOUD FORTIFIED
- **Installations** : 28,791
- **Note** : 3.4/4 ⭐⭐⭐

### Types d'issues Xray

Une fois installé, Xray ajoute les types d'issues suivants :

1. **Test** : Cas de test individuel
2. **Test Plan** : Plan de test regroupant plusieurs tests
3. **Test Execution** : Exécution d'un ou plusieurs tests
4. **Test Set** : Ensemble de tests réutilisables

---

## 🎯 Objectifs de l'intégration

### 1. Gestion des cas de test dans Jira

- Créer des Test Cases pour chaque type de test
- Lier les Tests aux User Stories via "Test Coverage"
- Organiser les tests dans des Test Plans
- Suivre l'exécution des tests dans des Test Executions

### 2. Publication automatique des résultats

- Publier les résultats JUnit depuis GitHub Actions
- Publier les résultats Cucumber depuis GitHub Actions
- Créer automatiquement des Test Executions
- Mettre à jour le statut des tests (PASS/FAIL)

### 3. Traçabilité complète

- Lier les tests aux exigences (User Stories)
- Suivre la couverture des tests
- Historique des exécutions
- Rapports de qualité

---

## 📊 Tests à créer dans Xray

### Tests Unitaires (11 tests)

#### Test 1 : StudentService.findAll()
```
Type : Test
Résumé : Test unitaire - StudentService.findAll()
Description :
  Vérifie que la méthode findAll() retourne tous les étudiants.
  
  Préconditions :
  - Le repository contient des étudiants
  
  Étapes :
  1. Mocker le repository avec une liste d'étudiants
  2. Appeler service.findAll()
  3. Vérifier que la liste retournée n'est pas vide
  
  Résultat attendu :
  - La liste contient tous les étudiants du repository

Test Type : Generic
Lié à : PROJ-30 (Tests unitaires JUnit + Mockito)
```

#### Test 2 : StudentService.findById()
```
Type : Test
Résumé : Test unitaire - StudentService.findById()
Description :
  Vérifie que findById() retourne l'étudiant correct.
  
  Préconditions :
  - Un étudiant existe avec l'ID 1
  
  Étapes :
  1. Mocker le repository pour retourner un étudiant
  2. Appeler service.findById(1L)
  3. Vérifier que l'étudiant retourné a l'ID 1
  
  Résultat attendu :
  - L'étudiant avec l'ID 1 est retourné

Test Type : Generic
Lié à : PROJ-30
```

#### Test 3 : StudentService.calculateAge()
```
Type : Test
Résumé : Test unitaire - StudentService.calculateAge()
Description :
  Vérifie le calcul de l'âge à partir de la date de naissance.
  
  Préconditions :
  - Date de naissance : 01/01/2000
  
  Étapes :
  1. Créer un étudiant avec date de naissance 01/01/2000
  2. Appeler service.calculateAge()
  3. Vérifier que l'âge calculé est correct
  
  Résultat attendu :
  - L'âge calculé correspond à l'année actuelle - 2000

Test Type : Generic
Lié à : PROJ-30
```

### Tests d'Intégration (9 tests)

#### Test 4 : Persistence PostgreSQL
```
Type : Test
Résumé : Test intégration - Persistence PostgreSQL
Description :
  Vérifie que les étudiants sont correctement persistés dans PostgreSQL.
  
  Préconditions :
  - Testcontainers démarre un conteneur PostgreSQL
  
  Étapes :
  1. Créer un étudiant
  2. Sauvegarder dans le repository
  3. Récupérer l'étudiant depuis la base
  4. Vérifier que les données sont identiques
  
  Résultat attendu :
  - L'étudiant est retrouvé avec toutes ses données

Test Type : Generic
Lié à : PROJ-31 (Tests d'intégration Testcontainers)
```

#### Test 5 : Cache Redis
```
Type : Test
Résumé : Test intégration - Cache Redis
Description :
  Vérifie que le cache Redis fonctionne correctement.
  
  Préconditions :
  - Redis est démarré
  
  Étapes :
  1. Récupérer un étudiant (mise en cache)
  2. Récupérer le même étudiant (depuis le cache)
  3. Vérifier que la 2e requête est plus rapide
  
  Résultat attendu :
  - La 2e requête utilise le cache

Test Type : Generic
Lié à : PROJ-31
```

### Tests E2E (21 tests)

#### Test 6 : Création d'un étudiant via UI
```
Type : Test
Résumé : Test E2E - Création d'un étudiant via UI
Description :
  Vérifie le parcours complet de création d'un étudiant.
  
  Préconditions :
  - Tous les services sont démarrés (docker-compose up)
  - Le frontend est accessible
  
  Étapes :
  1. Naviguer vers /etudiants/nouveau
  2. Remplir le formulaire (nom, CIN, email, etc.)
  3. Soumettre le formulaire
  4. Vérifier que l'étudiant apparaît dans la liste
  
  Résultat attendu :
  - L'étudiant est créé et visible dans la liste

Test Type : Cucumber
Lié à : PROJ-32 (Tests E2E Cypress)
```

#### Test 7 : Modification d'un étudiant
```
Type : Test
Résumé : Test E2E - Modification d'un étudiant
Description :
  Vérifie la modification d'un étudiant existant.
  
  Préconditions :
  - Un étudiant existe dans la base
  
  Étapes :
  1. Naviguer vers la liste des étudiants
  2. Cliquer sur "Modifier" pour un étudiant
  3. Modifier le nom
  4. Sauvegarder
  5. Vérifier que le nom est mis à jour
  
  Résultat attendu :
  - Le nom de l'étudiant est modifié

Test Type : Cucumber
Lié à : PROJ-32
```

### Tests de Stress (1 test)

#### Test 8 : 50 utilisateurs concurrents
```
Type : Test
Résumé : Test stress - 50 utilisateurs concurrents
Description :
  Vérifie que l'API supporte 50 utilisateurs concurrents.
  
  Préconditions :
  - Le service étudiant est démarré
  
  Étapes :
  1. Simuler 50 utilisateurs avec Gatling
  2. Chaque utilisateur appelle GET /api/etudiants
  3. Mesurer les temps de réponse
  
  Résultat attendu :
  - 100% de requêtes réussies
  - Temps de réponse moyen < 100ms
  - 95e percentile < 200ms

Test Type : Generic
Lié à : PROJ-33 (Tests stress Gatling)
```

### Tests de Sécurité (2 tests)

#### Test 9 : Authentification JWT
```
Type : Test
Résumé : Test sécurité - Authentification JWT
Description :
  Vérifie que l'authentification JWT fonctionne.
  
  Préconditions :
  - Le service d'authentification est démarré
  
  Étapes :
  1. S'inscrire avec email/password
  2. Se connecter
  3. Récupérer le token JWT
  4. Utiliser le token pour accéder à une route protégée
  
  Résultat attendu :
  - L'accès est autorisé avec un token valide

Test Type : Generic
Lié à : PROJ-37 (Auth service Express MongoDB JWT)
```

#### Test 10 : Protection des routes
```
Type : Test
Résumé : Test sécurité - Protection des routes
Description :
  Vérifie que les routes protégées refusent l'accès sans token.
  
  Préconditions :
  - Le service d'authentification est démarré
  
  Étapes :
  1. Tenter d'accéder à /auth/me sans token
  2. Vérifier que l'accès est refusé (401)
  
  Résultat attendu :
  - Erreur 401 Unauthorized

Test Type : Generic
Lié à : PROJ-37
```

---

## 📋 Test Plan - Sprint 4

```
Type : Test Plan
Résumé : Test Plan - Sprint 4 - Tests et Qualité
Description :
  Plan de test pour le Sprint 4 couvrant tous les aspects du système.
  
  Objectifs :
  - Valider la stratégie de test complète
  - Atteindre 80% de couverture de code
  - Vérifier les performances sous charge
  - Valider la sécurité de l'authentification
  
Tests inclus :
  - Test 1 : StudentService.findAll()
  - Test 2 : StudentService.findById()
  - Test 3 : StudentService.calculateAge()
  - Test 4 : Persistence PostgreSQL
  - Test 5 : Cache Redis
  - Test 6 : Création d'un étudiant via UI
  - Test 7 : Modification d'un étudiant
  - Test 8 : 50 utilisateurs concurrents
  - Test 9 : Authentification JWT
  - Test 10 : Protection des routes
  
Sprint : Sprint 4
Période : 9 mai - 23 mai 2026
```

---

## 🔧 Configuration de l'API Xray

### Étape 1 : Générer les API Keys

1. **Accéder aux paramètres Xray**
   ```
   Jira → Apps → Xray → API Keys
   ```

2. **Créer une nouvelle API Key**
   ```
   - Cliquer sur "Create API Key"
   - Nom : "GitHub Actions CI"
   - Copier le Client ID et Client Secret
   ```

3. **Sauvegarder les credentials**
   ```
   Client ID : VOTRE_CLIENT_ID
   Client Secret : VOTRE_CLIENT_SECRET
   ```

### Étape 2 : Configurer les secrets GitHub

1. **Aller dans GitHub → Settings → Secrets → Actions**

2. **Créer les secrets suivants** :
   ```
   XRAY_CLIENT_ID : Votre Client ID Xray
   XRAY_CLIENT_SECRET : Votre Client Secret Xray
   JIRA_PROJECT_KEY : PROJ
   ```

### Étape 3 : Authentification Xray

L'authentification se fait via l'API REST Xray :

```bash
curl -X POST \
  https://xray.cloud.getxray.app/api/v2/authenticate \
  -H "Content-Type: application/json" \
  -d '{
    "client_id": "VOTRE_CLIENT_ID",
    "client_secret": "VOTRE_CLIENT_SECRET"
  }'
```

Réponse :
```json
"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

Le token est valide pendant 1 heure.

---

## 🚀 Intégration GitHub Actions

### Workflow modifié : `.github/workflows/test-and-report.yml`

```yaml
name: CI Tests and Quality

on:
  push:
    branches: [ version-4, main ]
  pull_request:
    branches: [ version-4, main ]

jobs:
  test-spring-boot:
    runs-on: ubuntu-latest
    
    steps:
      - uses: actions/checkout@v3
      
      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'
      
      - name: Run tests with Maven
        run: |
          cd spring-boot-api
          mvn clean test
      
      - name: Generate JUnit XML reports
        if: always()
        run: |
          cd spring-boot-api
          mvn surefire-report:report
      
      # ========================================
      # XRAY INTEGRATION - AUTHENTICATION
      # ========================================
      - name: Authenticate with Xray
        id: xray-auth
        if: always()
        run: |
          XRAY_TOKEN=$(curl -X POST \
            https://xray.cloud.getxray.app/api/v2/authenticate \
            -H "Content-Type: application/json" \
            -d "{\"client_id\":\"${{ secrets.XRAY_CLIENT_ID }}\",\"client_secret\":\"${{ secrets.XRAY_CLIENT_SECRET }}\"}" \
            | jq -r '.')
          echo "::add-mask::$XRAY_TOKEN"
          echo "XRAY_TOKEN=$XRAY_TOKEN" >> $GITHUB_OUTPUT
      
      # ========================================
      # XRAY INTEGRATION - PUBLISH JUNIT RESULTS
      # ========================================
      - name: Publish JUnit results to Xray
        if: always() && steps.xray-auth.outputs.XRAY_TOKEN != ''
        run: |
          for file in spring-boot-api/target/surefire-reports/TEST-*.xml; do
            if [ -f "$file" ]; then
              curl -X POST \
                "https://xray.cloud.getxray.app/api/v2/import/execution/junit?projectKey=${{ secrets.JIRA_PROJECT_KEY }}" \
                -H "Content-Type: multipart/form-data" \
                -H "Authorization: Bearer ${{ steps.xray-auth.outputs.XRAY_TOKEN }}" \
                -F "file=@$file"
            fi
          done
      
      # ========================================
      # XRAY INTEGRATION - PUBLISH CUCUMBER RESULTS
      # ========================================
      - name: Publish Cucumber results to Xray
        if: always() && steps.xray-auth.outputs.XRAY_TOKEN != ''
        run: |
          if [ -f "spring-boot-api/target/cucumber-reports/cucumber.json" ]; then
            curl -X POST \
              "https://xray.cloud.getxray.app/api/v2/import/execution/cucumber?projectKey=${{ secrets.JIRA_PROJECT_KEY }}" \
              -H "Content-Type: application/json" \
              -H "Authorization: Bearer ${{ steps.xray-auth.outputs.XRAY_TOKEN }}" \
              -d @spring-boot-api/target/cucumber-reports/cucumber.json
          fi
      
      # ========================================
      # XRAY INTEGRATION - CREATE TEST EXECUTION
      # ========================================
      - name: Create Test Execution in Xray
        if: always() && steps.xray-auth.outputs.XRAY_TOKEN != ''
        run: |
          curl -X POST \
            "https://xray.cloud.getxray.app/api/v2/import/execution" \
            -H "Content-Type: application/json" \
            -H "Authorization: Bearer ${{ steps.xray-auth.outputs.XRAY_TOKEN }}" \
            -d '{
              "info": {
                "project": "${{ secrets.JIRA_PROJECT_KEY }}",
                "summary": "Test Execution - GitHub Actions - ${{ github.run_number }}",
                "description": "Automated test execution from GitHub Actions\n\nCommit: ${{ github.sha }}\nBranch: ${{ github.ref_name }}\nActor: ${{ github.actor }}",
                "testPlanKey": "PROJ-38"
              }
            }'
```

---

## ✅ Vérification de l'intégration

### Dans Jira

1. **Vérifier les Test Cases créés**
   ```
   Jira → Filters → All issues → Type = Test
   ```

2. **Vérifier le Test Plan**
   ```
   Jira → PROJ-38 (Test Plan - Sprint 4)
   ```

3. **Vérifier les Test Executions**
   ```
   Jira → Filters → All issues → Type = Test Execution
   ```

4. **Vérifier les résultats**
   ```
   - Ouvrir une Test Execution
   - Vérifier que les tests ont un statut (PASS/FAIL)
   - Vérifier les détails d'exécution
   ```

### Dans GitHub Actions

1. **Vérifier le workflow**
   ```
   GitHub → Actions → CI Tests and Quality
   ```

2. **Vérifier les étapes Xray**
   ```
   - Authenticate with Xray : ✅
   - Publish JUnit results to Xray : ✅
   - Publish Cucumber results to Xray : ✅
   - Create Test Execution in Xray : ✅
   ```

---

## 📊 Résultats attendus

### Après chaque run CI/CD

1. **Test Execution créée automatiquement**
   - Titre : "Test Execution - GitHub Actions - #XX"
   - Description : Commit, branche, acteur
   - Liée au Test Plan PROJ-38

2. **Résultats des tests importés**
   - Tests unitaires : 11/11 PASS
   - Tests d'intégration : 9/9 PASS
   - Tests E2E : 21/21 PASS
   - Tests de stress : 1/1 PASS
   - Tests de sécurité : 2/2 PASS

3. **Couverture de test visible**
   - Dans chaque User Story
   - Onglet "Test Coverage"
   - Liste des tests liés

---

## 🎓 Bonnes pratiques

### 1. Nommer les tests clairement

❌ **Mauvais** :
```
Test 1
Test de l'API
```

✅ **Bon** :
```
Test unitaire - StudentService.findAll()
Test E2E - Création d'un étudiant via UI
```

### 2. Lier les tests aux User Stories

Chaque test doit être lié à au moins une User Story via "Test Coverage".

### 3. Organiser les tests dans des Test Plans

Créer un Test Plan par Sprint ou par fonctionnalité.

### 4. Mettre à jour les tests régulièrement

Quand une fonctionnalité change, mettre à jour les tests correspondants.

### 5. Analyser les échecs

Quand un test échoue, analyser la cause et créer un Bug si nécessaire.

---

## 🔧 Dépannage

### Les résultats ne sont pas publiés

**Solutions** :
1. Vérifier que les secrets GitHub sont configurés
2. Vérifier que le token Xray est valide (1h)
3. Vérifier les logs GitHub Actions
4. Vérifier que les fichiers XML existent

### Les tests n'apparaissent pas dans Jira

**Solutions** :
1. Vérifier que les Test Cases existent dans Jira
2. Vérifier que le projectKey est correct
3. Vérifier que les noms de tests correspondent

### L'authentification échoue

**Solutions** :
1. Vérifier le Client ID et Client Secret
2. Régénérer les API Keys dans Xray
3. Mettre à jour les secrets GitHub

---

## 📚 Ressources

- [Documentation Xray Cloud](https://docs.getxray.app/display/XRAYCLOUD)
- [API REST Xray](https://docs.getxray.app/display/XRAYCLOUD/REST+API)
- [Import de résultats JUnit](https://docs.getxray.app/display/XRAYCLOUD/Import+Execution+Results+-+REST)
- [Import de résultats Cucumber](https://docs.getxray.app/display/XRAYCLOUD/Import+Execution+Results+-+REST+v2#ImportExecutionResultsRESTv2-CucumberJSONresults)

---

## ✅ Résultat

L'intégration Xray permet :

- ✅ Gestion centralisée des cas de test dans Jira
- ✅ Publication automatique des résultats depuis GitHub Actions
- ✅ Traçabilité complète entre tests et exigences
- ✅ Suivi de la couverture des tests
- ✅ Historique des exécutions
- ✅ Rapports de qualité
- ✅ Conformité avec la Question 5 de la Partie 4

---

*Documentation créée le 10 mai 2026*
