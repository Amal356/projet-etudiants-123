# Test d'Intégration GitHub ↔ Jira

## 🎯 Objectif
Ce fichier sert à tester l'intégration entre GitHub et Jira.

## 📝 Instructions de Test

### Étape 1 : Créer un commit avec référence Jira
```bash
git add TEST_INTEGRATION_JIRA.md
git commit -m "PROJ-30 : Test intégration GitHub Jira"
git push origin version-4
```

### Étape 2 : Vérifier dans Jira
1. Ouvrir le ticket PROJ-30 dans Jira
2. Regarder le panneau de droite
3. Chercher la section "Development" ou "Développement"
4. Vérifier si le commit apparaît

### Étape 3 : Résultat Attendu
Si l'intégration fonctionne, vous devriez voir :
- ✅ Le commit avec le message "PROJ-30 : Test intégration GitHub Jira"
- ✅ Le nom de la branche (version-4)
- ✅ Un lien vers le commit sur GitHub

## ✅ Checklist de Vérification

### Dans Jira
- [ ] Section "Development" visible dans le ticket
- [ ] Commits affichés
- [ ] Branches affichées
- [ ] Lien vers GitHub fonctionnel

### Dans GitHub
- [ ] Application "GitHub for Jira" installée
- [ ] Repository connecté à Jira
- [ ] Commits visibles dans GitHub

## 🔧 Si l'intégration ne fonctionne pas

### Solution 1 : Installer GitHub for Jira
1. Aller dans Jira
2. Cliquer sur **Apps** (en haut)
3. Cliquer sur **Find new apps**
4. Rechercher "GitHub for Jira"
5. Cliquer sur **Install**
6. Suivre les instructions pour connecter votre compte GitHub

### Solution 2 : Vérifier la connexion
1. Dans Jira, aller dans **Apps** > **Manage your apps**
2. Trouver "GitHub for Jira"
3. Cliquer sur **Configure**
4. Vérifier que votre repository est bien connecté

### Solution 3 : Reconnecter le repository
1. Dans la configuration de GitHub for Jira
2. Cliquer sur **Add organization** ou **Add repository**
3. Sélectionner votre repository
4. Autoriser l'accès

## 📊 Convention de Nommage (Rappel)

Pour que Jira détecte automatiquement les liens :

### Commits
```bash
git commit -m "PROJ-XX : Description du changement"
```

### Branches
```bash
git checkout -b feature/PROJ-XX-description
```

### Pull Requests
```
Titre : PROJ-XX : Description de la fonctionnalité
```

## 🎯 Exemple Complet

```bash
# 1. Créer une branche avec référence Jira
git checkout -b feature/PROJ-30-test-integration

# 2. Faire des modifications
echo "Test" > test.txt

# 3. Commit avec référence Jira
git add test.txt
git commit -m "PROJ-30 : Ajout fichier de test pour vérifier intégration"

# 4. Push
git push origin feature/PROJ-30-test-integration

# 5. Créer une Pull Request avec titre
# "PROJ-30 : Test intégration GitHub Jira"
```

## ✅ Résultat Attendu dans Jira

Dans le ticket PROJ-30, vous devriez voir :

```
Development
├── 1 branch
│   └── feature/PROJ-30-test-integration
├── 2 commits
│   ├── PROJ-30 : Test intégration GitHub Jira
│   └── PROJ-30 : Ajout fichier de test
└── 1 pull request
    └── PROJ-30 : Test intégration GitHub Jira
```

---

**Date de création :** 15 mai 2026  
**Ticket Jira :** PROJ-30
