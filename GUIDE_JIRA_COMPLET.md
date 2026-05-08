# 📋 GUIDE JIRA COMPLET — Parties 1, 2, 3 et 4

## 🎯 Structure Globale

```
Epic : Gestion des Étudiants
 ├── Sprint 1 — Partie 1 : API REST de base (6 stories)
 ├── Sprint 2 — Partie 2 : Enrichissement (12 stories)
 ├── Sprint 3 — Partie 3 : Architecture Micro Services (9 stories)
 └── Sprint 4 — Partie 4 : Tests et Qualité (9 stories)

Total: 36 User Stories (PROJ-1 à PROJ-36)
```

---

## 📝 Étape 1: Créer le Projet Jira

1. Cliquez sur **"Créer un projet"**
2. Choisissez **"Scrum"**
3. Remplissez:
   - **Nom**: `Gestion Étudiants`
   - **Clé**: `PROJ`
   - **Type**: Team-managed
4. Cliquez sur **"Créer"**

---

## 📝 Étape 2: Créer l'Epic

1. Allez dans **"Backlog"**
2. Cliquez sur **"Create Epic"**
3. Remplissez:
   - **Nom**: `Gestion des Étudiants`
   - **Description**: `Système complet de gestion des étudiants et départements avec architecture microservices`

---

## 🚀 SPRINT 1 — Partie 1 : API REST de base

### Créer le Sprint 1
1. Dans le Backlog, cliquez sur **"Create sprint"**
2. Nom: `Sprint 1 - API REST de base`

### User Stories (6 stories)

#### PROJ-1 : Créer l'entité Etudiant
```
Type: Story
Sprint: Sprint 1
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux créer l'entité Etudiant,
Afin de modéliser les données des étudiants.

Attributs requis:
- id (Long, auto-généré)
- cin (String)
- nom (String)
- dateNaissance (LocalDate)

Critères d'acceptation:
- Annotations JPA correctes (@Entity, @Id, @GeneratedValue)
- Lombok utilisé (@Data, @Builder)
- Classe compilée sans erreur
```

#### PROJ-2 : Créer l'endpoint GET /api/etudiants
```
Type: Story
Sprint: Sprint 1
Epic: Gestion des Étudiants

Description:
En tant qu'admin,
Je veux lister tous les étudiants,
Afin de consulter la liste complète.

Critères d'acceptation:
- Endpoint GET /api/etudiants fonctionnel
- Retourne une liste JSON
- Code HTTP 200
```

#### PROJ-3 : Configurer PostgreSQL via Docker
```
Type: Story
Sprint: Sprint 1
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux une base de données PostgreSQL,
Afin de persister les données des étudiants.

Critères d'acceptation:
- PostgreSQL lancé via Docker
- application.properties configuré
- Connexion fonctionnelle
```

#### PROJ-4 : Créer le fichier docker-compose.yml
```
Type: Story
Sprint: Sprint 1
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux lancer l'API et la base de données,
Afin de démarrer tout le projet avec une seule commande.

Critères d'acceptation:
- Conteneur PostgreSQL configuré
- Conteneur Spring Boot configuré
- Réseau Docker entre les deux
- depends_on configuré
- docker compose up --build fonctionne
- API accessible sur http://localhost:8080/api/etudiants
```

#### PROJ-5 : Ajouter les données initiales
```
Type: Story
Sprint: Sprint 1
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux des données de test au démarrage,
Afin de tester l'API sans saisie manuelle.

Critères d'acceptation:
- Au moins 5 étudiants insérés au démarrage
- Via CommandLineRunner ou data.sql
- Données visibles via GET /api/etudiants
```

#### PROJ-6 : Créer le dépôt GitHub
```
Type: Task
Sprint: Sprint 1
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux un dépôt GitHub,
Afin de versionner le code.

Critères d'acceptation:
- Dépôt GitHub créé
- Structure correcte :
  /projet-etudiants/
  ├── api-spring-boot/
  ├── mobile-app/
  └── docker-compose.yml
- README.md présent
- .gitignore configuré
```

---

## 🚀 SPRINT 2 — Partie 2 : Enrichissement

### Créer le Sprint 2
1. Dans le Backlog, cliquez sur **"Create sprint"**
2. Nom: `Sprint 2 - Enrichissement`

### User Stories (12 stories)

#### PROJ-7 : Créer branche Git version-2
```
Type: Task
Sprint: Sprint 2
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux isoler le travail de la Partie 2,
Afin de garder un historique propre.

Critères d'acceptation:
- Branche version-2 créée depuis main
- git checkout -b version-2
- Commits liés aux tickets Jira
```

#### PROJ-8 : Ajouter méthode age() à l'entité Etudiant
```
Type: Story
Sprint: Sprint 2
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux calculer l'âge d'un étudiant,
Afin d'afficher son âge dynamiquement.

Critères d'acceptation:
- Méthode age() calculée avec Period.between()
- Utilise dateNaissance et LocalDate.now()
- Retourne un int
- Compatible avec Lombok
```

#### PROJ-9 : Tester age() avec Cucumber BDD
```
Type: Story
Sprint: Sprint 2
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux tester la méthode age() avec BDD,
Afin de valider le comportement attendu.

Critères d'acceptation:
- Fichier .feature créé avec scénario Gherkin
- Given / When / Then implémentés
- Step definitions Java créées
- Test passe avec succès
- Fichier dans : test/resources/features/etudiant.feature
```

#### PROJ-10 : Créer page index.html avec Fetch JS
```
Type: Story
Sprint: Sprint 2
Epic: Gestion des Étudiants

Description:
En tant qu'utilisateur,
Je veux voir la liste des étudiants dans un navigateur,
Afin de consulter les données visuellement.

Critères d'acceptation:
- Fichier index.html dans src/main/resources/static/
- Appel fetch('/api/etudiants') fonctionnel
- Liste affichée dynamiquement dans le DOM
- Aucun framework JS requis
```

#### PROJ-11 : Publier image Docker sur Docker Hub
```
Type: Story
Sprint: Sprint 2
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux publier l'image Docker,
Afin de la rendre disponible pour le déploiement.

Critères d'acceptation:
- Image buildée correctement
- Taguée avec username Docker Hub
- Poussée sur Docker Hub
- Commandes utilisées :
  docker build -t username/etudiant-service:1.0 .
  docker push username/etudiant-service:1.0
```

#### PROJ-12 : Créer manifests Kubernetes K3S
```
Type: Story
Sprint: Sprint 2
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux déployer l'application sur Kubernetes,
Afin de simuler un environnement de production.

Critères d'acceptation:
- k8s/etudiant-deployment.yaml créé
- k8s/postgres-deployment.yaml créé
- Deployment + Service pour chaque
- Déployé sur K3S local
- Accès vérifié via port-forward ou NodePort
```

#### PROJ-13 : Ajouter entité Département + relation ManyToOne
```
Type: Story
Sprint: Sprint 2
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux créer l'entité Département,
Afin d'organiser les étudiants par département.

Critères d'acceptation:
- Entité Departement (id Long, nom String)
- Relation @ManyToOne dans Etudiant
- Attributs Etudiant mis à jour :
  (id, cin, nom, dateNaissance, email,
   anneePremiereInscription, departement)
- Schéma généré via ddl-auto=update
```

#### PROJ-14 : Architecture en couches propre
```
Type: Story
Sprint: Sprint 2
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux refactoriser le projet en couches,
Afin de séparer clairement les responsabilités.

Critères d'acceptation:
- Packages créés :
  controller / service / repository /
  entity / dto / mapper / config
- Lombok utilisé (@Data, @Builder, @RequiredArgsConstructor)
- DTO utilisé dans Controller
- Mapper assure la conversion DTO <-> entité
- Service contient la logique métier
```

#### PROJ-15 : CRUD complet Etudiant et Département
```
Type: Story
Sprint: Sprint 2
Epic: Gestion des Étudiants

Description:
En tant qu'admin,
Je veux gérer les étudiants et départements,
Afin d'avoir toutes les opérations CRUD.

Critères d'acceptation:
Etudiants:
- GET /api/etudiants
- GET /api/etudiants/{id}
- POST /api/etudiants → 201 Created
- PUT /api/etudiants/{id}
- DELETE /api/etudiants/{id} → 204 No Content
- GET /api/etudiants?annee=2022

Départements:
- GET /api/departements
- GET /api/departements/{id}
- POST /api/departements → 201 Created
- PUT /api/departements/{id}
- DELETE /api/departements/{id} → 204 No Content
```

#### PROJ-16 : Gestion des erreurs HTTP standard
```
Type: Story
Sprint: Sprint 2
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux gérer les erreurs HTTP globalement,
Afin de retourner des réponses JSON structurées.

Critères d'acceptation:
- @RestControllerAdvice créé
- ResourceNotFoundException → 404
- MethodArgumentNotValidException → 400
- Erreurs génériques → 500
- Réponses JSON structurées
```

#### PROJ-17 : Documentation Swagger OpenAPI
```
Type: Story
Sprint: Sprint 2
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux documenter l'API automatiquement,
Afin de faciliter son utilisation.

Critères d'acceptation:
- Dépendance springdoc-openapi ajoutée dans pom.xml
- @Operation et @ApiResponse sur les controllers
- Documentation accessible sur /swagger-ui.html
```

#### PROJ-18 : Cache Redis
```
Type: Story
Sprint: Sprint 2
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux activer le cache Redis,
Afin d'améliorer les performances de lecture.

Critères d'acceptation:
- spring-boot-starter-data-redis ajouté
- @EnableCaching dans la config
- @Cacheable sur findAll()
- @CacheEvict sur save() et delete()
- Conteneur Redis dans docker-compose.yml
```

---

## 🚀 SPRINT 3 — Partie 3 : Architecture Micro Services

### Créer le Sprint 3
1. Dans le Backlog, cliquez sur **"Create sprint"**
2. Nom: `Sprint 3 - Architecture Micro Services`

### User Stories (9 stories)

#### PROJ-19 : Créer branche Git version-3
```
Type: Task
Sprint: Sprint 3
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux isoler le travail de la Partie 3,
Afin de garder un historique propre.

Critères d'acceptation:
- Branche version-3 créée depuis version-2
- git checkout version-2
- git checkout -b version-3
```

#### PROJ-20 : Définir le workflow GitHub
```
Type: Story
Sprint: Sprint 3
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux mettre en place un workflow GitHub structuré,
Afin de garantir la qualité du code.

Critères d'acceptation:
- Protection de branche activée sur main et version-3
- Review obligatoire avant merge
- Push direct interdit
- .github/ISSUE_TEMPLATE/bug_report.md créé
- .github/pull_request_template.md créé
- Section "Convention de review" dans README
```

#### PROJ-21 : Créer le micro service grading-service
```
Type: Story
Sprint: Sprint 3
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux créer un micro service de gestion des notes,
Afin de séparer les responsabilités.

Critères d'acceptation:
- Projet Spring Boot indépendant grading-service
- Entité Note (id, studentId, matiere, valeur)
- Architecture en couches (controller/service/repository/entity/dto/mapper/config)
- CRUD complet pour Note
- Gestion erreurs HTTP
- Documentation Swagger
```

#### PROJ-22 : Configurer Eureka Server
```
Type: Story
Sprint: Sprint 3
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux un serveur de registre Eureka,
Afin que les micro services se découvrent automatiquement.

Critères d'acceptation:
- Projet eureka-server créé
- @EnableEurekaServer configuré
- etudiant-service enregistré sur Eureka
- grading-service enregistré sur Eureka
- spring.application.name défini dans chaque service
```

#### PROJ-23 : Ajouter Feign Client dans grading-service
```
Type: Story
Sprint: Sprint 3
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux que grading-service communique avec etudiant-service,
Afin de valider l'existence d'un étudiant avant d'enregistrer une note.

Critères d'acceptation:
- @FeignClient(name = "etudiant-service") créé
- Méthode GET /api/etudiants/{id} appelée
- Adresse résolue via Eureka
- Vérification étudiant avant save() note
```

#### PROJ-24 : Créer l'API Gateway
```
Type: Story
Sprint: Sprint 3
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux un point d'entrée unique,
Afin que les clients n'appellent qu'une seule adresse.

Critères d'acceptation:
- Projet api-gateway créé
- Route /api/etudiants/** → etudiant-service
- Route /api/notes/** → grading-service
- Load balancing lb:// configuré
- Mobile et frontend passent par la Gateway
```

#### PROJ-25 : Mettre à jour l'application mobile
```
Type: Story
Sprint: Sprint 3
Epic: Gestion des Étudiants

Description:
En tant qu'utilisateur mobile,
Je veux filtrer les étudiants par département,
Afin de trouver rapidement les étudiants d'un département.

Critères d'acceptation:
- GET /api/departements appelé au chargement
- Sélection département (DropdownButton Flutter ou Picker React Native)
- Etudiants filtrés par département affiché
- Toutes les requêtes passent par l'API Gateway
```

#### PROJ-26 : Créer le frontend Next.js
```
Type: Story
Sprint: Sprint 3
Epic: Gestion des Étudiants

Description:
En tant qu'utilisateur,
Je veux une interface web moderne,
Afin de gérer étudiants et départements facilement.

Critères d'acceptation:
- Projet Next.js dans frontend/
- Page /etudiants (liste + formulaire)
- Page /etudiants/[id] (détail/édition)
- Page /departements (liste + gestion)
- Tailwind CSS pour le style
- Server Components pour lectures
- Client Components pour formulaires
- Toutes les requêtes via API Gateway
```

#### PROJ-27 : Mettre à jour docker-compose.yml
```
Type: Story
Sprint: Sprint 3
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux orchestrer tous les micro services,
Afin de lancer toute l'architecture avec une commande.

Critères d'acceptation:
Services inclus avec ports corrects :
- postgres : 5432
- redis : 6379
- eureka-server : 8761
- etudiant-service : 8081
- grading-service : 8082
- api-gateway : 8080
- frontend : 3000
Tous sur le même réseau Docker
depends_on configuré correctement
```

---

## 🚀 SPRINT 4 — Partie 4 : Tests et Qualité

### Créer le Sprint 4
1. Dans le Backlog, cliquez sur **"Create sprint"**
2. Nom: `Sprint 4 - Tests et Qualité`

### User Stories (9 stories)

#### PROJ-28 : Créer branche Git version-4
```
Type: Task
Sprint: Sprint 4
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux isoler le travail de la Partie 4,
Afin de garder un historique propre.

Critères d'acceptation:
- Branche version-4 créée depuis version-3
- git checkout version-3
- git checkout -b version-4
```

#### PROJ-29 : Tests unitaires avec JUnit + Mockito
```
Type: Story
Sprint: Sprint 4
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux des tests unitaires sur la couche Service,
Afin de valider la logique métier en isolation.

Critères d'acceptation:
- @ExtendWith(MockitoExtension.class) utilisé
- @Mock sur Repository et Mapper
- @InjectMocks sur Service
- Tests pour : findAll, findById, save, delete, age()
- Tests passent sans contexte Spring
```

#### PROJ-30 : Tests d'intégration avec Testcontainers
```
Type: Story
Sprint: Sprint 4
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux des tests d'intégration avec une vraie base PostgreSQL,
Afin de valider le comportement réel.

Critères d'acceptation:
- @SpringBootTest et @Testcontainers utilisés
- PostgreSQLContainer configuré
- @DynamicPropertySource pour les propriétés
- Tests de persistance et récupération
- Docker actif pendant les tests
```

#### PROJ-31 : Tests E2E avec Cypress
```
Type: Story
Sprint: Sprint 4
Epic: Gestion des Étudiants

Description:
En tant que testeur,
Je veux des tests End-to-End,
Afin de valider le comportement complet du système.

Critères d'acceptation:
- Cypress installé dans frontend/
- Tests dans cypress/e2e/
- Scénario : afficher la liste des étudiants
- Scénario : créer un nouvel étudiant
- data-testid ajoutés dans les composants
- Stack complète lancée via docker compose up
```

#### PROJ-32 : Tests de stress avec Gatling
```
Type: Story
Sprint: Sprint 4
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux mesurer les performances de l'API sous charge,
Afin d'identifier les goulots d'étranglement.

Critères d'acceptation:
- Plugin Gatling ajouté dans pom.xml
- Scénario EtudiantSimulation créé
- 50 utilisateurs en 30 secondes
- GET /api/etudiants testé
- Rapport généré avec temps de réponse moyen et p95
```

#### PROJ-33 : Couverture de code JaCoCo ≥ 80%
```
Type: Story
Sprint: Sprint 4
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux mesurer la couverture de code,
Afin de garantir la qualité des tests.

Critères d'acceptation:
- Plugin JaCoCo configuré dans pom.xml
- Seuil minimum 80% configuré
- Build échoue si couverture < 80%
- Rapport JaCoCo généré
```

#### PROJ-34 : Intégration GitHub ↔ Jira
```
Type: Story
Sprint: Sprint 4
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux lier GitHub et Jira automatiquement,
Afin d'avoir une traçabilité complète.

Critères d'acceptation:
- GitHub for Jira installé depuis Marketplace
- Dépôt GitHub connecté à Jira
- Commits référencent les clés PROJ-XX
- Branches nommées feature/PROJ-XX-description
- PRs liées aux tickets automatiquement
```

#### PROJ-35 : Intégration Xray pour les tests
```
Type: Story
Sprint: Sprint 4
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux gérer les cas de test dans Jira via Xray,
Afin d'avoir une couverture fonctionnelle visible.

Critères d'acceptation:
- Xray for Jira installé
- Test cases créés pour chaque méthode JUnit importante
- Tests liés aux User Stories via "Test Coverage"
- Test Plan créé pour Sprint 4
- Test Execution créée pour chaque run CI
- Résultats publiés automatiquement depuis GitHub Actions
```

#### PROJ-36 : Micro service Auth avec Express MongoDB JWT
```
Type: Story
Sprint: Sprint 4
Epic: Gestion des Étudiants

Description:
En tant que développeur,
Je veux un micro service d'authentification,
Afin de sécuriser l'accès à l'API.

Critères d'acceptation:
- Projet Node.js dans auth-service/
- POST /auth/register fonctionnel
- POST /auth/login retourne un token JWT
- Modèle User avec Mongoose (username, password)
- Passwords hashés avec bcrypt
- JWT signé avec JWT_SECRET
- Ajouté dans docker-compose.yml avec MongoDB
```

---

## 📸 Convention des Commits Git

```bash
# Sprint 1
git commit -m "PROJ-1 : création entité Etudiant"
git commit -m "PROJ-2 : création endpoint GET /api/etudiants"
git commit -m "PROJ-3 : configuration PostgreSQL Docker"
git commit -m "PROJ-4 : création docker-compose.yml"
git commit -m "PROJ-5 : ajout données initiales"
git commit -m "PROJ-6 : création dépôt GitHub"

# Sprint 2
git commit -m "PROJ-7 : création branche version-2"
git commit -m "PROJ-8 : ajout méthode age()"
git commit -m "PROJ-9 : tests BDD Cucumber age()"
git commit -m "PROJ-10 : création page index.html"
git commit -m "PROJ-11 : publication image Docker Hub"
git commit -m "PROJ-12 : manifests Kubernetes K3S"
git commit -m "PROJ-13 : entité Département ManyToOne"
git commit -m "PROJ-14 : refactorisation architecture couches"
git commit -m "PROJ-15 : CRUD complet Etudiant Département"
git commit -m "PROJ-16 : GlobalExceptionHandler"
git commit -m "PROJ-17 : documentation Swagger OpenAPI"
git commit -m "PROJ-18 : cache Redis"

# Sprint 3
git commit -m "PROJ-19 : création branche version-3"
git commit -m "PROJ-20 : workflow GitHub protection branches"
git commit -m "PROJ-21 : création grading-service"
git commit -m "PROJ-22 : configuration Eureka Server"
git commit -m "PROJ-23 : Feign Client grading vers etudiant"
git commit -m "PROJ-24 : création API Gateway"
git commit -m "PROJ-25 : mise à jour application mobile"
git commit -m "PROJ-26 : création frontend Next.js"
git commit -m "PROJ-27 : docker-compose unifié tous services"

# Sprint 4
git commit -m "PROJ-28 : création branche version-4"
git commit -m "PROJ-29 : tests unitaires JUnit Mockito"
git commit -m "PROJ-30 : tests intégration Testcontainers"
git commit -m "PROJ-31 : tests E2E Cypress"
git commit -m "PROJ-32 : tests stress Gatling"
git commit -m "PROJ-33 : couverture JaCoCo 80%"
git commit -m "PROJ-34 : intégration GitHub Jira"
git commit -m "PROJ-35 : Xray cas de test"
git commit -m "PROJ-36 : auth-service Express MongoDB JWT"
```

---

## 📊 Résumé Final

| Sprint | Partie | Nombre de Stories |
|--------|--------|-------------------|
| Sprint 1 | API REST de base | 6 stories (PROJ-1 à PROJ-6) |
| Sprint 2 | Enrichissement | 12 stories (PROJ-7 à PROJ-18) |
| Sprint 3 | Micro Services | 9 stories (PROJ-19 à PROJ-27) |
| Sprint 4 | Tests et Qualité | 9 stories (PROJ-28 à PROJ-36) |
| **Total** | **4 Parties** | **36 stories** |

---

## ✅ Checklist Finale

### Projet Jira
- [ ] Projet "Gestion Étudiants" créé (clé: PROJ)
- [ ] Epic "Gestion des Étudiants" créée

### Sprint 1
- [ ] Sprint 1 créé
- [ ] 6 User Stories créées (PROJ-1 à PROJ-6)
- [ ] Stories assignées au Sprint 1
- [ ] Stories liées à l'Epic

### Sprint 2
- [ ] Sprint 2 créé
- [ ] 12 User Stories créées (PROJ-7 à PROJ-18)
- [ ] Stories assignées au Sprint 2
- [ ] Stories liées à l'Epic

### Sprint 3
- [ ] Sprint 3 créé
- [ ] 9 User Stories créées (PROJ-19 à PROJ-27)
- [ ] Stories assignées au Sprint 3
- [ ] Stories liées à l'Epic

### Sprint 4
- [ ] Sprint 4 créé
- [ ] 9 User Stories créées (PROJ-28 à PROJ-36)
- [ ] Stories assignées au Sprint 4
- [ ] Stories liées à l'Epic

### Documentation
- [ ] Capture d'écran du board prise
- [ ] Image sauvegardée dans docs/jira-board.png
- [ ] README.md mis à jour

---

**Date**: 8 Mai 2026  
**Source**: PDFs officiels Parties 1, 2, 3 et 4  
**Total**: 36 User Stories réparties sur 4 Sprints
