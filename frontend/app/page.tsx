import Link from 'next/link'

export default function Home() {
  return (
    <div className="max-w-4xl mx-auto">
      <div className="text-center mb-12">
        <h1 className="text-4xl font-bold text-gray-800 mb-4">
          🎓 Système de Gestion des Étudiants
        </h1>
        <p className="text-xl text-gray-600">
          Architecture Microservices avec Spring Boot, Next.js et Flutter
        </p>
      </div>

      <div className="grid md:grid-cols-2 gap-8 mb-12">
        <Link href="/etudiants">
          <div className="bg-white p-8 rounded-lg shadow-lg hover:shadow-xl transition cursor-pointer border-2 border-transparent hover:border-primary">
            <div className="text-5xl mb-4">👨‍🎓</div>
            <h2 className="text-2xl font-bold text-gray-800 mb-2">Étudiants</h2>
            <p className="text-gray-600">
              Gérer la liste des étudiants, ajouter, modifier et supprimer des étudiants
            </p>
          </div>
        </Link>

        <Link href="/departements">
          <div className="bg-white p-8 rounded-lg shadow-lg hover:shadow-xl transition cursor-pointer border-2 border-transparent hover:border-secondary">
            <div className="text-5xl mb-4">🏢</div>
            <h2 className="text-2xl font-bold text-gray-800 mb-2">Départements</h2>
            <p className="text-gray-600">
              Gérer les départements, créer et organiser les départements académiques
            </p>
          </div>
        </Link>
      </div>

      <div className="bg-blue-50 p-6 rounded-lg">
        <h3 className="text-xl font-bold text-gray-800 mb-4">🏗️ Architecture</h3>
        <ul className="space-y-2 text-gray-700">
          <li>✅ <strong>Eureka Server</strong> - Découverte de services (Port 8761)</li>
          <li>✅ <strong>API Gateway</strong> - Point d'entrée unique (Port 8080)</li>
          <li>✅ <strong>Etudiant Service</strong> - Gestion étudiants et départements (Port 8081)</li>
          <li>✅ <strong>Grading Service</strong> - Gestion des notes (Port 8082)</li>
          <li>✅ <strong>Auth Service</strong> - Authentification JWT (Port 3001)</li>
          <li>✅ <strong>Frontend Next.js</strong> - Interface web (Port 3000)</li>
          <li>✅ <strong>Mobile Flutter</strong> - Application mobile (Port 8090)</li>
        </ul>
      </div>
    </div>
  )
}
