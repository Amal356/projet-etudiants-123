// ***********************************************************
// Support file pour les tests E2E
// ***********************************************************

// Import des commandes personnalisées
import './commands';

// Configuration globale
Cypress.on('uncaught:exception', (err, runnable) => {
  // Empêcher Cypress de fail sur les exceptions non capturées
  // Retourner false pour ne pas fail le test
  return false;
});

// Hook avant tous les tests
before(() => {
  cy.log('🚀 Initialisation des tests E2E');
});

// Hook après tous les tests
after(() => {
  cy.log('🏁 Fin des tests E2E');
});
