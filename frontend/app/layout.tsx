import type { Metadata } from 'next'
import { Inter } from 'next/font/google'
import './globals.css'
import Link from 'next/link'

const inter = Inter({ subsets: ['latin'] })

export const metadata: Metadata = {
  title: 'Gestion des Étudiants',
  description: 'Système de gestion des étudiants et départements',
}

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {
  return (
    <html lang="fr">
      <body className={inter.className}>
        <nav className="bg-primary text-white shadow-lg">
          <div className="container mx-auto px-4">
            <div className="flex items-center justify-between h-16">
              <Link href="/" className="text-xl font-bold">
                🎓 Gestion Étudiants
              </Link>
              <div className="flex space-x-4">
                <Link 
                  href="/etudiants" 
                  className="hover:bg-blue-600 px-3 py-2 rounded-md transition"
                >
                  Étudiants
                </Link>
                <Link 
                  href="/departements" 
                  className="hover:bg-blue-600 px-3 py-2 rounded-md transition"
                >
                  Départements
                </Link>
              </div>
            </div>
          </div>
        </nav>
        <main className="container mx-auto px-4 py-8">
          {children}
        </main>
        <footer className="bg-gray-100 mt-12 py-6 text-center text-gray-600">
          <p>© 2026 Système de Gestion des Étudiants - Architecture Microservices</p>
        </footer>
      </body>
    </html>
  )
}
