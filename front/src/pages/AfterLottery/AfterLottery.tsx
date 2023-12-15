import { useLocation } from 'react-router-dom'
import { Message } from '../../components/Message.tsx/Message'
import { Tree } from '../../components/Tree/Tree'
import { AfterLotteryState } from '../../types/gameState'

export const AfterLottery = () => {
  const location = useLocation() as { state: AfterLotteryState }
  const { text, activeGifts } = location.state

  return (
    <div className="main-wrapper">
      <div className="main-page__row">
        <Tree activeGifts={activeGifts} />
      </div>
      <div className="main-page__row">
        <Message text={text} />
      </div>
    </div>
  )
}
