# 🧪 Tests E2E avec Cypress

Tests End-to-End pour l'API de gestion des étudiants.

## 📋 Prérequis

- Node.js 18+ ou 20+
- npm ou yarn
- Tous les services doivent être lancés (voir section "Démarrage des services")

## 🚀 Installation

```bash
cd cypress-tests
npm install
```

## 🏃 Démarrage des Services

**IMPORTANT:** Tous les services doivent être lancés avant d'exécuter les tests!

```bash
# Depuis la racine du projet
docker-compose up

# Ou seulement les services nécessaires
docker-compose up postgres redis eureka-server etudiant-service
```

**Vérifier que les services sont prêts:**
- API Étudiant: http://localhost:8080/api/etudiants
- Eureka: http://localhost:8761

## 🧪 Exécution des Tests

### Mode Interactif (Interface Graphique)

```bash
npm run cypress:open
```

Cette commande ouvre l'interface Cypress où vous pouvez:
- Sélectionner les tests à exécuter
- Voir les tests en temps réel
- Déboguer facilement

### Mode Headless (Ligne de Commande)

```bash
# Exécuter tous les tests E2E
npm run cypress:run

# Exécuter seulement les tests API
npm run test:api

# Exécuter un fichier spécifique
npx cypress run --spec "cypress/e2e/api/students.cy.js"
```

## 📊 Structure des Tests

```
cypress-tests/
├── cypress/
│   ├── e2e/
│   │   └── api/
│   │       ├── students.cy.js      # Tests pour l'API Étudiants
│   │       └── departments.cy.js   # Tests pour l'API Départements
│   ├── support/
│   │   ├── commands.js             # Commandes personnalisées
│   │   └── e2e.js                  # Configuration globale
│   ├── videos/                     # Vidéos des tests (générées)
│   └── screenshots/                # Screenshots des échecs (générés)
├── cypress.config.js               # Configuration Cypress
├── package.json
└── README.md
```

## 🎯 Tests Implémentés

### API Étudiants (`students.cy.js`)

#### 1. Récupération (GET)
- ✅ Récupérer tous les étudiants
- ✅ Récupérer un étudiant par ID
- ✅ Erreur 404 pour étudiant inexistant
- ✅ Récupérer par année d'inscription

#### 2. Création (POST)
- ✅ Créer un nouvel étudiant
- ✅ Validation des données invalides
- ✅ Contrainte d'unicité du CIN

#### 3. Mise à jour (PUT)
- ✅ Mettre à jour un étudiant existant
- ✅ Erreur 404 pour étudiant inexistant

#### 4. Suppression (DELETE)
- ✅ Supprimer un étudiant existant
- ✅ Erreur 404 pour étudiant inexistant

#### 5. Scénario CRUD Complet
- ✅ Cycle complet: Créer → Lire → Mettre à jour → Supprimer

#### 6. Tests de Performance
- ✅ Temps de réponse < 2 secondes
- ✅ Gestion de requêtes simultanées

### API Départements (`departments.cy.js`)

- ✅ Récupération de tous les départements
- ✅ Récupération par ID
- ✅ Création de département
- ✅ Mise à jour de département
- ✅ Suppression de département

## 🔧 Configuration

### Variables d'Environnement

Les URLs sont configurées dans `cypress.config.js`:

```javascript
env: {
  apiUrl: 'http://localhost:8080/api',
  authUrl: 'http://localhost:3001/auth'
}
```

### Timeouts

```javascript
defaultCommandTimeout: 10000,
requestTimeout: 10000,
responseTimeout: 10000
```

## 📹 Vidéos et Screenshots

- **Vidéos**: Enregistrées automatiquement pour chaque test en mode headless
- **Screenshots**: Capturés automatiquement en cas d'échec
- **Emplacement**: `cypress/videos/` et `cypress/screenshots/`

## 🛠️ Commandes Personnalisées

### `cy.createTestStudent(overrides)`

Crée un étudiant de test avec des données par défaut.

```javascript
cy.createTestStudent({ nom: 'John Doe' }).then((student) => {
  cy.log(`Étudiant créé: ${student.id}`);
});
```

### `cy.deleteStudent(studentId)`

Supprime un étudiant par son ID.

```javascript
cy.deleteStudent(123);
```

### `cy.createTestDepartment(name)`

Crée un département de test.

```javascript
cy.createTestDepartment('Informatique').then((dept) => {
  cy.log(`Département créé: ${dept.id}`);
});
```

### `cy.checkApiHealth()`

Vérifie la santé de l'API.

```javascript
cy.checkApiHealth().then((response) => {
  expect(response.status).to.eq(200);
});
```

## 📊 Rapports de Tests

### Rapport Console

Les tests affichent des logs détaillés dans la console:

```
✅ 5 étudiant(s) trouvé(s)
✅ Étudiant créé avec ID: 123
✅ Temps de réponse: 245ms
🎉 Cycle CRUD complet réussi!
```

### Rapport HTML

Pour générer un rapport HTML (nécessite un plugin):

```bash
npm install --save-dev cypress-mochawesome-reporter
```

## 🐛 Débogage

### Mode Interactif

Le mode interactif (`npm run cypress:open`) est idéal pour le débogage:
- Voir les requêtes HTTP en temps réel
- Inspecter les réponses
- Rejouer les tests étape par étape

### Logs Détaillés

Utilisez `cy.log()` pour ajouter des logs personnalisés:

```javascript
cy.log('🔍 Vérification de l\'étudiant...');
```

### Pause dans les Tests

Ajoutez `cy.pause()` pour mettre en pause l'exécution:

```javascript
cy.request('GET', '/api/etudiants').then((response) => {
  cy.pause(); // Le test s'arrête ici
  expect(response.status).to.eq(200);
});
```

## ⚠️ Problèmes Courants

### Services non démarrés

**Erreur:** `cy.request() failed trying to load: http://localhost:8080/api/etudiants`

**Solution:** Vérifier que tous les services sont lancés:
```bash
docker-compose up
```

### Port déjà utilisé

**Erreur:** `Port 8080 is already in use`

**Solution:** Arrêter les services existants:
```bash
docker-compose down
docker-compose up
```

### Timeout

**Erreur:** `Timed out retrying after 10000ms`

**Solution:** Augmenter les timeouts dans `cypress.config.js` ou vérifier que l'API répond.

## 📈 Bonnes Pratiques

1. **Isolation des Tests**: Chaque test doit être indépendant
2. **Nettoyage**: Supprimer les données de test après utilisation
3. **Données Uniques**: Utiliser des timestamps pour éviter les conflits
4. **Assertions Claires**: Vérifier les status codes et les données
5. **Logs Informatifs**: Ajouter des logs pour faciliter le débogage

## 🔄 CI/CD

### GitHub Actions

Exemple de workflow:

```yaml
name: Cypress Tests

on: [push, pull_request]

jobs:
  cypress:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      
      - name: Start services
        run: docker-compose up -d
      
      - name: Wait for services
        run: sleep 30
      
      - name: Run Cypress tests
        run: |
          cd cypress-tests
          npm install
          npm run cypress:run
      
      - name: Upload videos
        if: always()
        uses: actions/upload-artifact@v3
        with:
          name: cypress-videos
          path: cypress-tests/cypress/videos
```

## 📚 Ressources

- [Documentation Cypress](https://docs.cypress.io/)
- [Best Practices Cypress](https://docs.cypress.io/guides/references/best-practices)
- [API Testing avec Cypress](https://docs.cypress.io/guides/guides/network-requests)

## 🎓 Exemples d'Utilisation

### Test Simple

```javascript
it('Devrait récupérer tous les étudiants', () => {
  cy.request('GET', `${Cypress.env('apiUrl')}/etudiants`)
    .then((response) => {
      expect(response.status).to.eq(200);
      expect(response.body).to.be.an('array');
    });
});
```

### Test avec Données Dynamiques

```javascript
it('Devrait créer un étudiant', () => {
  const timestamp = Date.now();
  const student = {
    nom: `Test ${timestamp}`,
    email: `test.${timestamp}@example.com`,
    cin: `CIN${timestamp}`,
    dateNaissance: '2000-01-15',
    anneePremiereInscription: 2023
  };

  cy.request('POST', `${Cypress.env('apiUrl')}/etudiants`, student)
    .then((response) => {
      expect(response.status).to.eq(201);
      expect(response.body.nom).to.eq(student.nom);
    });
});
```

### Test avec Commande Personnalisée

```javascript
it('Devrait créer et supprimer un étudiant', () => {
  cy.createTestStudent().then((student) => {
    cy.log(`Étudiant créé: ${student.id}`);
    
    cy.deleteStudent(student.id).then((response) => {
      expect(response.status).to.eq(204);
    });
  });
});
```

---

**Date:** 7 Mai 2026  
**Version:** 1.0.0  
**Partie:** 4 - Question 2.3
