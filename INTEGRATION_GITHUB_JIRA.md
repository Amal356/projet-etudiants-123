# 🔗 Intégration GitHub ↔ Jira

**Date d'installation** : 10 mai 2026  
**Status** : ✅ Opérationnelle  
**Application** : GitHub for Jira (Atlassian)

---

## 📋 Configuration

### Connexion établie

- **Organisation GitHub** : Amal356
- **Dépôt connecté** : projet-etudiants-123
- **Type d'accès** : Only select repositories
- **Permissions** : FULL ACCESS
- **Backfill status** : FINISHED (depuis 13/11/2025)

### Instance Jira

- **URL** : https://amalyousef356.atlassian.net
- **Projet** : PROJ
- **Application installée** : GitHub for Jira

---

## 🎯 Conventions de nommage

Pour que l'intégration fonctionne correctement, nous utilisons les conventions suivantes :

### Branches

**Format** : `feature/PROJ-XX-description-courte`

**Exemples** :
```bash
feature/PROJ-29-branche-version-4
feature/PROJ-30-tests-unitaires
feature/PROJ-31-tests-integration
feature/PROJ-32-tests-e2e-cypress
feature/PROJ-33-tests-stress-gatling
feature/PROJ-34-integration-github-jira
feature/PROJ-35-integration-xray
feature/PROJ-36-auth-service
```

### Commits

**Format** : `PROJ-XX: Description du changement`

**Exemples** :
```bash
PROJ-30: Ajout des tests unitaires avec JUnit 5 et Mockito
PROJ-31: Configuration Testcontainers pour PostgreSQL
PROJ-32: Implémentation des tests E2E avec Cypress
PROJ-33: Configuration Gatling pour tests de stress
PROJ-34: Installation et configuration GitHub for Jira
PROJ-36: Création du service d'authentification JWT
```

### Pull Requests

**Format du titre** : `PROJ-XX: Titre descriptif`

**Exemple** :
```
Titre : PROJ-34: Intégration GitHub ↔ Jira pour traçabilité complète

Description :
Cette PR configure l'intégration entre GitHub et Jira pour assurer une traçabilité complète.

## Changements
- ✅ Installation de l'application "GitHub for Jira"
- ✅ Connexion du dépôt Amal356/projet-etudiants-123
- ✅ Configuration des permissions (FULL ACCESS)
- ✅ Documentation des conventions de nommage
- ✅ Backfill des données historiques

## Bénéfices
- Visibilité des commits dans les tickets Jira
- Visibilité des branches dans les tickets Jira
- Liens automatiques entre PRs et tickets
- Traçabilité complète du développement

## Lié à
- Jira : PROJ-34
- Sprint : Sprint 4
- Partie : Partie 4 - Question 4

## Checklist
- [x] Application installée
- [x] Dépôt connecté
- [x] Conventions documentées
- [x] Tests effectués
```

---

## ✅ Vérification de l'intégration

### Dans Jira

Pour vérifier qu'un commit/branche/PR est lié à un ticket :

1. **Ouvrir un ticket Jira** (ex: PROJ-34)
2. **Aller dans l'onglet "Development"**
3. **Vérifier la présence de** :
   - Branches liées
   - Commits liés
   - Pull Requests liées

### Dans GitHub

Les commits et PRs avec références Jira affichent automatiquement :
- Un lien vers le ticket Jira
- Le statut du ticket
- Les informations du Sprint

---

## 📊 Tickets Jira du Sprint 4

Les tickets suivants ont été créés pour la Partie 4 :

| Ticket | Titre | Points | Status |
|--------|-------|--------|--------|
| PROJ-29 | Créer branche Git version-4 | 1 | ✅ Done |
| PROJ-30 | Tests unitaires JUnit + Mockito | 3 | ✅ Done |
| PROJ-31 | Tests d'intégration Testcontainers | 3 | ✅ Done |
| PROJ-32 | Tests E2E Cypress | 3 | ✅ Done |
| PROJ-33 | Tests de stress Gatling | 2 | ✅ Done |
| PROJ-34 | Intégration GitHub ↔ Jira | 2 | 🔄 In Progress |
| PROJ-35 | Intégration Xray | 5 | 📋 To Do |
| PROJ-36 | Service d'authentification | 8 | ✅ Done |

---

## 🎓 Bonnes pratiques

### Toujours référencer les tickets

❌ **Mauvais** :
```bash
git commit -m "Ajout des tests"
git checkout -b fix-bug
```

✅ **Bon** :
```bash
git commit -m "PROJ-30: Ajout des tests unitaires avec JUnit 5"
git checkout -b feature/PROJ-30-tests-unitaires
```

### Utiliser des messages descriptifs

❌ **Mauvais** :
```bash
git commit -m "PROJ-30: fix"
```

✅ **Bon** :
```bash
git commit -m "PROJ-30: Correction du test StudentService.calculateAge() pour gérer les dates futures"
```

### Lier les PRs aux tickets

Dans la description de la PR, toujours mentionner :
- Le ticket Jira concerné
- Le Sprint
- Les changements effectués
- Les tests ajoutés

---

## 🔧 Dépannage

### Les commits n'apparaissent pas dans Jira

**Solutions** :
1. Vérifier que le format est correct : `PROJ-XX: message`
2. Vérifier que le ticket existe dans Jira
3. Attendre 1-2 minutes (synchronisation)
4. Rafraîchir la page Jira

### Les branches ne sont pas visibles

**Solutions** :
1. Vérifier le format : `feature/PROJ-XX-description`
2. Pousser la branche sur GitHub : `git push origin nom-branche`
3. Vérifier dans Jira → Development

### L'intégration ne fonctionne plus

**Solutions** :
1. Jira → Apps → Manage your apps
2. Vérifier que "GitHub for Jira" est activé
3. Vérifier la connexion dans Settings
4. Reconnecter si nécessaire

---

## 📚 Ressources

- [Documentation GitHub for Jira](https://github.com/atlassian/github-for-jira)
- [Smart Commits Jira](https://support.atlassian.com/jira-software-cloud/docs/process-issues-with-smart-commits/)
- [Guide d'implémentation Q4 et Q5](./GUIDE_IMPLEMENTATION_Q4_Q5.md)
- [Rapport de conformité Partie 4](./CONFORMITE_PARTIE4.md)

---

## ✅ Résultat

L'intégration GitHub ↔ Jira est maintenant **opérationnelle** et permet :

- ✅ Traçabilité complète des commits
- ✅ Visibilité des branches dans Jira
- ✅ Liens automatiques entre PRs et tickets
- ✅ Suivi du développement dans Jira
- ✅ Conformité avec la Question 4 de la Partie 4

---

*Documentation créée le 10 mai 2026*
