# 🔐 Auth Service - Microservice d'Authentification

Service d'authentification basé sur Node.js, Express, MongoDB et JWT pour le projet de gestion des étudiants.

## 🎯 Fonctionnalités

- ✅ Inscription d'utilisateurs avec validation
- ✅ Connexion avec génération de JWT
- ✅ Vérification de token JWT
- ✅ Hachage sécurisé des mots de passe avec bcrypt
- ✅ Gestion des rôles (user, admin)
- ✅ Protection des routes avec middleware
- ✅ Validation des données avec Mongoose

## 🛠️ Technologies

- **Node.js** - Runtime JavaScript
- **Express** - Framework web
- **MongoDB** - Base de données NoSQL
- **Mongoose** - ODM pour MongoDB
- **JWT** - Authentification par token
- **bcrypt** - Hachage des mots de passe
- **CORS** - Gestion des requêtes cross-origin

## 📋 Prérequis

- Node.js 18+ ou 20+
- MongoDB 7+
- npm ou yarn

## 🚀 Installation

### 1. Installer les dépendances

```bash
cd auth-service
npm install
```

### 2. Configuration

Créer un fichier `.env` (déjà fourni):

```env
PORT=3001
MONGO_URI=mongodb://mongodb:27017/authdb
JWT_SECRET=votre_secret_jwt_super_securise
JWT_EXPIRES_IN=24h
NODE_ENV=development
```

### 3. Démarrage

**Mode développement:**
```bash
npm run dev
```

**Mode production:**
```bash
npm start
```

## 🐳 Docker

### Build de l'image

```bash
docker build -t auth-service .
```

### Lancement avec Docker Compose

```bash
docker-compose up auth-service mongodb
```

## 📡 API Endpoints

### 1. Health Check

```http
GET /health
```

**Réponse:**
```json
{
  "success": true,
  "message": "Auth Service is running",
  "timestamp": "2026-05-07T22:00:00.000Z",
  "uptime": 123.456
}
```

### 2. Inscription

```http
POST /auth/register
Content-Type: application/json

{
  "username": "john_doe",
  "email": "john@example.com",
  "password": "password123",
  "role": "user"
}
```

**Réponse (201):**
```json
{
  "success": true,
  "message": "Utilisateur créé avec succès",
  "data": {
    "user": {
      "id": "663f1234567890abcdef1234",
      "username": "john_doe",
      "email": "john@example.com",
      "role": "user",
      "createdAt": "2026-05-07T22:00:00.000Z"
    },
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
}
```

### 3. Connexion

```http
POST /auth/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "password123"
}
```

**Réponse (200):**
```json
{
  "success": true,
  "message": "Connexion réussie",
  "data": {
    "user": {
      "id": "663f1234567890abcdef1234",
      "username": "john_doe",
      "email": "john@example.com",
      "role": "user"
    },
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
}
```

### 4. Vérification du Token

```http
GET /auth/verify
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

**Réponse (200):**
```json
{
  "success": true,
  "message": "Token valide",
  "data": {
    "user": {
      "id": "663f1234567890abcdef1234",
      "username": "john_doe",
      "email": "john@example.com",
      "role": "user",
      "isActive": true
    }
  }
}
```

### 5. Profil Utilisateur

```http
GET /auth/me
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

**Réponse (200):**
```json
{
  "success": true,
  "data": {
    "user": {
      "id": "663f1234567890abcdef1234",
      "username": "john_doe",
      "email": "john@example.com",
      "role": "user",
      "isActive": true,
      "createdAt": "2026-05-07T22:00:00.000Z",
      "updatedAt": "2026-05-07T22:00:00.000Z"
    }
  }
}
```

## 🔒 Sécurité

### Hachage des Mots de Passe

Les mots de passe sont automatiquement hachés avec bcrypt (10 rounds de salt) avant d'être stockés dans MongoDB.

### JWT

Les tokens JWT sont signés avec un secret et expirent après 24 heures par défaut.

### Middleware de Protection

Utilisez le middleware `protect` pour protéger vos routes:

```javascript
const { protect, admin } = require('./middleware/authMiddleware');

// Route protégée (nécessite un token valide)
router.get('/protected', protect, (req, res) => {
  res.json({ user: req.user });
});

// Route admin (nécessite un token valide + rôle admin)
router.get('/admin', protect, admin, (req, res) => {
  res.json({ message: 'Admin access' });
});
```

## 📊 Modèle de Données

### User Schema

```javascript
{
  username: String (unique, 3-50 caractères),
  email: String (unique, format email),
  password: String (hashé, min 6 caractères),
  role: String (enum: ['user', 'admin']),
  isActive: Boolean (default: true),
  createdAt: Date (auto),
  updatedAt: Date (auto)
}
```

## 🧪 Tests

### Test avec cURL

**Inscription:**
```bash
curl -X POST http://localhost:3001/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123"
  }'
```

**Connexion:**
```bash
curl -X POST http://localhost:3001/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "password123"
  }'
```

**Vérification (remplacer YOUR_TOKEN):**
```bash
curl -X GET http://localhost:3001/auth/verify \
  -H "Authorization: Bearer YOUR_TOKEN"
```

## 🌐 Intégration avec les Autres Services

### Depuis le Frontend

```javascript
// Inscription
const response = await fetch('http://localhost:3001/auth/register', {
  method: 'POST',
  headers: { 'Content-Type': 'application/json' },
  body: JSON.stringify({
    username: 'john_doe',
    email: 'john@example.com',
    password: 'password123'
  })
});

const data = await response.json();
const token = data.data.token;

// Utiliser le token pour les requêtes protégées
const protectedResponse = await fetch('http://localhost:8080/api/etudiants', {
  headers: {
    'Authorization': `Bearer ${token}`
  }
});
```

### Depuis l'API Gateway

Configurer l'API Gateway pour vérifier les tokens JWT avant de router les requêtes vers les microservices.

## 📝 Variables d'Environnement

| Variable | Description | Valeur par défaut |
|----------|-------------|-------------------|
| `PORT` | Port du serveur | `3001` |
| `MONGO_URI` | URI de connexion MongoDB | `mongodb://mongodb:27017/authdb` |
| `JWT_SECRET` | Secret pour signer les JWT | (requis) |
| `JWT_EXPIRES_IN` | Durée de validité du JWT | `24h` |
| `NODE_ENV` | Environnement | `development` |

## 🐛 Débogage

### Logs

Le service affiche des logs détaillés:
- Connexion MongoDB
- Requêtes HTTP (méthode + path)
- Erreurs de validation
- Erreurs serveur

### Erreurs Courantes

**MongoDB connection failed:**
- Vérifier que MongoDB est démarré
- Vérifier l'URI de connexion dans `.env`

**Token invalide:**
- Vérifier que le token est bien formaté: `Bearer <token>`
- Vérifier que le JWT_SECRET est le même

**Validation errors:**
- Vérifier que tous les champs requis sont fournis
- Vérifier le format de l'email
- Vérifier la longueur du mot de passe (min 6 caractères)

## 📦 Structure du Projet

```
auth-service/
├── src/
│   ├── config/
│   │   └── database.js          # Connexion MongoDB
│   ├── models/
│   │   └── User.js              # Modèle Mongoose User
│   ├── routes/
│   │   └── auth.js              # Routes d'authentification
│   ├── middleware/
│   │   └── authMiddleware.js    # Middleware JWT
│   └── app.js                   # Application Express
├── .env                         # Variables d'environnement
├── .gitignore                   # Fichiers à ignorer
├── Dockerfile                   # Image Docker
├── package.json                 # Dépendances npm
└── README.md                    # Ce fichier
```

## 🚀 Prochaines Étapes

- [ ] Ajouter des tests unitaires (Jest)
- [ ] Implémenter la réinitialisation de mot de passe
- [ ] Ajouter la vérification d'email
- [ ] Implémenter le refresh token
- [ ] Ajouter rate limiting
- [ ] Implémenter la déconnexion (blacklist de tokens)

## 📄 Licence

ISC

---

**Auteur:** Projet Étudiant - Partie 4  
**Date:** Mai 2026  
**Version:** 1.0.0
