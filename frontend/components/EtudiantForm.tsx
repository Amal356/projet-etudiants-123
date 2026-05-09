'use client'

import { useState } from 'react'
import { useRouter } from 'next/navigation'

const API_URL = process.env.NEXT_PUBLIC_API_GATEWAY_URL || 'http://localhost:8080'

interface Etudiant {
  id?: number
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

export default function EtudiantForm({ 
  etudiant, 
  departements 
}: { 
  etudiant?: Etudiant
  departements: Departement[]
}) {
  const router = useRouter()
  const [loading, setLoading] = useState(false)
  const [formData, setFormData] = useState({
    cin: etudiant?.cin || '',
    nom: etudiant?.nom || '',
    dateNaissance: etudiant?.dateNaissance || '',
    email: etudiant?.email || '',
    anneePremiereInscription: etudiant?.anneePremiereInscription || new Date().getFullYear(),
    departementId: etudiant?.departement?.id || '',
  })

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault()
    setLoading(true)

    try {
      const payload = {
        cin: formData.cin,
        nom: formData.nom,
        dateNaissance: formData.dateNaissance,
        email: formData.email,
        anneePremiereInscription: parseInt(formData.anneePremiereInscription.toString()),
        departementId: formData.departementId ? parseInt(formData.departementId.toString()) : null,
      }

      const url = etudiant 
        ? `${API_URL}/api/etudiants/${etudiant.id}`
        : `${API_URL}/api/etudiants`

      const method = etudiant ? 'PUT' : 'POST'

      const res = await fetch(url, {
        method,
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
      })

      if (res.ok) {
        alert(etudiant ? 'Étudiant modifié avec succès' : 'Étudiant créé avec succès')
        router.push('/etudiants')
        router.refresh()
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
    <form onSubmit={handleSubmit} className="bg-white p-8 rounded-lg shadow-md">
      <div className="space-y-6">
        <div>
          <label className="block text-sm font-medium text-gray-700 mb-2">
            CIN *
          </label>
          <input
            type="text"
            name="cin"
            required
            value={formData.cin}
            onChange={(e) => setFormData({ ...formData, cin: e.target.value })}
            className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary focus:border-transparent"
            placeholder="Ex: 12345678"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700 mb-2">
            Nom complet *
          </label>
          <input
            type="text"
            name="nom"
            required
            value={formData.nom}
            onChange={(e) => setFormData({ ...formData, nom: e.target.value })}
            className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary focus:border-transparent"
            placeholder="Ex: Jean Dupont"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700 mb-2">
            Email *
          </label>
          <input
            type="email"
            name="email"
            required
            value={formData.email}
            onChange={(e) => setFormData({ ...formData, email: e.target.value })}
            className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary focus:border-transparent"
            placeholder="Ex: jean.dupont@example.com"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700 mb-2">
            Date de naissance *
          </label>
          <input
            type="date"
            name="dateNaissance"
            required
            value={formData.dateNaissance}
            onChange={(e) => setFormData({ ...formData, dateNaissance: e.target.value })}
            className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary focus:border-transparent"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700 mb-2">
            Année de première inscription *
          </label>
          <input
            type="number"
            name="anneePremiereInscription"
            required
            min="1900"
            max="2100"
            value={formData.anneePremiereInscription}
            onChange={(e) => setFormData({ ...formData, anneePremiereInscription: parseInt(e.target.value) })}
            className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary focus:border-transparent"
            placeholder="Ex: 2022"
          />
        </div>

        <div>
          <label className="block text-sm font-medium text-gray-700 mb-2">
            Département
          </label>
          <select
            name="departementId"
            value={formData.departementId}
            onChange={(e) => setFormData({ ...formData, departementId: e.target.value })}
            className="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-primary focus:border-transparent"
          >
            <option value="">Aucun département</option>
            {departements.map(dept => (
              <option key={dept.id} value={dept.id}>
                {dept.nom}
              </option>
            ))}
          </select>
        </div>
      </div>

      <div className="flex space-x-4 mt-8">
        <button
          type="submit"
          disabled={loading}
          className="flex-1 bg-primary text-white px-6 py-3 rounded-lg hover:bg-blue-600 transition disabled:opacity-50 font-semibold"
        >
          {loading ? 'Enregistrement...' : (etudiant ? '✏️ Modifier' : '➕ Créer')}
        </button>
        <button
          type="button"
          onClick={() => router.back()}
          className="flex-1 bg-gray-500 text-white px-6 py-3 rounded-lg hover:bg-gray-600 transition font-semibold"
        >
          ❌ Annuler
        </button>
      </div>
    </form>
  )
}
