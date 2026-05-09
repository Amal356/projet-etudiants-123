'use client'

import { useState } from 'react'

const API_URL = process.env.NEXT_PUBLIC_API_GATEWAY_URL || 'http://localhost:8080'

interface Departement {
  id?: number
  nom: string
}

export default function DepartementForm({ 
  departement, 
  onSuccess,
  onCancel
}: { 
  departement?: Departement
  onSuccess: () => void
  onCancel: () => void
}) {
  const [loading, setLoading] = useState(false)
  const [nom, setNom] = useState(departement?.nom || '')

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    setLoading(true)

    try {
      const url = departement 
        ? `${API_URL}/api/departements/${departement.id}`
        : `${API_URL}/api/departements`

      const method = departement ? 'PUT' : 'POST'

      const res = await fetch(url, {
        method,
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ nom }),
      })

      if (res.ok) {
        alert(departement ? 'Département modifié avec succès' : 'Département créé avec succès')
        onSuccess()
      } else {
        const error = await res.text()
        alert(`Erreur: ${error}`)
      }
    } catch (error) {
      console.error('Erreur:', error)
      alert('Erreur lors de l\'enregistrement')
    } finally {
      setLoading(false)
    }
  }

  return (
    <form onSubmit={handleSubmit}>
      <div className="mb-6">
        <label className="block text-sm font-medium text-gray-700 mb-2">
          Nom du département *
        </label>
        <input
          type="text"
          required
          value={nom}
          onChange={(e) => setNom(e.target.value)}
          className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary focus:border-transparent"
          placeholder="Ex: Informatique"
        />
      </div>

      <div className="flex space-x-4">
        <button
          type="submit"
          disabled={loading}
          className="flex-1 bg-primary text-white px-6 py-3 rounded-lg hover:bg-blue-600 transition disabled:opacity-50 font-semibold"
        >
          {loading ? 'Enregistrement...' : (departement ? '✏️ Modifier' : '➕ Créer')}
        </button>
        <button
          type="button"
          onClick={onCancel}
          className="flex-1 bg-gray-500 text-white px-6 py-3 rounded-lg hover:bg-gray-600 transition font-semibold"
        >
          ❌ Annuler
        </button>
      </div>
    </form>
  )
}
