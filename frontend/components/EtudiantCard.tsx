'use client'

import Link from 'next/link'

interface Etudiant {
  id: number
  cin: string
  nom: string
  dateNaissance: string
  email: string
  anneePremiereInscription: number
  departement?: {
    id: number
    nom: string
  }
}

export default function EtudiantCard({ 
  etudiant, 
  onDelete 
}: { 
  etudiant: Etudiant
  onDelete: (id: number) => void
}) {
  const calculateAge = (dateNaissance: string) => {
    const birthDate = new Date(dateNaissance)
    const today = new Date()
    let age = today.getFullYear() - birthDate.getFullYear()
    const monthDiff = today.getMonth() - birthDate.getMonth()
    if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthDate.getDate())) {
      age--
    }
    return age
  }

  return (
    <div 
      className="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition"
      data-testid="etudiant-item"
    >
      <div className="flex justify-between items-start mb-4">
        <h3 className="text-xl font-bold text-gray-800">{etudiant.nom}</h3>
        <span className="bg-primary text-white px-3 py-1 rounded-full text-sm">
          {calculateAge(etudiant.dateNaissance)} ans
        </span>
      </div>

      <div className="space-y-2 text-gray-600 mb-4">
        <p><strong>CIN:</strong> {etudiant.cin}</p>
        <p><strong>Email:</strong> {etudiant.email}</p>
        <p><strong>Date de naissance:</strong> {new Date(etudiant.dateNaissance).toLocaleDateString('fr-FR')}</p>
        <p><strong>Année d'inscription:</strong> {etudiant.anneePremiereInscription}</p>
        {etudiant.departement && (
          <p>
            <strong>Département:</strong>{' '}
            <span className="bg-secondary text-white px-2 py-1 rounded text-sm">
              {etudiant.departement.nom}
            </span>
          </p>
        )}
      </div>

      <div className="flex space-x-2">
        <Link 
          href={`/etudiants/${etudiant.id}`}
          className="flex-1 bg-blue-500 text-white text-center px-4 py-2 rounded-lg hover:bg-blue-600 transition"
        >
          ✏️ Modifier
        </Link>
        <button
          onClick={() => onDelete(etudiant.id)}
          className="flex-1 bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600 transition"
        >
          🗑️ Supprimer
        </button>
      </div>
    </div>
  )
}
