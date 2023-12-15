import { useLocation } from 'react-router-dom'
import { Message } from '../../components/Message.tsx/Message'
import { Tree } from '../../components/Tree/Tree'
import { WaitNextGameState } from '../../types/gameState'
import HowToPlay from '../../components/HowToPlay/HowToPlay'
import { Rules } from '../../components/Rules/Rules'

export const WaitingNextGame = () => {
  const location = useLocation() as { state: WaitNextGameState }
  const { text, activeGifts, activePrizes, nonActivePrizes } = location.state

  return (
    <div className="main-wrapper">
      <div className="main-page__row">
        <Tree
          activeGifts={activeGifts}
          activePrizes={activePrizes}
          nonActivePrizes={nonActivePrizes}
        />
      </div>
      <div className="main-page__row">
        <Message text={text} />
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
