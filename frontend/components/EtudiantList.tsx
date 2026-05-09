'use client'

import { useState } from 'react'
import Link from 'next/link'
import EtudiantCard from './EtudiantCard'

const API_URL = process.env.NEXT_PUBLIC_API_GATEWAY_URL || 'http://localhost:8080'

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

interface Departement {
  id: number
  nom: string
}

export default function EtudiantList({ 
  initialEtudiants, 
  departements 
}: { 
  initialEtudiants: Etudiant[]
  departements: Departement[]
}) {
  const [etudiants, setEtudiants] = useState<Etudiant[]>(initialEtudiants)
  const [filtreAnnee, setFiltreAnnee] = useState<string>('')
  const [filtreDepartement, setFiltreDepartement] = useState<string>('')
  const [loading, setLoading] = useState(false)

  const handleDelete = async (id: number) => {
    if (!confirm('Êtes-vous sûr de vouloir supprimer cet étudiant ?')) return

    try {
      const res = await fetch(`${API_URL}/api/etudiants/${id}`, {
        method: 'DELETE',
      })

      if (res.ok) {
        setEtudiants(etudiants.filter(e => e.id !== id))
        alert('Étudiant supprimé avec succès')
      } else {
        alert('Erreur lors de la suppression')
      }
    } catch (error) {
      console.error('Erreur:', error)
      alert('Erreur lors de la suppression')
    }
  }

  const handleFilter = async () => {
    setLoading(true)
    try {
      let url = `${API_URL}/api/etudiants`
      const params = new URLSearchParams()
      
      if (filtreAnnee) {
        params.append('annee', filtreAnnee)
      }

      if (params.toString()) {
        url += `?${params.toString()}`
      }

      const res = await fetch(url)
      if (res.ok) {
        let data = await res.json()
        
        // Filtre côté client pour le département
        if (filtreDepartement) {
          data = data.filter((e: any) => 
       e.departementId === parseInt(filtreDepartement)
          )
        }
        
        setEtudiants(data)
      }
    } catch (error) {
      console.error('Erreur lors du filtrage:', error)
    } finally {
      setLoading(false)
    }
  }

  const handleReset = async () => {
    setFiltreAnnee('')
    setFiltreDepartement('')
    setLoading(true)
    try {
      const res = await fetch(`${API_URL}/api/etudiants`)
      if (res.ok) {
        const data = await res.json()
        setEtudiants(data)
      }
    } catch (error) {
      console.error('Erreur:', error)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div>
      {/* Filtres */}
      <div className="bg-white p-6 rounded-lg shadow-md mb-6">
        <h3 className="text-lg font-semibold mb-4">🔍 Filtres</h3>
        <div className="grid md:grid-cols-3 gap-4">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Année d'inscription
            </label>
            <input
              type="number"
              value={filtreAnnee}
              onChange={(e) => setFiltreAnnee(e.target.value)}
              placeholder="Ex: 2022"
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary focus:border-transparent"
            />
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Département
            </label>
            <select
              value={filtreDepartement}
              onChange={(e) => setFiltreDepartement(e.target.value)}
              className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary focus:border-transparent"
            >
              <option value="">Tous les départements</option>
              {departements.map(dept => (
                <option key={dept.id} value={dept.id}>
                  {dept.nom}
                </option>
              ))}
            </select>
          </div>

          <div className="flex items-end space-x-2">
            <button
              onClick={handleFilter}
              disabled={loading}
              className="flex-1 bg-primary text-white px-4 py-2 rounded-lg hover:bg-blue-600 transition disabled:opacity-50"
            >
              {loading ? 'Chargement...' : 'Filtrer'}
            </button>
            <button
              onClick={handleReset}
              disabled={loading}
              className="flex-1 bg-gray-500 text-white px-4 py-2 rounded-lg hover:bg-gray-600 transition disabled:opacity-50"
            >
              Réinitialiser
            </button>
          </div>
        </div>
      </div>

      {/* Liste des étudiants */}
      {etudiants.length === 0 ? (
        <div className="text-center py-12 bg-white rounded-lg shadow-md">
          <p className="text-gray-500 text-lg">Aucun étudiant trouvé</p>
        </div>
      ) : (
        <div className="grid md:grid-cols-2 lg:grid-cols-3 gap-6">
          {etudiants.map(etudiant => (
            <EtudiantCard 
              key={etudiant.id} 
              etudiant={etudiant} 
              onDelete={handleDelete}
            />
          ))}
        </div>
      )}

      <div className="mt-6 text-center text-gray-600">
        Total: {etudiants.length} étudiant(s)
      </div>
    </div>
  )
}
