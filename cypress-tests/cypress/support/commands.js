// ***********************************************
// Custom commands pour les tests E2E
// ***********************************************

/**
 * Commande personnalisée pour créer un étudiant de test
 */
Cypress.Commands.add('createTestStudent', (overrides = {}) => {
  const timestamp = Date.now();
  const defaultStudent = {
    nom: `Test Student ${timestamp}`,
    email: `test.${timestamp}@example.com`,
    cin: `CIN${timestamp}`,
    dateNaissance: '2000-01-15',
    anneePremiereInscription: 2023
  };

  const student = { ...defaultStudent, ...overrides };

  return cy.request({
    method: 'POST',
    url: `${Cypress.env('apiUrl')}/etudiants`,
    body: student
  }).then((response) => {
    expect(response.status).to.eq(201);
    return response.body;
  });
});

/**
 * Commande personnalisée pour supprimer un étudiant
 */
Cypress.Commands.add('deleteStudent', (studentId) => {
  return cy.request({
    method: 'DELETE',
    url: `${Cypress.env('apiUrl')}/etudiants/${studentId}`,
    failOnStatusCode: false
  });
});

/**
 * Commande personnalisée pour créer un département de test
 */
Cypress.Commands.add('createTestDepartment', (name) => {
  const timestamp = Date.now();
  const departmentName = name || `Test Department ${timestamp}`;

  return cy.request({
    method: 'POST',
    url: `${Cypress.env('apiUrl')}/departements`,
    body: { nom: departmentName }
  }).then((response) => {
    expect(response.status).to.eq(201);
    return response.body;
  });
});

/**
 * Commande personnalisée pour vérifier la santé de l'API
 */
Cypress.Commands.add('checkApiHealth', () => {
  return cy.request({
    method: 'GET',
    url: Cypress.env('apiUrl'),
    failOnStatusCode: false
  }).then((response) => {
    cy.log(`API Status: ${response.status}`);
    return response;
  });
});

/**
 * Commande personnalisée pour nettoyer les données de test
 */
Cypress.Commands.add('cleanupTestData', () => {
  cy.log('🧹 Nettoyage des données de test...');
  // Cette commande peut être étendue pour nettoyer les données de test
});
