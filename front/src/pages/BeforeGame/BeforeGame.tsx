import Tree from '../../assets/images/Tree/Tree6.png'
import { Message } from '../../components/Message.tsx/Message'
import { Rules } from '../../components/Rules/Rules'
import HowToPlay from '../../components/HowToPlay/HowToPlay'
import { useEffect } from 'react'
import { useNavigate } from 'react-router'

export const BeforeGame = () => {
  const navigate = useNavigate()
  useEffect(() => {}, [navigate])

  return (
    <div className="main-page__top align-center">
      <div className="main-page__row">
        <div className="main-page__title">
          <h1>Игра «Наряди ёлку»</h1>
        </div>
      </div>
      <div className="main-page__row">
        <img src={Tree} alt="Ёлочка" />
      </div>
      <div className="main-page__row">
        <Message text="Первая игра начнётся 19 декабря в 9:00!" />
      </div>

      <div className="main-page__row">
        <a
          href="https://app.simplenote.com/p/0RWqgQ"
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
