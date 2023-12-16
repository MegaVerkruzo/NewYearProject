import { FC } from 'react'
import Sphere from '../../../assets/images/Sphere.png'

type TicketProps = {
  ticketNumber: string
  textBelow: string
}

export const LotteryTicket: FC<TicketProps> = ({ ticketNumber, textBelow }) => {
  return (
    <div className="ticket">
      <h1>Твой номер для розыгрыша</h1>
      <div className="ticket__image">
        <img src={Sphere} alt="Шар" />
        <div className="ticket__number">{ticketNumber}</div>
      </div>
      <div className="ticket__text">{textBelow}</div>
    </div>
  )
}
