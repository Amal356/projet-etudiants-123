# 🔗 Guide de Vérification de l'Intégration GitHub ↔ Jira

## ✅ Commit de Test Créé

**Date :** 15 mai 2026  
**Commit Hash :** `24dd45c`  
**Message :** `PROJ-35 : Test intégration GitHub Jira - Ajout documentation Sprint 4`  
**Branche :** `version-4`  
**Ticket Jira :** PROJ-35  
**Statut :** ✅ Poussé vers GitHub avec succès

---

## 📋 Étapes de Vérification

### 1️⃣ Vérifier dans Jira (MÉTHODE PRINCIPALE)

#### Option A : Via le Ticket PROJ-35
1. **Ouvrir le ticket PROJ-35** :
   - URL directe : https://amalyousef356.atlassian.net/browse/PROJ-35
   - Ou depuis le board : https://amalyousef356.atlassian.net/jira/software/projects/PROJ/boards/100

2. **Regarder le panneau de droite** du ticket

3. **Chercher la section "Development"** (ou "Développement")
   - Cette section apparaît automatiquement quand GitHub est connecté
   - Elle affiche les commits, branches et pull requests liés

4. **Vérifier les informations affichées** :
   ```
   Development
   ├── 1 commit
   │   └── PROJ-35 : Test intégration GitHub Jira - Ajout documentation Sprint 4
   │       Hash: 24dd45c
   │       Auteur: [Votre nom]
   │       Date: 15 mai 2026
   ├── 1 branch
   │   └── version-4
   └── Lien vers GitHub
       └── https://github.com/Amal356/projet-etudiants-123/commit/24dd45c
   ```

#### Option B : Via le Board Jira
1. Aller sur le board : https://amalyousef356.atlassian.net/jira/software/projects/PROJ/boards/100
2. Trouver la carte **PROJ-35**
3. Cliquer sur la carte pour ouvrir les détails
4. Vérifier la section "Development" dans le panneau de droite

---

### 2️⃣ Vérifier dans GitHub

#### Vérifier le Commit
1. **Aller sur le repository GitHub** :
   - URL : https://github.com/Amal356/projet-etudiants-123

2. **Cliquer sur l'onglet "Commits"** ou aller sur :
   - https://github.com/Amal356/projet-etudiants-123/commits/version-4

3. **Trouver le commit** `24dd45c` :
   - Message : "PROJ-35 : Test intégration GitHub Jira - Ajout documentation Sprint 4"
   - Date : 15 mai 2026

4. **Cliquer sur le commit** pour voir les détails :
   - URL directe : https://github.com/Amal356/projet-etudiants-123/commit/24dd45c

#### Vérifier l'Application GitHub for Jira
1. **Aller dans Settings du repository** :
   - https://github.com/Amal356/projet-etudiants-123/settings

2. **Cliquer sur "Integrations"** dans le menu de gauche

3. **Vérifier que "GitHub for Jira" est installé** :
   - Statut : Active
   - Connected to : amalyousef356.atlassian.net

---

### 3️⃣ Vérifier les Webhooks (Optionnel)

1. **Aller dans Settings > Webhooks** :
   - https://github.com/Amal356/projet-etudiants-123/settings/hooks

2. **Vérifier qu'un webhook Jira existe** :
   - URL : https://amalyousef356.atlassian.net/...
   - Events : Push, Pull Request, etc.
   - Status : ✅ Active

---

## ✅ Résultats Attendus

### Si l'intégration fonctionne correctement :

#### Dans Jira (Ticket PROJ-35)
- ✅ Section "Development" visible
- ✅ Commit affiché : "PROJ-35 : Test intégration GitHub Jira - Ajout documentation Sprint 4"
- ✅ Hash du commit : `24dd45c`
- ✅ Branche affichée : `version-4`
- ✅ Lien cliquable vers GitHub
- ✅ Nom de l'auteur visible
- ✅ Date du commit visible

#### Dans GitHub
- ✅ Commit visible dans l'historique
- ✅ Message du commit contient "PROJ-35"
- ✅ Application "GitHub for Jira" installée
- ✅ Webhook Jira actif

---

## ❌ Si l'intégration ne fonctionne PAS

### Symptômes
- ❌ Pas de section "Development" dans le ticket Jira
- ❌ Section "Development" vide
- ❌ Commit non affiché dans Jira

### Solutions

#### Solution 1 : Vérifier l'installation de GitHub for Jira

1. **Dans Jira, aller dans Apps** :
   - Cliquer sur **Apps** (en haut de la page)
   - Cliquer sur **Manage your apps**

2. **Chercher "GitHub for Jira"** :
   - Si absent : Cliquer sur **Find new apps** et installer
   - Si présent : Vérifier qu'il est activé

3. **Configurer l'application** :
   - Cliquer sur **Configure** à côté de "GitHub for Jira"
   - Vérifier que votre repository est connecté

#### Solution 2 : Reconnecter le Repository

1. **Dans la configuration de GitHub for Jira** :
   - Cliquer sur **Add organization** ou **Add repository**
   - Sélectionner votre compte GitHub : `Amal356`
   - Sélectionner le repository : `projet-etudiants-123`
   - Autoriser l'accès

2. **Vérifier les permissions** :
   - L'application doit avoir accès en lecture aux commits
   - L'application doit avoir accès aux webhooks

#### Solution 3 : Vérifier la Convention de Nommage

Les commits doivent suivre ce format pour être détectés :
```
PROJ-XX : Description du changement
```

**Exemples valides :**
- ✅ `PROJ-35 : Test intégration GitHub Jira`
- ✅ `PROJ-30 : Ajout tests unitaires`
- ✅ `PROJ-29 : Création branche version-4`

**Exemples invalides :**
- ❌ `Test intégration PROJ-35` (référence à la fin)
- ❌ `[PROJ-35] Test intégration` (mauvais format)
- ❌ `proj-35 : Test intégration` (minuscules)

#### Solution 4 : Forcer la Synchronisation

1. **Dans Jira, ouvrir le ticket PROJ-35**
2. **Cliquer sur "..." (menu)** en haut à droite
3. **Chercher une option "Refresh" ou "Sync"**
4. Attendre quelques minutes et rafraîchir la page

#### Solution 5 : Créer un Nouveau Commit de Test

Si le premier commit n'apparaît pas, créez-en un nouveau :

```bash
# Créer un fichier de test
echo "Test intégration Jira" > test-jira.txt

# Commit avec référence Jira
git add test-jira.txt
git commit -m "PROJ-35 : Second test intégration GitHub Jira"

# Push
git push origin version-4
```

Puis vérifier à nouveau dans Jira après 2-3 minutes.

---

## 📊 Convention de Nommage Complète

Pour que Jira détecte automatiquement les liens avec GitHub :

### Commits
```bash
git commit -m "PROJ-XX : Description du changement"
```

**Exemples :**
```bash
git commit -m "PROJ-30 : Ajout tests unitaires JUnit"
git commit -m "PROJ-31 : Configuration Testcontainers"
git commit -m "PROJ-35 : Test intégration GitHub Jira"
```

### Branches
```bash
git checkout -b feature/PROJ-XX-description
```

**Exemples :**
```bash
git checkout -b feature/PROJ-30-tests-unitaires
git checkout -b feature/PROJ-35-integration-jira
git checkout -b bugfix/PROJ-40-fix-api
```

### Pull Requests
**Titre :**
```
PROJ-XX : Description de la fonctionnalité
```

**Exemples :**
```
PROJ-30 : Ajout tests unitaires avec JUnit et Mockito
PROJ-35 : Configuration intégration GitHub Jira
PROJ-37 : Implémentation service authentification JWT
```

---

## 🎯 Exemple Complet de Workflow

### Scénario : Travailler sur PROJ-30 (Tests Unitaires)

```bash
# 1. Créer une branche avec référence Jira
git checkout version-4
git pull origin version-4
git checkout -b feature/PROJ-30-tests-unitaires

# 2. Faire des modifications
# ... éditer les fichiers de tests ...

# 3. Commit avec référence Jira
git add src/test/java/com/studentmanagement/service/StudentServiceTest.java
git commit -m "PROJ-30 : Ajout tests unitaires pour StudentService"

# 4. Push
git push origin feature/PROJ-30-tests-unitaires

# 5. Créer une Pull Request sur GitHub
# Titre : "PROJ-30 : Ajout tests unitaires avec JUnit et Mockito"
# Description : "Implémentation des tests unitaires pour le service étudiant"

# 6. Vérifier dans Jira (ticket PROJ-30)
# - Section Development doit afficher :
#   - 1 branch : feature/PROJ-30-tests-unitaires
#   - 1 commit : PROJ-30 : Ajout tests unitaires pour StudentService
#   - 1 pull request : PROJ-30 : Ajout tests unitaires avec JUnit et Mockito
```

---

## 📈 Avantages de l'Intégration

### Traçabilité
- ✅ Voir tous les commits liés à une User Story
- ✅ Suivre l'avancement du développement
- ✅ Lien direct entre code et ticket

### Collaboration
- ✅ Toute l'équipe voit les changements
- ✅ Facilite les code reviews
- ✅ Améliore la communication

### Reporting
- ✅ Rapports automatiques de progression
- ✅ Statistiques de développement
- ✅ Historique complet

---

## 🔍 Vérification Finale

### Checklist de Vérification

#### Dans Jira (Ticket PROJ-35)
- [ ] Section "Development" visible
- [ ] Commit `24dd45c` affiché
- [ ] Message du commit correct
- [ ] Branche `version-4` affichée
- [ ] Lien vers GitHub fonctionnel
- [ ] Nom de l'auteur visible
- [ ] Date du commit visible

#### Dans GitHub
- [ ] Commit visible dans l'historique
- [ ] Message contient "PROJ-35"
- [ ] Application "GitHub for Jira" installée
- [ ] Webhook Jira actif
- [ ] Repository connecté à Jira

#### Test Fonctionnel
- [ ] Créer un nouveau commit avec "PROJ-35"
- [ ] Vérifier qu'il apparaît dans Jira
- [ ] Cliquer sur le lien GitHub depuis Jira
- [ ] Vérifier que le lien fonctionne

---

## 📞 Support

### Si vous avez besoin d'aide

1. **Documentation officielle** :
   - GitHub for Jira : https://github.com/atlassian/github-for-jira
   - Atlassian Docs : https://support.atlassian.com/jira-cloud-administration/docs/integrate-with-github/

2. **Vérifier les logs** :
   - Dans GitHub : Settings > Webhooks > Recent Deliveries
   - Dans Jira : Apps > Manage your apps > GitHub for Jira > View logs

3. **Contacter le support** :
   - Support Atlassian : https://support.atlassian.com
   - GitHub Support : https://support.github.com

---

## ✅ Conclusion

**Commit de test créé :** ✅  
**Hash :** `24dd45c`  
**Ticket :** PROJ-35  
**Branche :** version-4  

**Prochaine étape :**
1. Ouvrir le ticket PROJ-35 dans Jira : https://amalyousef356.atlassian.net/browse/PROJ-35
2. Vérifier la section "Development"
3. Confirmer que le commit apparaît

**Si le commit apparaît dans Jira :** ✅ L'intégration fonctionne parfaitement !  
**Si le commit n'apparaît pas :** Suivre les solutions proposées ci-dessus.

---

**Date de création :** 15 mai 2026  
**Auteur :** Kiro AI Assistant  
**Projet :** Système de Gestion des Étudiants  
**Sprint :** Sprint 4 - Tests et Qualité
