/**
 * Tests E2E pour l'API Départements
 */

describe('API Départements - Tests E2E', () => {
  const apiUrl = Cypress.env('apiUrl');
  let createdDepartmentId;

  before(() => {
    cy.log('🚀 Démarrage des tests E2E pour l\'API Départements');
  });

  describe('1. Récupération des départements (GET)', () => {
    it('Devrait récupérer la liste de tous les départements', () => {
      cy.request({
        method: 'GET',
        url: `${apiUrl}/departements`
      }).then((response) => {
        expect(response.status).to.eq(200);
        expect(response.body).to.be.an('array');
        cy.log(`✅ ${response.body.length} département(s) trouvé(s)`);
        
        if (response.body.length > 0) {
          const dept = response.body[0];
          expect(dept).to.have.property('id');
          expect(dept).to.have.property('nom');
        }
      });
    });

    it('Devrait récupérer un département par ID', () => {
      cy.request('GET', `${apiUrl}/departements`).then((response) => {
        if (response.body.length > 0) {
          const deptId = response.body[0].id;
          
          cy.request({
            method: 'GET',
            url: `${apiUrl}/departements/${deptId}`
          }).then((getResponse) => {
            expect(getResponse.status).to.eq(200);
            expect(getResponse.body).to.have.property('id', deptId);
            expect(getResponse.body).to.have.property('nom');
            cy.log(`✅ Département ${deptId} récupéré avec succès`);
          });
        }
      });
    });

    it('Devrait retourner 404 pour un département inexistant', () => {
      cy.request({
        method: 'GET',
        url: `${apiUrl}/departements/99999`,
        failOnStatusCode: false
      }).then((response) => {
        expect(response.status).to.eq(404);
        cy.log('✅ Erreur 404 correctement retournée');
      });
    });
  });

  describe('2. Création de départements (POST)', () => {
    it('Devrait créer un nouveau département', () => {
      const timestamp = Date.now();
      const newDepartment = {
        nom: `Département Test ${timestamp}`
      };

      cy.request({
        method: 'POST',
        url: `${apiUrl}/departements`,
        body: newDepartment
      }).then((response) => {
        expect(response.status).to.eq(201);
        expect(response.body).to.have.property('id');
        expect(response.body.nom).to.eq(newDepartment.nom);
        
        createdDepartmentId = response.body.id;
        cy.log(`✅ Département créé avec ID: ${createdDepartmentId}`);
      });
    });

    it('Devrait rejeter la création avec un nom vide', () => {
      cy.request({
        method: 'POST',
        url: `${apiUrl}/departements`,
        body: { nom: '' },
        failOnStatusCode: false
      }).then((response) => {
        expect(response.status).to.be.oneOf([400, 500]);
        cy.log('✅ Validation du nom fonctionne');
      });
    });
  });

  describe('3. Mise à jour de départements (PUT)', () => {
    it('Devrait mettre à jour un département existant', () => {
      if (createdDepartmentId) {
        const updatedData = {
          nom: `Département Updated ${Date.now()}`
        };

        cy.request({
          method: 'PUT',
          url: `${apiUrl}/departements/${createdDepartmentId}`,
          body: updatedData
        }).then((response) => {
          expect(response.status).to.eq(200);
          expect(response.body.nom).to.eq(updatedData.nom);
          cy.log(`✅ Département ${createdDepartmentId} mis à jour`);
        });
      }
    });
  });

  describe('4. Suppression de départements (DELETE)', () => {
    it('Devrait supprimer un département existant', () => {
      const timestamp = Date.now();
      const deptToDelete = {
        nom: `Département Delete ${timestamp}`
      };

      cy.request('POST', `${apiUrl}/departements`, deptToDelete).then((createResponse) => {
        const deptId = createResponse.body.id;

        cy.request({
          method: 'DELETE',
          url: `${apiUrl}/departements/${deptId}`
        }).then((deleteResponse) => {
          expect(deleteResponse.status).to.eq(204);
          cy.log(`✅ Département ${deptId} supprimé`);
        });
      });
    });
  });

  after(() => {
    cy.log('🏁 Tests départements terminés');
  });
});
