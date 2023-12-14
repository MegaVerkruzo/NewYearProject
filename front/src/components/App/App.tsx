import { FC } from 'react'
import { Header } from '../Header/Header'
import { Footer } from '../Footer/Footer'
import { QueryClient } from '@tanstack/query-core'
import { QueryClientProvider } from '@tanstack/react-query'
import { Main } from '../Main/Main'

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
        <Main />
        <Footer />
      </div>
    </QueryClientProvider>
  )
}
