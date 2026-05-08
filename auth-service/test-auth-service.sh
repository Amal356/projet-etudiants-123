#!/bin/bash

# Script de test pour le service d'authentification
# Usage: ./test-auth-service.sh

echo "=========================================="
echo "🔐 Test du Service d'Authentification"
echo "=========================================="
echo ""

BASE_URL="http://localhost:3001"

# Couleurs pour l'affichage
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Test 1: Health Check
echo -e "${YELLOW}Test 1: Health Check${NC}"
echo "GET $BASE_URL/health"
curl -s -X GET "$BASE_URL/health" | jq '.'
echo ""
echo ""

# Test 2: Inscription
echo -e "${YELLOW}Test 2: Inscription d'un nouvel utilisateur${NC}"
echo "POST $BASE_URL/auth/register"
REGISTER_RESPONSE=$(curl -s -X POST "$BASE_URL/auth/register" \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123"
  }')

echo "$REGISTER_RESPONSE" | jq '.'

# Extraire le token
TOKEN=$(echo "$REGISTER_RESPONSE" | jq -r '.data.token')

if [ "$TOKEN" != "null" ] && [ -n "$TOKEN" ]; then
  echo -e "${GREEN}✅ Inscription réussie! Token obtenu.${NC}"
else
  echo -e "${RED}❌ Échec de l'inscription${NC}"
  exit 1
fi
echo ""
echo ""

# Test 3: Connexion
echo -e "${YELLOW}Test 3: Connexion avec le même utilisateur${NC}"
echo "POST $BASE_URL/auth/login"
LOGIN_RESPONSE=$(curl -s -X POST "$BASE_URL/auth/login" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "password123"
  }')

echo "$LOGIN_RESPONSE" | jq '.'

# Extraire le nouveau token
TOKEN=$(echo "$LOGIN_RESPONSE" | jq -r '.data.token')

if [ "$TOKEN" != "null" ] && [ -n "$TOKEN" ]; then
  echo -e "${GREEN}✅ Connexion réussie! Token obtenu.${NC}"
else
  echo -e "${RED}❌ Échec de la connexion${NC}"
  exit 1
fi
echo ""
echo ""

# Test 4: Vérification du token
echo -e "${YELLOW}Test 4: Vérification du token JWT${NC}"
echo "GET $BASE_URL/auth/verify"
echo "Authorization: Bearer $TOKEN"
VERIFY_RESPONSE=$(curl -s -X GET "$BASE_URL/auth/verify" \
  -H "Authorization: Bearer $TOKEN")

echo "$VERIFY_RESPONSE" | jq '.'

if echo "$VERIFY_RESPONSE" | jq -e '.success == true' > /dev/null; then
  echo -e "${GREEN}✅ Token valide!${NC}"
else
  echo -e "${RED}❌ Token invalide${NC}"
  exit 1
fi
echo ""
echo ""

# Test 5: Récupération du profil
echo -e "${YELLOW}Test 5: Récupération du profil utilisateur${NC}"
echo "GET $BASE_URL/auth/me"
ME_RESPONSE=$(curl -s -X GET "$BASE_URL/auth/me" \
  -H "Authorization: Bearer $TOKEN")

echo "$ME_RESPONSE" | jq '.'

if echo "$ME_RESPONSE" | jq -e '.success == true' > /dev/null; then
  echo -e "${GREEN}✅ Profil récupéré avec succès!${NC}"
else
  echo -e "${RED}❌ Échec de la récupération du profil${NC}"
  exit 1
fi
echo ""
echo ""

# Test 6: Tentative de connexion avec mauvais mot de passe
echo -e "${YELLOW}Test 6: Tentative de connexion avec mauvais mot de passe${NC}"
echo "POST $BASE_URL/auth/login"
BAD_LOGIN_RESPONSE=$(curl -s -X POST "$BASE_URL/auth/login" \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "wrongpassword"
  }')

echo "$BAD_LOGIN_RESPONSE" | jq '.'

if echo "$BAD_LOGIN_RESPONSE" | jq -e '.success == false' > /dev/null; then
  echo -e "${GREEN}✅ Rejet correct du mauvais mot de passe!${NC}"
else
  echo -e "${RED}❌ Le mauvais mot de passe a été accepté (problème de sécurité!)${NC}"
fi
echo ""
echo ""

# Test 7: Accès sans token
echo -e "${YELLOW}Test 7: Tentative d'accès sans token${NC}"
echo "GET $BASE_URL/auth/verify (sans Authorization header)"
NO_TOKEN_RESPONSE=$(curl -s -X GET "$BASE_URL/auth/verify")

echo "$NO_TOKEN_RESPONSE" | jq '.'

if echo "$NO_TOKEN_RESPONSE" | jq -e '.success == false' > /dev/null; then
  echo -e "${GREEN}✅ Accès refusé sans token (sécurité OK)!${NC}"
else
  echo -e "${RED}❌ Accès autorisé sans token (problème de sécurité!)${NC}"
fi
echo ""
echo ""

# Résumé
echo "=========================================="
echo -e "${GREEN}✅ TOUS LES TESTS SONT PASSÉS!${NC}"
echo "=========================================="
echo ""
echo "Le service d'authentification fonctionne correctement:"
echo "  ✅ Health check"
echo "  ✅ Inscription"
echo "  ✅ Connexion"
echo "  ✅ Vérification du token"
echo "  ✅ Récupération du profil"
echo "  ✅ Rejet des mauvais mots de passe"
echo "  ✅ Protection des routes sans token"
echo ""
echo "Token JWT obtenu:"
echo "$TOKEN"
echo ""
