'use client'

import { useState } from 'react'
import DepartementForm from './DepartementForm'

const API_URL = process.env.NEXT_PUBLIC_API_GATEWAY_URL || 'http://localhost:8080'

interface Departement {
  id: number
  nom: string
}

export default function DepartementList({ 
  initialDepartements 
}: { 
  initialDepartements: Departement[]
}) {
  const [departements, setDepartements] = useState<Departement[]>(initialDepartements)
  const [showForm, setShowForm] = useState(false)
  const [editingDepartement, setEditingDepartement] = useState<Departement | null>(null)

  const handleDelete = async (id: number) => {
    if (!confirm('Êtes-vous sûr de vouloir supprimer ce département ?')) return

    try {
      const res = await fetch(`${API_URL}/api/departements/${id}`, {
        method: 'DELETE',
      })

      if (res.ok) {
        setDepartements(departements.filter(d => d.id !== id))
        alert('Département supprimé avec succès')
      } else {
        alert('Erreur lors de la suppression')
      }
    } catch (error) {
      console.error('Erreur:', error)
      alert('Erreur lors de la suppression')
    }
  }

  const handleEdit = (departement: Departement) => {
    setEditingDepartement(departement)
    setShowForm(true)
  }

  const handleFormClose = () => {
    setShowForm(false)
    setEditingDepartement(null)
  }

  const handleFormSuccess = async () => {
    // Recharger la liste
    try {
      const res = await fetch(`${API_URL}/api/departements`)
      if (res.ok) {
        const data = await res.json()
        setDepartements(data)
      }
    } catch (error) {
      console.error('Erreur:', error)
    }
    handleFormClose()
  }

  return (
    <div>
      <div className="mb-6">
        <button
          onClick={() => setShowForm(true)}
          className="bg-primary text-white px-6 py-3 rounded-lg hover:bg-blue-600 transition font-semibold"
        >
          ➕ Nouveau Département
        </button>
      </div>

      {showForm && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4">
          <div className="bg-white rounded-lg p-8 max-w-md w-full">
            <h2 className="text-2xl font-bold mb-6">
              {editingDepartement ? '✏️ Modifier Département' : '➕ Nouveau Département'}
            </h2>
            <DepartementForm 
              departement={editingDepartement || undefined}
              onSuccess={handleFormSuccess}
              onCancel={handleFormClose}
            />
          </div>
        </div>
      )}

      {departements.length === 0 ? (
        <div className="text-center py-12 bg-white rounded-lg shadow-md">
          <p className="text-gray-500 text-lg">Aucun département trouvé</p>
        </div>
      ) : (
        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
          {departements.map(departement => (
            <div 
              key={departement.id}
              className="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition"
            >
              <div className="flex items-center justify-between mb-4">
                <h3 className="text-xl font-bold text-gray-800">{departement.nom}</h3>
                <span className="text-3xl">🏢</span>
              </div>

              <div className="text-sm text-gray-600 mb-4">
                ID: {departement.id}
              </div>

              <div className="flex space-x-2">
                <button
                  onClick={() => handleEdit(departement)}
                  className="flex-1 bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600 transition"
                >
                  ✏️ Modifier
                </button>
                <button
                  onClick={() => handleDelete(departement.id)}
                  className="flex-1 bg-red-500 text-white px-4 py-2 rounded-lg hover:bg-red-600 transition"
                >
                  🗑️ Supprimer
                </button>
              </div>
            </div>
          ))}
        </div>
      )}

      <div className="mt-6 text-center text-gray-600">
        Total: {departements.length} département(s)
      </div>
    </div>
  )
}
