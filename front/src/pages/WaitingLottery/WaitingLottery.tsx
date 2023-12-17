import { useLocation } from 'react-router-dom'
import { LotteryTicket } from '../../components/App/LotteryTicket/LotteryTicket'
import { Message } from '../../components/Message.tsx/Message'
import { Tree } from '../../components/Tree/Tree'
import { WaitEndLotteryState } from '../../types/gameState'

export const WaitingLottery = () => {
  const location = useLocation() as { state: WaitEndLotteryState }
  const {
    text,
    activeGifts,
    activePrizes,
    nonActivePrizes,
    lotteryTimer,
    textWithLink,
    ticketNumber,
  } = location.state

  // TODO: добавить таймер в zustand store

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
        <LotteryTicket
          ticketNumber={ticketNumber.toString()}
          textBelow={textWithLink}
        />
      </div>
    </div>
  )
}
