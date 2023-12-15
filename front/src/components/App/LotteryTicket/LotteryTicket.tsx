import { FC } from 'react'
import Sphere from '../../../assets/images/Sphere.png'

type TicketProps = {
  ticketNumber: string
}

export const LotteryTicket: FC<TicketProps> = ({ ticketNumber }) => {
  return (
    <div className="ticket">
      <h1>Твой порядковый номер</h1>
      <div className="ticket__image">
        <img src={Sphere} alt="Шар" />
        <div className="ticket__number">{ticketNumber}</div>
      </div>
      <div className="ticket__text">
        Текст для пояснения зачем нужен порядковый номер и как выиграть подарки
      </div>
    </div>
  )
}
