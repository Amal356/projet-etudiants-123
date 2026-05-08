const { defineConfig } = require('cypress');

module.exports = defineConfig({
  e2e: {
    baseUrl: 'http://localhost:8080',
    apiUrl: 'http://localhost:8080/api',
    
    // Configuration des timeouts
    defaultCommandTimeout: 10000,
    requestTimeout: 10000,
    responseTimeout: 10000,
    
    // Configuration des vidéos et screenshots
    video: true,
    screenshotOnRunFailure: true,
    
    // Configuration du viewport
    viewportWidth: 1280,
    viewportHeight: 720,
    
    // Variables d'environnement
    env: {
      apiUrl: 'http://localhost:8080/api',
      authUrl: 'http://localhost:3001/auth'
    },
    
    setupNodeEvents(on, config) {
      // implement node event listeners here
      on('task', {
        log(message) {
          console.log(message);
          return null;
        }
      });
    },
  },
});
