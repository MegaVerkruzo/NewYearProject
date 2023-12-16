import { useLocation } from 'react-router-dom'
import { Game } from '../../components/Game/Game'
import { Message } from '../../components/Message.tsx/Message'
import { Tree } from '../../components/Tree/Tree'
import { WaitFeedbackState } from '../../types/gameState'
import { Feedback } from '../../components/Feedback/Feedback'

export const WaitingFeedback = () => {
  const location = useLocation() as { state: WaitFeedbackState }
  const {
    text,
    letters,
    wordLength,
    feedbackQuestion,
    afterFeedbackResponse,
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
        <Game wordLength={wordLength} letters={letters} isEnd={true} />
      </div>
      <div className="main-page__row">
        <Feedback
          text={feedbackQuestion}
          afterFeedbackText={afterFeedbackResponse}
        />
      </div>
    </div>
  )
}
