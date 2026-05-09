import Link from 'next/link'
import EtudiantList from '@/components/EtudiantList'

const API_URL = process.env.API_GATEWAY_URL || 'http://localhost:8080'

async function getEtudiants() {
  try {
    const res = await fetch(`${API_URL}/api/etudiants`, {
      cache: 'no-store',
    })
    if (!res.ok) return []
    return res.json()
  } catch (error) {
    console.error('Erreur lors de la récupération des étudiants:', error)
    return []
  }
}

async function getDepartements() {
  try {
    const res = await fetch(`${API_URL}/api/departements`, {
      cache: 'no-store',
    })
    if (!res.ok) return []
    return res.json()
  } catch (error) {
    console.error('Erreur lors de la récupération des départements:', error)
    return []
  }
}

export default async function EtudiantsPage() {
  const etudiants = await getEtudiants()
  const departements = await getDepartements()

  return (
    <div>
      <div className="flex justify-between items-center mb-8">
        <h1 className="text-3xl font-bold text-gray-800">👨‍🎓 Liste des Étudiants</h1>
        <Link 
          href="/etudiants/nouveau"
          className="bg-primary text-white px-6 py-2 rounded-lg hover:bg-blue-600 transition"
        >
          ➕ Nouvel Étudiant
        </Link>
      </div>

      <EtudiantList 
        initialEtudiants={etudiants} 
        departements={departements}
      />
    </div>
  )
}
