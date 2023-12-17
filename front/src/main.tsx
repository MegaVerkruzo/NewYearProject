import ReactDOM from 'react-dom/client'
import { Router } from './components/Router/Router'
import './styles/index.css'
// import eruda from 'eruda'

// const el = document.createElement('div')
// document.body.appendChild(el)

// eruda.init({
//   container: el,
//   tool: ['console', 'elements'],
// })

ReactDOM.createRoot(document.getElementById('root')!).render(<Router />)
