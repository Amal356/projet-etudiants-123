# 🎨 Frontend Next.js - Gestion des Étudiants

Frontend moderne développé avec **Next.js 14** (App Router) et **Tailwind CSS** pour le système de gestion des étudiants.

---

## 🚀 Fonctionnalités

### Gestion des Étudiants
- ✅ Liste complète des étudiants avec pagination
- ✅ Filtrage par année d'inscription
- ✅ Filtrage par département
- ✅ Création d'un nouvel étudiant
- ✅ Modification d'un étudiant existant
- ✅ Suppression d'un étudiant
- ✅ Calcul automatique de l'âge

### Gestion des Départements
- ✅ Liste des départements
- ✅ Création d'un nouveau département
- ✅ Modification d'un département
- ✅ Suppression d'un département

---

## 🏗️ Architecture

### Technologies
- **Next.js 14** - Framework React avec App Router
- **TypeScript** - Typage statique
- **Tailwind CSS** - Styling moderne et responsive
- **Server Components** - Pour les lectures de données
- **Client Components** - Pour les formulaires interactifs

### Structure
```
frontend/
├── app/
│   ├── layout.tsx              # Layout principal avec navigation
│   ├── page.tsx                # Page d'accueil
│   ├── globals.css             # Styles globaux
│   ├── etudiants/
│   │   ├── page.tsx            # Liste des étudiants (Server Component)
│   │   ├── nouveau/
│   │   │   └── page.tsx        # Formulaire création
│   │   └── [id]/
│   │       └── page.tsx        # Formulaire édition
│   └── departements/
│       └── page.tsx            # Gestion des départements
├── components/
│   ├── EtudiantList.tsx        # Liste avec filtres (Client Component)
│   ├── EtudiantCard.tsx        # Card d'affichage étudiant
│   ├── EtudiantForm.tsx        # Formulaire étudiant (Client Component)
│   ├── DepartementList.tsx     # Liste départements (Client Component)
│   └── DepartementForm.tsx     # Formulaire département
├── public/                     # Assets statiques
├── Dockerfile                  # Image Docker
├── next.config.js              # Configuration Next.js
├── tailwind.config.ts          # Configuration Tailwind
└── package.json                # Dépendances
```

---

## 📦 Installation

### Prérequis
- Node.js 20+
- npm ou yarn

### Installation locale
```bash
cd frontend
npm install
```

---

## 🚀 Démarrage

### Mode développement
```bash
npm run dev
```
Accès: http://localhost:3000

### Mode production
```bash
npm run build
npm start
```

### Avec Docker
```bash
docker build -t frontend:1.0 .
docker run -p 3000:3000 \
  -e API_GATEWAY_URL=http://api-gateway:8080 \
  frontend:1.0
```

### Avec Docker Compose (recommandé)
```bash
# À la racine du projet
docker-compose up frontend
```

---

## 🔧 Configuration

### Variables d'environnement

Créez un fichier `.env.local` :

```env
# URL de l'API Gateway (côté client)
NEXT_PUBLIC_API_GATEWAY_URL=http://localhost:8080

# URL de l'API Gateway (côté serveur)
API_GATEWAY_URL=http://api-gateway:8080
```

**Note** :
- `NEXT_PUBLIC_*` : Variables exposées au navigateur
- Sans préfixe : Variables serveur uniquement

---

## 🎨 Design

### Palette de couleurs
- **Primary** : `#3b82f6` (Bleu)
- **Secondary** : `#8b5cf6` (Violet)
- **Success** : `#10b981` (Vert)
- **Danger** : `#ef4444` (Rouge)

### Composants
- Cards avec shadow et hover effects
- Formulaires avec validation
- Boutons avec états (loading, disabled)
- Navigation responsive
- Filtres interactifs

---

## 📡 API Integration

Toutes les requêtes passent par l'**API Gateway** (port 8080) :

### Endpoints utilisés

**Étudiants** :
- `GET /api/etudiants` - Liste
- `GET /api/etudiants/{id}` - Détail
- `POST /api/etudiants` - Création
- `PUT /api/etudiants/{id}` - Modification
- `DELETE /api/etudiants/{id}` - Suppression
- `GET /api/etudiants?annee=2022` - Filtrage par année

**Départements** :
- `GET /api/departements` - Liste
- `GET /api/departements/{id}` - Détail
- `POST /api/departements` - Création
- `PUT /api/departements/{id}` - Modification
- `DELETE /api/departements/{id}` - Suppression

---

## 🧪 Tests

### Tests E2E avec Cypress

Les tests Cypress sont dans le dossier `cypress-tests/` à la racine :

```bash
# À la racine du projet
cd cypress-tests
npm install
npm run cypress:open
```

Tests disponibles :
- ✅ Affichage de la liste des étudiants
- ✅ Création d'un nouvel étudiant
- ✅ Modification d'un étudiant
- ✅ Suppression d'un étudiant
- ✅ Filtrage par département

---

## 🐳 Docker

### Build de l'image
```bash
docker build -t amal878/frontend:1.0 .
```

### Push vers Docker Hub
```bash
docker push amal878/frontend:1.0
```

### Variables d'environnement Docker
```yaml
environment:
  - API_GATEWAY_URL=http://api-gateway:8080
  - NEXT_PUBLIC_API_GATEWAY_URL=http://localhost:8080
```

---

## 📊 Performance

### Optimisations
- ✅ Server Components pour SSR
- ✅ Client Components uniquement pour l'interactivité
- ✅ Images optimisées avec Next.js Image
- ✅ Code splitting automatique
- ✅ Caching des requêtes API
- ✅ Build optimisé pour production

---

## 🔗 Intégration avec l'architecture

### Services connectés
```
Frontend (3000)
    ↓
API Gateway (8080)
    ↓
├── Etudiant Service (8081)
└── Grading Service (8082)
    ↓
Eureka Server (8761)
```

### Flux de données
1. **Frontend** envoie requête HTTP
2. **API Gateway** route vers le bon service
3. **Service** traite et retourne les données
4. **Frontend** affiche les résultats

---

## 📝 Conformité PDF Partie 3 - Q6

✅ **Toutes les exigences respectées** :

- [x] Projet Next.js dans `frontend/`
- [x] Pages `/etudiants` et `/departements`
- [x] Server Components pour lectures
- [x] Client Components pour formulaires
- [x] Tailwind CSS pour styling
- [x] CRUD complet via API Gateway
- [x] Dockerfile pour containerisation
- [x] Intégration docker-compose.yml

---

## 🚀 Prochaines étapes

1. **Tests E2E** : Compléter les tests Cypress
2. **Authentification** : Intégrer auth-service JWT
3. **Gestion des notes** : Ajouter page pour grading-service
4. **PWA** : Transformer en Progressive Web App
5. **i18n** : Support multilingue

---

## 📚 Documentation

- [Next.js Documentation](https://nextjs.org/docs)
- [Tailwind CSS](https://tailwindcss.com/docs)
- [TypeScript](https://www.typescriptlang.org/docs)

---

## 👥 Contribution

Voir [CONTRIBUTING.md](../CONTRIBUTING.md) à la racine du projet.

---

**Auteur** : Projet Intégration des Compétences  
**Date** : 9 Mai 2026  
**Version** : 1.0.0
