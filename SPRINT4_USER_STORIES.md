# Sprint 4 - User Stories Complètes

## Epic : PROJ-1 - Gestion des Étudiants

### 🎯 Sprint 4 : Tests et Qualité (Partie 4)

---

## ✅ User Stories Déjà Créées (Visibles dans Jira)

### PROJ-29 : Créer branche Git version-4
**En tant que** développeur  
**Je veux** créer une branche version-4 depuis version-3  
**Afin de** isoler le travail du Sprint 4

**Critères d'acceptation :**
- [ ] Branche `version-4` créée depuis `version-3`
- [ ] Sprint 4 créé dans Jira
- [ ] User Stories décomposées

**Tâches :**
- Créer la branche Git
- Créer le Sprint 4 dans Jira
- Décomposer les questions en User Stories

---

### PROJ-30 : Tests unitaires JUnit + Mockito
**En tant que** développeur  
**Je veux** ajouter des tests unitaires avec JUnit et Mockito  
**Afin de** tester la logique métier en isolation

**Critères d'acceptation :**
- [ ] Tests pour StudentService
- [ ] Tests pour StudentController
- [ ] Tests pour GlobalExceptionHandler
- [ ] Utilisation de @Mock et @InjectMocks
- [ ] Tous les tests passent

**Tâches :**
- Créer StudentServiceTest
- Créer StudentControllerTest
- Créer GlobalExceptionHandlerTest
- Configurer Mockito

---

### PROJ-31 : Tests d'intégration Testcontainers
**En tant que** développeur  
**Je veux** tester l'intégration avec PostgreSQL via Testcontainers  
**Afin de** valider la persistance des données

**Critères d'acceptation :**
- [ ] Testcontainers configuré pour PostgreSQL
- [ ] Tests d'intégration pour le repository
- [ ] Tests avec @SpringBootTest
- [ ] Tous les tests passent

**Tâches :**
- Ajouter dépendances Testcontainers
- Créer tests d'intégration
- Configurer PostgreSQLContainer

---

### PROJ-32 : Tests E2E Cypress
**En tant que** développeur  
**Je veux** tester les parcours utilisateur complets avec Cypress  
**Afin de** valider le comportement end-to-end

**Critères d'acceptation :**
- [ ] Tests Cypress pour les étudiants
- [ ] Tests Cypress pour les départements
- [ ] Tests passent avec docker-compose
- [ ] 21/21 tests réussis

**Tâches :**
- Créer tests students.cy.js
- Créer tests departments.cy.js
- Configurer Cypress
- Exécuter les tests

---

## 📋 User Stories à Créer dans Jira

### PROJ-33 : Tests de stress Gatling
**En tant que** développeur  
**Je veux** tester la performance de l'API sous charge avec Gatling  
**Afin de** valider la tenue en charge du système

**Critères d'acceptation :**
- [ ] Simulation Gatling créée
- [ ] Scénario : 50 utilisateurs sur 30 secondes
- [ ] Temps de réponse < 500ms pour 95% des requêtes
- [ ] Rapport Gatling généré

**Tâches :**
- Créer EtudiantSimulation.java
- Configurer le plugin Gatling Maven
- Exécuter la simulation
- Analyser les résultats

**Estimation :** 3 Story Points

---

### PROJ-34 : Couverture de code JaCoCo ≥ 80%
**En tant que** développeur  
**Je veux** mesurer et garantir une couverture de code ≥ 80%  
**Afin de** assurer la qualité du code

**Critères d'acceptation :**
- [ ] Plugin JaCoCo configuré dans pom.xml
- [ ] Seuil minimum de 80% défini
- [ ] Build échoue si couverture < 80%
- [ ] Rapport de couverture généré

**Tâches :**
- Configurer JaCoCo dans pom.xml
- Définir les règles de couverture
- Générer le rapport
- Vérifier la couverture

**Estimation :** 2 Story Points

---

### PROJ-35 : Intégration GitHub ↔ Jira
**En tant que** chef de projet  
**Je veux** lier automatiquement les commits et PR aux tickets Jira  
**Afin de** avoir une traçabilité complète

**Critères d'acceptation :**
- [ ] Application GitHub for Jira installée
- [ ] Convention de nommage définie
- [ ] Commits liés aux tickets
- [ ] PR visibles dans Jira

**Tâches :**
- Installer GitHub for Jira
- Configurer l'intégration
- Définir les conventions
- Tester la liaison

**Estimation :** 2 Story Points

---

### PROJ-36 : Intégration Xray pour gestion des tests
**En tant que** QA  
**Je veux** gérer les cas de test dans Jira avec Xray  
**Afin de** avoir une couverture fonctionnelle visible

**Critères d'acceptation :**
- [ ] Xray installé dans Jira
- [ ] Test Cases créés
- [ ] Test Plan créé pour Sprint 4
- [ ] Résultats publiés automatiquement

**Tâches :**
- Installer Xray for Jira
- Créer les Test Cases
- Créer le Test Plan
- Configurer l'API Xray
- Publier les résultats

**Estimation :** 5 Story Points

---

### PROJ-37 : Workflow GitHub Actions CI/CD
**En tant que** DevOps  
**Je veux** automatiser les tests via GitHub Actions  
**Afin de** valider chaque commit automatiquement

**Critères d'acceptation :**
- [ ] Workflow test-and-report.yml créé
- [ ] Tests backend automatisés
- [ ] Tests frontend automatisés
- [ ] Tests E2E automatisés
- [ ] Résultats publiés vers Xray

**Tâches :**
- Créer le workflow GitHub Actions
- Configurer les jobs de test
- Configurer la publication Xray
- Tester le workflow

**Estimation :** 5 Story Points

---

### PROJ-38 : Micro service Authentification (Express + MongoDB + JWT)
**En tant que** développeur  
**Je veux** créer un service d'authentification avec Express et JWT  
**Afin de** sécuriser l'accès aux micro services

**Critères d'acceptation :**
- [ ] Service Express créé
- [ ] MongoDB configuré
- [ ] Endpoints /register et /login fonctionnels
- [ ] Tokens JWT émis
- [ ] Mots de passe hashés avec bcrypt
- [ ] Service intégré dans docker-compose

**Tâches :**
- Créer le projet Node.js
- Configurer Express + Mongoose
- Implémenter l'inscription
- Implémenter la connexion
- Configurer JWT
- Dockeriser le service
- Ajouter au docker-compose

**Estimation :** 8 Story Points

---

## 📊 Résumé du Sprint 4

| User Story | Clé | Estimation | Statut |
|------------|-----|------------|--------|
| Créer branche version-4 | PROJ-29 | 1 SP | ✅ À faire |
| Tests unitaires JUnit + Mockito | PROJ-30 | 5 SP | ✅ À faire |
| Tests intégration Testcontainers | PROJ-31 | 3 SP | ✅ À faire |
| Tests E2E Cypress | PROJ-32 | 3 SP | ✅ À faire |
| Tests de stress Gatling | PROJ-33 | 3 SP | ⚠️ À créer |
| Couverture JaCoCo ≥ 80% | PROJ-34 | 2 SP | ⚠️ À créer |
| Intégration GitHub ↔ Jira | PROJ-35 | 2 SP | ⚠️ À créer |
| Intégration Xray | PROJ-36 | 5 SP | ⚠️ À créer |
| Workflow GitHub Actions | PROJ-37 | 5 SP | ⚠️ À créer |
| Micro service Auth | PROJ-38 | 8 SP | ⚠️ À créer |
| **TOTAL** | - | **37 SP** | - |

---

## 🎯 Ordre de Réalisation Recommandé

### Phase 1 : Infrastructure de Tests (Semaine 1)
1. PROJ-29 : Créer branche version-4
2. PROJ-30 : Tests unitaires JUnit + Mockito
3. PROJ-31 : Tests intégration Testcontainers
4. PROJ-34 : Couverture JaCoCo ≥ 80%

### Phase 2 : Tests Avancés (Semaine 2)
5. PROJ-32 : Tests E2E Cypress
6. PROJ-33 : Tests de stress Gatling

### Phase 3 : Intégration et Automatisation (Semaine 3)
7. PROJ-35 : Intégration GitHub ↔ Jira
8. PROJ-37 : Workflow GitHub Actions
9. PROJ-36 : Intégration Xray

### Phase 4 : Sécurité (Semaine 4)
10. PROJ-38 : Micro service Auth

---

## 📝 Template pour Créer les User Stories dans Jira

### Format de User Story :
```
Titre : [PROJ-XX] Nom de la fonctionnalité

Description :
En tant que [rôle]
Je veux [action]
Afin de [bénéfice]

Critères d'acceptation :
- [ ] Critère 1
- [ ] Critère 2
- [ ] Critère 3

Tâches :
- Tâche 1
- Tâche 2
- Tâche 3

Estimation : X Story Points
Epic Link : PROJ-1 (Gestion des Étudiants)
Sprint : Sprint 4
```

---

## 🔗 Conventions de Commit

Pour lier automatiquement les commits aux tickets Jira :

```bash
# Format de commit
git commit -m "PROJ-XX : Description du changement"

# Exemples
git commit -m "PROJ-30 : Ajout tests unitaires StudentService"
git commit -m "PROJ-33 : Configuration Gatling pour tests de stress"
git commit -m "PROJ-38 : Création service auth avec Express"
```

---

## 🔗 Conventions de Branches

```bash
# Format de branche
feature/PROJ-XX-description-courte

# Exemples
git checkout -b feature/PROJ-30-tests-unitaires
git checkout -b feature/PROJ-33-gatling-stress
git checkout -b feature/PROJ-38-auth-service
```

---

## 🔗 Conventions de Pull Request

**Titre :** `PROJ-XX : Description de la fonctionnalité`

**Description :**
```markdown
## Description
[Description détaillée des changements]

## Ticket Jira
- Lien : https://amalyousef356.atlassian.net/browse/PROJ-XX
- Type : Story / Task / Bug

## Changements
- [ ] Changement 1
- [ ] Changement 2

## Tests
- [ ] Tests unitaires ajoutés
- [ ] Tests d'intégration ajoutés
- [ ] Tests E2E ajoutés
- [ ] Tous les tests passent

## Checklist
- [ ] Code compilé sans erreurs
- [ ] Tests passent
- [ ] Documentation mise à jour
- [ ] Couverture ≥ 80%
```

---

**Date de création :** 15 mai 2026  
**Auteur :** Équipe Projet Étudiants  
**Sprint :** Sprint 4 - Tests et Qualité
