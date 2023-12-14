import Tree from '../../assets/images/Tree/Tree6.png'
import Sanki from '../../assets/images/Sanki.png'
import { Message } from '../../components/Message.tsx/Message'
import { Rules } from '../../components/Rules/Rules'
import HowToPlay from '../../components/HowToPlay/HowToPlay'
import { useEffect } from 'react'
import { useNavigate } from 'react-router'

export const BeforeGame = () => {
  const navigate = useNavigate()
  useEffect(() => {}, [navigate])

  return (
    <div className="main-page__top">
      <div className="main-page__row">
        <div className="main-image__container">
          <div className="main-page__title">
            <h1>Наряди свою ёлочку Благополучия</h1>
          </div>

          <img src={Tree} alt="Ёлочка" />
          <img src={Sanki} alt="Санки" className="sanki" />
        </div>
      </div>
      <div className="main-page__row">
        <Message text="Новогодняя викторина начнется 18 декабря в 10.00" />
      </div>

      <div className="main-page__row">
        <a
          href="https://telegra.ph/Novogodnyaya-viktorina-Naryadi-svoyu-yolochku-12-12"
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
