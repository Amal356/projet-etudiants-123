import EtudiantForm from '@/components/EtudiantForm'

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

export default async function NouvelEtudiantPage() {
  const departements = await getDepartements()

  return (
    <div className="max-w-2xl mx-auto">
      <h1 className="text-3xl font-bold text-gray-800 mb-8">➕ Nouvel Étudiant</h1>
      <EtudiantForm departements={departements} />
    </div>
  )
}
