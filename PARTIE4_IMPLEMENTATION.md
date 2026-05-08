# 🎯 PARTIE 4 - IMPLÉMENTATION

## Objectif Général
Atteindre un niveau de qualité logicielle professionnel sur le microservice étudiant en mettant en place:
- ✅ Stratégie de test complète (unitaire, intégration, E2E, stress)
- ✅ Couverture de code ≥ 80%
- ✅ Intégration Jira ↔ GitHub
- ✅ Intégration Xray pour la gestion des tests
- ✅ Microservice d'authentification (Express + MongoDB + JWT)

---

## 📋 Plan d'Implémentation

### Q1 - ✅ Branche Git version-4
**Status:** TERMINÉ
- ✅ Branche `version-4` créée à partir de `version-3`
- ⏳ Sprint 4 à créer dans Jira
- ⏳ User Stories à rédiger

### Q2 - Tests Complets sur etudiant-service (spring-boot-api)

#### 2.1 Tests Unitaires (JUnit 5 + Mockito)
**Objectif:** Tester la couche Service en isolation

**Fichiers à créer:**
- `spring-boot-api/src/test/java/com/studentmanagement/service/StudentServiceTest.java`
- `spring-boot-api/src/test/java/com/studentmanagement/service/DepartmentServiceTest.java`

**Tests à implémenter:**
- ✅ Création d'un étudiant
- ✅ Récupération de tous les étudiants
- ✅ Récupération par ID
- ✅ Récupération par année d'inscription
- ✅ Calcul de l'âge
- ✅ Mise à jour d'un étudiant
- ✅ Suppression d'un étudiant
- ✅ Gestion des exceptions

#### 2.2 Tests d'Intégration (Testcontainers)
**Objectif:** Tester avec une vraie base PostgreSQL

**Fichiers à créer:**
- `spring-boot-api/src/test/java/com/studentmanagement/integration/StudentIntegrationTest.java`
- `spring-boot-api/src/test/java/com/studentmanagement/integration/DepartmentIntegrationTest.java`

**Tests à implémenter:**
- ✅ Persistance et récupération d'un étudiant
- ✅ Requêtes JPA personnalisées
- ✅ Relations entre entités
- ✅ Transactions

**Dépendances Maven à ajouter:**
```xml
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>junit-jupiter</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>postgresql</artifactId>
    <scope>test</scope>
</dependency>
```

#### 2.3 Tests E2E avec Cypress
**Objectif:** Tester le comportement utilisateur complet

**Fichiers à créer:**
- `frontend/cypress/e2e/etudiants.cy.js`
- `frontend/cypress.config.js`

**Scénarios à tester:**
- ✅ Affichage de la liste des étudiants
- ✅ Création d'un nouvel étudiant
- ✅ Modification d'un étudiant
- ✅ Suppression d'un étudiant
- ✅ Recherche d'étudiants

**Prérequis:**
- Tous les services doivent être lancés via `docker-compose up`

#### 2.4 Tests de Stress avec Gatling
**Objectif:** Évaluer le comportement sous charge

**Fichiers à créer:**
- `spring-boot-api/src/gatling/java/simulations/EtudiantSimulation.java`

**Scénarios:**
- ✅ 50 utilisateurs concurrents pendant 30 secondes
- ✅ Appels GET /api/etudiants
- ✅ Mesure du temps de réponse moyen
- ✅ Mesure du percentile 95

**Plugin Maven à ajouter:**
```xml
<plugin>
    <groupId>io.gatling</groupId>
    <artifactId>gatling-maven-plugin</artifactId>
    <version>4.6.0</version>
</plugin>
```

#### 2.5 Couverture de Code avec JaCoCo
**Objectif:** Atteindre ≥ 80% de couverture

**Configuration Maven:**
```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.11</version>
    <configuration>
        <rules>
            <rule>
                <limits>
                    <limit>
                        <counter>LINE</counter>
                        <value>COVEREDRATIO</value>
                        <minimum>0.80</minimum>
                    </limit>
                </limits>
            </rule>
        </rules>
    </configuration>
</plugin>
```

---

### Q3 - ✅ Testcontainers pour PostgreSQL
**Status:** Intégré dans Q2.2

---

### Q4 - Intégration GitHub ↔ Jira

**Étapes:**
1. ⏳ Installer "GitHub for Jira" depuis Jira Marketplace
2. ⏳ Autoriser l'accès au dépôt GitHub
3. ⏳ Utiliser les conventions de nommage:
   - Branches: `feature/PROJ-XX-description`
   - Commits: `PROJ-XX : description`
   - PR: `PROJ-XX : titre`

**Exemple:**
```bash
git checkout -b feature/PROJ-24-tests-unitaires
git commit -m "PROJ-24 : ajout des tests unitaires StudentService"
```

---

### Q5 - Intégration Xray pour la Gestion des Tests

**Étapes:**
1. ⏳ Installer "Xray for Jira" depuis Jira Marketplace
2. ⏳ Créer des issues de type "Test" dans Jira
3. ⏳ Lier les Tests aux User Stories
4. ⏳ Créer un Test Plan pour Sprint 4
5. ⏳ Créer une Test Execution
6. ⏳ Configurer GitHub Actions pour publier les résultats

**Fichier à créer:**
- `.github/workflows/test-and-report.yml`

**Workflow CI/CD:**
```yaml
- name: Run tests
  run: mvn verify
  
- name: Publish results to Xray
  run: |
    curl -H "Content-Type: multipart/form-data" \
      -H "Authorization: Bearer ${{ secrets.XRAY_TOKEN }}" \
      -F "file=@target/surefire-reports/TEST-*.xml" \
      "https://xray.cloud.getxray.app/api/v2/import/execution/junit?projectKey=PROJ"
```

---

### Q6 - ✅ Microservice Authentification (Express + MongoDB + JWT)

**Status:** ✅ TERMINÉ

**Objectif:** Créer un nouveau microservice Node.js pour l'authentification

#### Structure du Projet ✅
```
auth-service/
├── src/
│   ├── config/
│   │   └── database.js          # ✅ Connexion MongoDB
│   ├── models/
│   │   └── User.js              # ✅ Modèle utilisateur
│   ├── routes/
│   │   └── auth.js              # ✅ Routes d'authentification
│   ├── middleware/
│   │   └── authMiddleware.js    # ✅ Middleware JWT
│   └── app.js                   # ✅ Application Express
├── package.json                 # ✅ Dépendances npm
├── Dockerfile                   # ✅ Image Docker
├── .env                         # ✅ Configuration
├── .gitignore                   # ✅ Exclusions Git
├── README.md                    # ✅ Documentation complète
└── test-auth-service.sh         # ✅ Script de test
```

#### Endpoints Implémentés ✅
- ✅ `POST /auth/register` - Inscription avec validation
- ✅ `POST /auth/login` - Connexion (retourne JWT)
- ✅ `GET /auth/verify` - Vérification du token
- ✅ `GET /auth/me` - Profil utilisateur
- ✅ `GET /health` - Health check

#### Technologies ✅
- ✅ **Express 4.18.2** - Framework web
- ✅ **Mongoose 8.0.0** - ODM pour MongoDB
- ✅ **bcrypt 5.1.1** - Hachage des mots de passe (10 rounds)
- ✅ **jsonwebtoken 9.0.2** - Génération et vérification JWT
- ✅ **dotenv 16.3.1** - Variables d'environnement
- ✅ **cors 2.8.5** - Gestion CORS

#### Dépendances npm ✅
```json
{
  "dependencies": {
    "express": "^4.18.2",
    "mongoose": "^8.0.0",
    "bcrypt": "^5.1.1",
    "jsonwebtoken": "^9.0.2",
    "dotenv": "^16.3.1",
    "cors": "^2.8.5"
  },
  "devDependencies": {
    "nodemon": "^3.0.1"
  }
}
```

#### Docker Compose ✅
Ajouté au `docker-compose.yml`:
```yaml
mongodb:
  image: mongo:7-alpine
  container_name: studentdb-mongodb
  ports:
    - "27017:27017"
  volumes:
    - mongodb-data:/data/db
  networks:
    - student-network
  healthcheck:
    test: ["CMD", "mongosh", "--eval", "db.adminCommand('ping')"]

auth-service:
  build: ./auth-service
  container_name: auth-service
  ports:
    - "3001:3001"
  environment:
    PORT: 3001
    MONGO_URI: mongodb://mongodb:27017/authdb
    JWT_SECRET: votre_secret_jwt_super_securise
    JWT_EXPIRES_IN: 24h
    NODE_ENV: production
  depends_on:
    mongodb:
      condition: service_healthy
  networks:
    - student-network
```

#### Sécurité Implémentée ✅
- ✅ Hachage bcrypt avec 10 rounds de salt
- ✅ JWT avec expiration (24h par défaut)
- ✅ Validation Mongoose (email, username, password)
- ✅ Middleware de protection des routes
- ✅ Gestion des rôles (user, admin)
- ✅ Utilisateur non-root dans Docker
- ✅ Messages d'erreur génériques

#### Tests ✅
```bash
# Démarrer les services
docker-compose up mongodb auth-service

# Tester avec le script
cd auth-service
chmod +x test-auth-service.sh
./test-auth-service.sh
```

#### Documentation ✅
- ✅ README.md complet avec exemples
- ✅ PARTIE4_AUTH_SERVICE.md avec détails d'implémentation
- ✅ Script de test automatisé
- ✅ Exemples d'intégration (Frontend, API Gateway, Flutter)

---

## 📊 Structure Finale du Dépôt

```
/projet-etudiants/
├── spring-boot-api/              # Etudiant Service
│   ├── src/
│   │   ├── main/
│   │   ├── test/
│   │   │   ├── java/
│   │   │   │   ├── unit/         # Tests unitaires (NOUVEAU)
│   │   │   │   └── integration/  # Tests Testcontainers (NOUVEAU)
│   │   │   └── resources/
│   │   │       └── features/     # Tests BDD (Partie 2)
│   │   └── gatling/              # Tests de stress (NOUVEAU)
│   └── pom.xml                   # Avec JaCoCo + Testcontainers + Gatling
├── grading-service/              # Grading Service (Partie 3)
├── eureka-server/                # Service Discovery (Partie 3)
├── api-gateway/                  # API Gateway (Partie 3)
├── auth-service/                 # Auth Service (NOUVEAU)
│   ├── src/
│   │   ├── models/User.js
│   │   ├── routes/auth.js
│   │   └── app.js
│   ├── package.json
│   └── Dockerfile
├── frontend/                     # Frontend Next.js
│   └── cypress/                  # Tests E2E (NOUVEAU)
│       └── e2e/
├── mobile_app/                   # Flutter App
├── k8s/                          # Kubernetes deployments
├── .github/
│   ├── workflows/
│   │   └── test-and-report.yml   # CI/CD avec Xray (NOUVEAU)
│   ├── ISSUE_TEMPLATE/
│   └── pull_request_template.md
├── docker-compose.yml            # Mis à jour avec MongoDB + auth-service
├── PARTIE3_VERIFICATION.md
└── PARTIE4_IMPLEMENTATION.md     # Ce fichier
```

---

## 🎯 Ordre d'Implémentation Recommandé

### Phase 1: Tests (Semaine 1)
1. ✅ Configurer JaCoCo dans pom.xml
2. ✅ Ajouter dépendances Testcontainers
3. ✅ Créer tests unitaires (StudentService, DepartmentService)
4. ✅ Créer tests d'intégration (Testcontainers)
5. ✅ Vérifier couverture ≥ 80%

### Phase 2: Tests Avancés (Semaine 1-2)
6. ✅ Configurer Gatling
7. ✅ Créer simulation de stress
8. ✅ Installer Cypress dans frontend
9. ✅ Créer tests E2E

### Phase 3: Intégrations Jira (Semaine 2)
10. ✅ Configurer GitHub ↔ Jira
11. ✅ Installer Xray
12. ✅ Créer Test Plan et Tests dans Jira
13. ✅ Configurer GitHub Actions

### Phase 4: Auth Service (Semaine 2-3) ✅
14. ✅ Créer projet Node.js auth-service
15. ✅ Implémenter modèle User
16. ✅ Implémenter routes /register et /login
17. ✅ Créer Dockerfile
18. ✅ Mettre à jour docker-compose.yml
19. ✅ Tester le service

---

## ✅ Critères de Validation

### Tests
- [ ] Couverture de code ≥ 80% (JaCoCo)
- [ ] Tous les tests unitaires passent
- [ ] Tous les tests d'intégration passent
- [ ] Tests E2E fonctionnels
- [ ] Tests de stress exécutés avec succès

### Intégrations
- [ ] GitHub ↔ Jira configuré
- [ ] Commits liés aux tickets Jira
- [ ] Xray installé et configuré
- [ ] Tests Jira liés aux User Stories
- [ ] Pipeline CI/CD publie les résultats dans Xray

### Auth Service
- [x] Service démarre correctement
- [x] Endpoint /register fonctionne
- [x] Endpoint /login retourne un JWT valide
- [x] Mots de passe hashés avec bcrypt
- [x] MongoDB connecté
- [x] Service intégré dans docker-compose

---

## 📝 Notes Importantes

### ⚠️ Ne PAS Toucher
- ✅ Code de la Partie 1, 2, 3
- ✅ Services existants (etudiant, grading, eureka, gateway)
- ✅ Base de données PostgreSQL
- ✅ Configuration Redis

### ✅ Ajouter Uniquement
- Tests dans `spring-boot-api/src/test/`
- Auth service dans `auth-service/`
- Tests Cypress dans `frontend/cypress/`
- Configuration CI/CD dans `.github/workflows/`
- MongoDB dans `docker-compose.yml`

### 🎯 Objectif Final
Un système complet avec:
- Qualité logicielle professionnelle (tests + couverture)
- Traçabilité complète (Jira + GitHub + Xray)
- Authentification sécurisée (JWT)
- Architecture microservices polyglotte (Java + Node.js)

---

**Date de création:** 7 Mai 2026  
**Branche:** version-4  
**Status:** 🚀 EN COURS
