import { useLocation } from 'react-router-dom'
import { Tree } from '../../components/Tree/Tree'
import { Message } from '../../components/Message.tsx/Message'
import { Game } from '../../components/Game/Game'
import { InGameState } from '../../types/gameState'
import { Rules } from '../../components/Rules/Rules'
import HowToPlay from '../../components/HowToPlay/HowToPlay'

export const InGame = () => {
  const location = useLocation() as { state: InGameState }
  const {
    text,
    letters,
    currentLine,
    wordLength,
    activeGifts,
    activePrizes,
    nonActivePrizes,
  } = location.state

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
        <Game
          wordLength={wordLength}
          currentLine={currentLine}
          letters={letters}
          isEnd={currentLine === 5}
        />
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
