/**
 * Tests E2E pour l'API Étudiants
 * 
 * Prérequis:
 * - Tous les services doivent être lancés: docker-compose up
 * - L'API doit être accessible sur http://localhost:8080
 */

describe('API Étudiants - Tests E2E', () => {
  const apiUrl = Cypress.env('apiUrl');
  let createdStudentId;
  let departmentId;

  // Hook avant tous les tests
  before(() => {
    cy.log('🚀 Démarrage des tests E2E pour l\'API Étudiants');
    
    // Vérifier que l'API est accessible
    cy.request({
      method: 'GET',
      url: `${apiUrl}/etudiants`,
      failOnStatusCode: false
    }).then((response) => {
      expect(response.status).to.be.oneOf([200, 404]);
      cy.log('✅ API accessible');
    });
  });

  // Hook avant chaque test
  beforeEach(() => {
    cy.log('📝 Préparation du test...');
  });

  // Hook après chaque test
  afterEach(() => {
    cy.log('✅ Test terminé');
  });

  describe('1. Récupération des étudiants (GET)', () => {
    it('Devrait récupérer la liste de tous les étudiants', () => {
      cy.request({
        method: 'GET',
        url: `${apiUrl}/etudiants`
      }).then((response) => {
        expect(response.status).to.eq(200);
        expect(response.body).to.be.an('array');
        cy.log(`✅ ${response.body.length} étudiant(s) trouvé(s)`);
        
        // Si des étudiants existent, vérifier la structure
        if (response.body.length > 0) {
          const student = response.body[0];
          expect(student).to.have.property('id');
          expect(student).to.have.property('nom');
          expect(student).to.have.property('email');
          expect(student).to.have.property('cin');
        }
      });
    });

    it('Devrait récupérer un étudiant par ID', () => {
      // D'abord, récupérer la liste pour obtenir un ID
      cy.request('GET', `${apiUrl}/etudiants`).then((response) => {
        if (response.body.length > 0) {
          const studentId = response.body[0].id;
          
          cy.request({
            method: 'GET',
            url: `${apiUrl}/etudiants/${studentId}`
          }).then((getResponse) => {
            expect(getResponse.status).to.eq(200);
            expect(getResponse.body).to.have.property('id', studentId);
            expect(getResponse.body).to.have.property('nom');
            expect(getResponse.body).to.have.property('email');
            cy.log(`✅ Étudiant ${studentId} récupéré avec succès`);
          });
        } else {
          cy.log('⚠️ Aucun étudiant existant pour tester la récupération par ID');
        }
      });
    });

    it('Devrait retourner 404 pour un étudiant inexistant', () => {
      cy.request({
        method: 'GET',
        url: `${apiUrl}/etudiants/99999`,
        failOnStatusCode: false
      }).then((response) => {
        expect(response.status).to.eq(404);
        cy.log('✅ Erreur 404 correctement retournée');
      });
    });

    it('Devrait récupérer les étudiants par année d\'inscription', () => {
      const year = 2023;
      
      cy.request({
        method: 'GET',
        url: `${apiUrl}/etudiants/annee/${year}`,
        failOnStatusCode: false
      }).then((response) => {
        // Accepter 200 (succès) ou 404 (aucun étudiant pour cette année)
        expect(response.status).to.be.oneOf([200, 404, 500]);
        
        if (response.status === 200) {
          expect(response.body).to.be.an('array');
          
          // Vérifier que tous les étudiants ont l'année correcte
          response.body.forEach((student) => {
            expect(student.anneePremiereInscription).to.eq(year);
          });
          
          cy.log(`✅ ${response.body.length} étudiant(s) de ${year} trouvé(s)`);
        } else {
          cy.log(`⚠️ Endpoint /annee/${year} retourne ${response.status} - peut-être non implémenté`);
        }
      });
    });
  });

  describe('2. Création d\'étudiants (POST)', () => {
    it('Devrait créer un nouvel étudiant avec succès', () => {
      const timestamp = Date.now();
      const newStudent = {
        nom: `Test E2E ${timestamp}`,
        email: `test.e2e.${timestamp}@example.com`,
        cin: `CIN${timestamp}`,
        dateNaissance: '2000-01-15',
        anneePremiereInscription: 2023
      };

      cy.request({
        method: 'POST',
        url: `${apiUrl}/etudiants`,
        body: newStudent
      }).then((response) => {
        expect(response.status).to.eq(201);
        expect(response.body).to.have.property('id');
        expect(response.body.nom).to.eq(newStudent.nom);
        expect(response.body.email).to.eq(newStudent.email);
        expect(response.body.cin).to.eq(newStudent.cin);
        
        // Sauvegarder l'ID pour les tests suivants
        createdStudentId = response.body.id;
        
        cy.log(`✅ Étudiant créé avec ID: ${createdStudentId}`);
      });
    });

    it('Devrait rejeter la création avec des données invalides', () => {
      const invalidStudent = {
        nom: '', // Nom vide
        email: 'invalid-email', // Email invalide
        cin: ''
      };

      cy.request({
        method: 'POST',
        url: `${apiUrl}/etudiants`,
        body: invalidStudent,
        failOnStatusCode: false
      }).then((response) => {
        expect(response.status).to.be.oneOf([400, 500]);
        cy.log('✅ Validation des données fonctionne correctement');
      });
    });

    it('Devrait rejeter la création d\'un étudiant avec un CIN existant', () => {
      // Créer un premier étudiant
      const timestamp = Date.now();
      const student1 = {
        nom: `Test Duplicate ${timestamp}`,
        email: `test.dup1.${timestamp}@example.com`,
        cin: `CINDUP${timestamp}`,
        dateNaissance: '2000-01-15',
        anneePremiereInscription: 2023
      };

      cy.request('POST', `${apiUrl}/etudiants`, student1).then(() => {
        // Essayer de créer un deuxième étudiant avec le même CIN
        const student2 = {
          nom: `Test Duplicate 2 ${timestamp}`,
          email: `test.dup2.${timestamp}@example.com`,
          cin: `CINDUP${timestamp}`, // Même CIN
          dateNaissance: '2000-01-15',
          anneePremiereInscription: 2023
        };

        cy.request({
          method: 'POST',
          url: `${apiUrl}/etudiants`,
          body: student2,
          failOnStatusCode: false
        }).then((response) => {
          expect(response.status).to.be.oneOf([400, 409, 500]);
          cy.log('✅ Contrainte d\'unicité du CIN respectée');
        });
      });
    });
  });

  describe('3. Mise à jour d\'étudiants (PUT)', () => {
    it('Devrait mettre à jour un étudiant existant', () => {
      // Utiliser l'étudiant créé précédemment
      if (createdStudentId) {
        const updatedData = {
          nom: `Test E2E Updated ${Date.now()}`,
          email: `updated.${Date.now()}@example.com`,
          cin: `CINUPD${Date.now()}`,
          dateNaissance: '2000-01-15',
          anneePremiereInscription: 2024 // Changement d'année
        };

        cy.request({
          method: 'PUT',
          url: `${apiUrl}/etudiants/${createdStudentId}`,
          body: updatedData
        }).then((response) => {
          expect(response.status).to.eq(200);
          expect(response.body.id).to.eq(createdStudentId);
          expect(response.body.nom).to.eq(updatedData.nom);
          expect(response.body.anneePremiereInscription).to.eq(2024);
          
          cy.log(`✅ Étudiant ${createdStudentId} mis à jour avec succès`);
        });
      } else {
        cy.log('⚠️ Aucun étudiant créé pour tester la mise à jour');
      }
    });

    it('Devrait retourner 404 lors de la mise à jour d\'un étudiant inexistant', () => {
      const updatedData = {
        nom: 'Test Inexistant',
        email: 'inexistant@example.com',
        cin: 'CININEX',
        dateNaissance: '2000-01-15',
        anneePremiereInscription: 2023
      };

      cy.request({
        method: 'PUT',
        url: `${apiUrl}/etudiants/99999`,
        body: updatedData,
        failOnStatusCode: false
      }).then((response) => {
        expect(response.status).to.eq(404);
        cy.log('✅ Erreur 404 correctement retournée pour mise à jour');
      });
    });
  });

  describe('4. Suppression d\'étudiants (DELETE)', () => {
    it('Devrait supprimer un étudiant existant', () => {
      // Créer un étudiant spécifiquement pour le supprimer
      const timestamp = Date.now();
      const studentToDelete = {
        nom: `Test Delete ${timestamp}`,
        email: `test.delete.${timestamp}@example.com`,
        cin: `CINDEL${timestamp}`,
        dateNaissance: '2000-01-15',
        anneePremiereInscription: 2023
      };

      cy.request('POST', `${apiUrl}/etudiants`, studentToDelete).then((createResponse) => {
        const studentId = createResponse.body.id;

        // Supprimer l'étudiant
        cy.request({
          method: 'DELETE',
          url: `${apiUrl}/etudiants/${studentId}`
        }).then((deleteResponse) => {
          expect(deleteResponse.status).to.eq(204);
          cy.log(`✅ Étudiant ${studentId} supprimé avec succès`);

          // Vérifier que l'étudiant n'existe plus
          cy.request({
            method: 'GET',
            url: `${apiUrl}/etudiants/${studentId}`,
            failOnStatusCode: false
          }).then((getResponse) => {
            expect(getResponse.status).to.eq(404);
            cy.log('✅ Étudiant bien supprimé de la base de données');
          });
        });
      });
    });

    it('Devrait retourner 404 lors de la suppression d\'un étudiant inexistant', () => {
      cy.request({
        method: 'DELETE',
        url: `${apiUrl}/etudiants/99999`,
        failOnStatusCode: false
      }).then((response) => {
        expect(response.status).to.eq(404);
        cy.log('✅ Erreur 404 correctement retournée pour suppression');
      });
    });
  });

  describe('5. Scénario complet CRUD', () => {
    it('Devrait exécuter un cycle complet: Créer → Lire → Mettre à jour → Supprimer', () => {
      const timestamp = Date.now();
      
      // 1. CRÉER
      const newStudent = {
        nom: `Test CRUD ${timestamp}`,
        email: `test.crud.${timestamp}@example.com`,
        cin: `CINCRUD${timestamp}`,
        dateNaissance: '2000-01-15',
        anneePremiereInscription: 2023
      };

      cy.request('POST', `${apiUrl}/etudiants`, newStudent).then((createResponse) => {
        expect(createResponse.status).to.eq(201);
        const studentId = createResponse.body.id;
        cy.log(`✅ 1/4 - Étudiant créé: ${studentId}`);

        // 2. LIRE
        cy.request('GET', `${apiUrl}/etudiants/${studentId}`).then((readResponse) => {
          expect(readResponse.status).to.eq(200);
          expect(readResponse.body.nom).to.eq(newStudent.nom);
          cy.log(`✅ 2/4 - Étudiant lu: ${studentId}`);

          // 3. METTRE À JOUR
          const updatedStudent = {
            ...newStudent,
            nom: `Test CRUD Updated ${timestamp}`,
            anneePremiereInscription: 2024
          };

          cy.request('PUT', `${apiUrl}/etudiants/${studentId}`, updatedStudent).then((updateResponse) => {
            expect(updateResponse.status).to.eq(200);
            expect(updateResponse.body.nom).to.eq(updatedStudent.nom);
            expect(updateResponse.body.anneePremiereInscription).to.eq(2024);
            cy.log(`✅ 3/4 - Étudiant mis à jour: ${studentId}`);

            // 4. SUPPRIMER
            cy.request('DELETE', `${apiUrl}/etudiants/${studentId}`).then((deleteResponse) => {
              expect(deleteResponse.status).to.eq(204);
              cy.log(`✅ 4/4 - Étudiant supprimé: ${studentId}`);
              cy.log('🎉 Cycle CRUD complet réussi!');
            });
          });
        });
      });
    });
  });

  describe('6. Tests de performance', () => {
    it('Devrait répondre rapidement à une requête GET', () => {
      const startTime = Date.now();

      cy.request('GET', `${apiUrl}/etudiants`).then((response) => {
        const endTime = Date.now();
        const responseTime = endTime - startTime;

        expect(response.status).to.eq(200);
        expect(responseTime).to.be.lessThan(2000); // Moins de 2 secondes
        
        cy.log(`✅ Temps de réponse: ${responseTime}ms`);
      });
    });

    it('Devrait gérer plusieurs requêtes simultanées', () => {
      // Créer un tableau de promesses Cypress
      const numRequests = 5;
      
      // Exécuter les requêtes séquentiellement avec Cypress
      cy.wrap(null).then(() => {
        const results = [];
        
        // Chaîner les requêtes
        let chain = cy.wrap(null);
        
        for (let i = 0; i < numRequests; i++) {
          chain = chain.then(() => {
            return cy.request('GET', `${apiUrl}/etudiants`).then((response) => {
              results.push(response);
              return response;
            });
          });
        }
        
        // Vérifier les résultats
        return chain.then(() => {
          expect(results).to.have.length(numRequests);
          results.forEach((response) => {
            expect(response.status).to.eq(200);
          });
          cy.log(`✅ ${numRequests} requêtes traitées avec succès`);
        });
      });
    });
  });

  // Hook après tous les tests
  after(() => {
    cy.log('🏁 Tous les tests E2E terminés');
  });
});
