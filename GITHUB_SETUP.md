# 🚀 Configuration GitHub - Instructions

## ⚠️ IMPORTANT : Le dépôt GitHub doit être créé

Le dépôt **https://github.com/Amal366/projet-etudiants-123** n'existe pas encore ou n'est pas accessible.

---

## 📋 Étapes pour créer le dépôt GitHub

### 1. Créer le dépôt sur GitHub

1. Allez sur **https://github.com**
2. Connectez-vous avec votre compte **Amal366**
3. Cliquez sur le bouton **"New"** (ou **"+"** → **"New repository"**)
4. Remplissez les informations :
   - **Repository name** : `projet-etudiants-123`
   - **Description** : `Système de gestion des étudiants - Architecture Microservices`
   - **Visibility** : Public ou Private (selon votre choix)
   - ⚠️ **NE PAS** cocher "Add a README file"
   - ⚠️ **NE PAS** ajouter .gitignore
   - ⚠️ **NE PAS** choisir de licence
5. Cliquez sur **"Create repository"**

---

### 2. Pousser le code vers GitHub

Une fois le dépôt créé, exécutez ces commandes dans votre terminal :

```bash
# Vérifier que vous êtes dans le bon dossier
cd C:\Users\amaly\Desktop\projet-etudiants

# Vérifier l'état actuel
git status
git branch -a

# Pousser la branche main
git checkout main
git push -u origin main

# Pousser la branche version-2
git checkout version-2
git push -u origin version-2

# Pousser la branche version-3
git checkout version-3
git push -u origin version-3

# Pousser la branche version-4
git checkout version-4
git push -u origin version-4
```

---

## ✅ Structure des branches créées

Voici la structure de branches qui a été créée localement :

```
main (branche principale)
 └── version-2 (Partie 2)
      └── version-3 (Partie 3)
           └── version-4 (Partie 4)
```

**Branches disponibles :**
- ✅ `main` - Branche principale avec toute la structure
- ✅ `version-2` - Branche pour la Partie 2
- ✅ `version-3` - Branche pour la Partie 3
- ✅ `version-4` - Branche pour la Partie 4

---

## 📁 Structure du projet

```
/projet-etudiants/
├── api-gateway/              # API Gateway (Port 8888)
├── auth-service/             # Service d'authentification (Node.js)
├── cypress-tests/            # Tests E2E Cypress
├── eureka-server/            # Serveur Eureka (Port 8761)
├── grading-service/          # Service de gestion des notes (Port 8081)
├── k8s/                      # Manifests Kubernetes
├── spring-boot-api/          # Service Etudiant (Port 8080)
├── mobile_app/               # Application mobile Flutter
├── frontend/                 # Frontend Next.js (Port 3000)
├── docs/                     # Documentation et captures d'écran
├── docker-compose.yml        # Orchestration Docker
├── README.md                 # Documentation principale
├── .gitignore                # Fichiers à ignorer
├── GUIDE_JIRA_COMPLET.md     # Guide Jira complet (36 stories)
├── DEMARRAGE_JIRA.md         # Guide rapide Jira
└── CONTRIBUTING.md           # Guide de contribution
```

---

## 🔐 Authentification GitHub

Si GitHub vous demande de vous authentifier :

### Option 1 : Personal Access Token (Recommandé)

1. Allez sur **GitHub** → **Settings** → **Developer settings** → **Personal access tokens** → **Tokens (classic)**
2. Cliquez sur **"Generate new token"** → **"Generate new token (classic)"**
3. Donnez un nom : `projet-etudiants-token`
4. Cochez les permissions :
   - ✅ `repo` (Full control of private repositories)
5. Cliquez sur **"Generate token"**
6. **COPIEZ LE TOKEN** (vous ne pourrez plus le voir après)
7. Utilisez ce token comme mot de passe lors du `git push`

### Option 2 : GitHub CLI

```bash
# Installer GitHub CLI
winget install --id GitHub.cli

# S'authentifier
gh auth login

# Suivre les instructions
```

---

## 📊 Vérification après push

Une fois les branches poussées, vérifiez sur GitHub :

1. Allez sur **https://github.com/Amal366/projet-etudiants-123**
2. Vérifiez que vous voyez **4 branches** :
   - `main`
   - `version-2`
   - `version-3`
   - `version-4`
3. Vérifiez que tous les fichiers sont présents
4. Vérifiez que le README.md s'affiche correctement

---

## 🔗 Intégration GitHub ↔ Jira (PROJ-34)

Une fois le dépôt créé et poussé :

1. Allez sur **Jira** → **Settings** → **Apps**
2. Cherchez **"GitHub for Jira"**
3. Installez l'application
4. Connectez votre dépôt **projet-etudiants-123**
5. Vérifiez que les commits avec `PROJ-XX` sont liés automatiquement

---

## 📝 Conventions Git

### Branches
```bash
feature/PROJ-XX-description
bugfix/PROJ-XX-description
```

### Commits
```bash
git commit -m "PROJ-XX : description du changement"
```

**Exemples :**
```bash
git commit -m "PROJ-1 : création entité Etudiant"
git commit -m "PROJ-2 : création endpoint GET /api/etudiants"
git commit -m "PROJ-8 : ajout méthode age()"
```

---

## ❓ Problèmes courants

### Erreur : "Repository not found"
- Le dépôt n'existe pas encore sur GitHub
- Créez-le d'abord sur https://github.com/new

### Erreur : "Authentication failed"
- Utilisez un Personal Access Token au lieu du mot de passe
- Ou utilisez GitHub CLI (`gh auth login`)

### Erreur : "Permission denied"
- Vérifiez que vous êtes connecté avec le bon compte (Amal366)
- Vérifiez que vous avez les droits sur le dépôt

---

## 📚 Documentation

- **[README.md](./README.md)** - Documentation principale du projet
- **[GUIDE_JIRA_COMPLET.md](./GUIDE_JIRA_COMPLET.md)** - Guide Jira complet (36 stories)
- **[CONTRIBUTING.md](./CONTRIBUTING.md)** - Guide de contribution
- **[PARTIE4_GITHUB_JIRA_INTEGRATION.md](./PARTIE4_GITHUB_JIRA_INTEGRATION.md)** - Intégration GitHub ↔ Jira

---

## ✅ Checklist

- [ ] Dépôt GitHub créé : `projet-etudiants-123`
- [ ] Branche `main` poussée
- [ ] Branche `version-2` poussée
- [ ] Branche `version-3` poussée
- [ ] Branche `version-4` poussée
- [ ] README.md visible sur GitHub
- [ ] .gitignore configuré
- [ ] Structure du projet correcte
- [ ] GitHub for Jira installé et connecté

---

**Date** : 9 Mai 2026  
**Auteur** : Kiro AI Assistant  
**Dépôt** : https://github.com/Amal366/projet-etudiants-123
