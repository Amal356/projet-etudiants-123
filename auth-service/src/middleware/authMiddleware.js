const jwt = require('jsonwebtoken');
const User = require('../models/User');

/**
 * Middleware pour protéger les routes avec JWT
 */
const protect = async (req, res, next) => {
  let token;

  // Vérifier si le token est présent dans les headers
  if (
    req.headers.authorization &&
    req.headers.authorization.startsWith('Bearer')
  ) {
    try {
      // Extraire le token
      token = req.headers.authorization.split(' ')[1];

      // Vérifier et décoder le token
      const decoded = jwt.verify(token, process.env.JWT_SECRET);

      // Récupérer l'utilisateur (sans le mot de passe)
      req.user = await User.findById(decoded.id).select('-password');

      if (!req.user) {
        return res.status(401).json({
          success: false,
          message: 'Utilisateur non trouvé',
        });
      }

      if (!req.user.isActive) {
        return res.status(401).json({
          success: false,
          message: 'Compte utilisateur désactivé',
        });
      }

      next();
    } catch (error) {
      console.error('Erreur de vérification du token:', error.message);
      return res.status(401).json({
        success: false,
        message: 'Token invalide ou expiré',
      });
    }
  } else {
    return res.status(401).json({
      success: false,
      message: 'Accès non autorisé, token manquant',
    });
  }
};

/**
 * Middleware pour vérifier le rôle admin
 */
const admin = (req, res, next) => {
  if (req.user && req.user.role === 'admin') {
    next();
  } else {
    res.status(403).json({
      success: false,
      message: 'Accès refusé: droits administrateur requis',
    });
  }
};

module.exports = { protect, admin };
