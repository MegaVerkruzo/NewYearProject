import { FC } from 'react'
import { Header } from '../Header/Header'
import { Footer } from '../Footer/Footer'
import { Outlet } from 'react-router-dom'

export const App: FC = () => {
  return (
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
  )
}
