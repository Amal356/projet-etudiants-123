import { notFound } from 'next/navigation'
import EtudiantForm from '@/components/EtudiantForm'

const API_URL = process.env.API_GATEWAY_URL || 'http://localhost:8080'

async function getEtudiant(id: string) {
  try {
    const res = await fetch(`${API_URL}/api/etudiants/${id}`, {
      cache: 'no-store',
    })
    if (!res.ok) return null
    return res.json()
  } catch (error) {
    console.error('Erreur lors de la récupération de l\'étudiant:', error)
    return null
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

export default async function EditEtudiantPage({ params }: { params: { id: string } }) {
  const etudiant = await getEtudiant(params.id)
  const departements = await getDepartements()

  if (!etudiant) {
    notFound()
  }

  return (
    <div className="max-w-2xl mx-auto">
      <h1 className="text-3xl font-bold text-gray-800 mb-8">✏️ Modifier Étudiant</h1>
      <EtudiantForm etudiant={etudiant} departements={departements} />
    </div>
  )
}
