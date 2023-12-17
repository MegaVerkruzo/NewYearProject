import { Message } from '../../components/Message.tsx/Message'
import { Rules } from '../../components/Rules/Rules'
import HowToPlay from '../../components/HowToPlay/HowToPlay'
import { useLocation } from 'react-router-dom'
import { BeforeGameState } from '../../types/gameState'
import { Tree } from '../../components/Tree/Tree'

export const BeforeGame = () => {
  const location = useLocation() as { state: BeforeGameState }
  const { text } = location.state

  return (
    <div className="main-wrapper">
      <div className="main-page__row">
        <Tree />
      </div>
      <div className="main-page__row">
        <Message text={text} />
      </div>

      <div className="main-page__row">
        <a
          href="https://telegra.ph/Novogodnyaya-viktorina-Naryadi-svoyu-yolochku-12-17"
          target="_blank"
          className="main-page__btn"
        >
          К правилам
        </a>
      </div>

      <div className="main-page__row">
        <Rules />
      </div>
      <div className="main-page__row">
        <HowToPlay />
      </div>
    </div>
  )
}
