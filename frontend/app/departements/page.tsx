import DepartementList from '@/components/DepartementList'

const API_URL = process.env.API_GATEWAY_URL || 'http://localhost:8080'

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

export default async function DepartementsPage() {
  const departements = await getDepartements()

  return (
    <div>
      <h1 className="text-3xl font-bold text-gray-800 mb-8">🏢 Gestion des Départements</h1>
      <DepartementList initialDepartements={departements} />
    </div>
  )
}
