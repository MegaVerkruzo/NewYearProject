import { FC } from 'react'
import { Header } from '../Header/Header'
import { Footer } from '../Footer/Footer'
import { Outlet } from 'react-router-dom'
import { QueryClient } from '@tanstack/query-core'
import { QueryClientProvider } from '@tanstack/react-query'

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      refetchOnWindowFocus: false,
    },
  },
})

export const App: FC = () => {
  return (
    <QueryClientProvider client={queryClient}>
      <div className="wrapper">
        <Header />
        <main className="main">
          <div className="main__content">
            <div className="main-page">
              <div className="container">
                <Outlet />
              </div>
            </div>
          </div>
        </main>
        <Footer />
      </div>
    </QueryClientProvider>
  )
}
