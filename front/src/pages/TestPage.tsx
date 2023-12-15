import React from 'react'
import { Message } from '../components/Message.tsx/Message'
import HowToPlay from '../components/HowToPlay/HowToPlay'
import { Rules } from '../components/Rules/Rules'
import TreeSanki from '../assets/images/Tree/TreeSanki.png'
import { Meaning } from '../components/Meaning/Meaning'
import { LotteryTicket } from '../components/App/LotteryTicket/LotteryTicket'
import { Feedback } from '../components/Feedback/Feedback'
import { Game } from '../components/Game/Game'
import { Letter } from '../types/game'

const TestPage = () => {
  const letters: Letter[] = [
    { letter: 'а', state: 'yellow' },
    { letter: 'в', state: 'yellow' },
    { letter: 'а', state: 'green' },
    { letter: 'н', state: 'grey' },
    { letter: 'с', state: 'yellow' },
    { letter: 'с', state: 'green' },
    { letter: 'м', state: 'grey' },
    { letter: 'о', state: 'grey' },
    { letter: 'л', state: 'yellow' },
    { letter: 'а', state: 'green' },
    { letter: 'с', state: 'green' },
    { letter: 'л', state: 'green' },
    { letter: 'а', state: 'green' },
    { letter: 'в', state: 'green' },
    { letter: 'а', state: 'green' },
  ]

  return (
    <div className="main-wrapper">
      <div className="main-page__row">
        <div className="main-image__container">
          <div className="main-page__title">
            <h1>Наряди свою ёлочку Благополучия</h1>
          </div>

          <img src={TreeSanki} alt="Ёлочка" />
        </div>
      </div>
      <div className="main-page__row">
        <Message text="Новогодняя викторина начнется 18 декабря в 10.00" />
      </div>
      <div className="main-page__row">
        <Message
          text={`<b>Задание 1.</b> <a
          href="https://telegra.ph/Novogodnyaya-viktorina-Naryadi-svoyu-yolochku-12-12"
          target="_blank"
        >
          К правилам
        </a> На стебле сидит краса, желтизна в волосах, плотками повязалась, скрытою осталась!`}
        />
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
      {/* Meaning */}
      <div className="main-page__row">
        <Meaning text="Кукуруза - Злак с толстым стеблем и крупными съедобными желтыми зернами, собранными в початок Злак с толстым стеблем и крупными съедобными желтыми зернами, собранными в початок, а также зерна этого растения." />
      </div>
      {/* Feedback */}
      <div className="main-page__row">
        <Feedback text="Деду Морозу хочется узнать твое мнение" />
      </div>
      {/* Game field */}
      <div className="main-page__row">
        <Game wordLength={5} currentLine={3} isEnd={false} letters={letters} />
      </div>
      {/* Порядковый номер */}
      <div className="main-page__row">
        <LotteryTicket ticketNumber="11" />
      </div>
      {/* Елка на канвасе */}
      <div className="main-page__row"></div>
    </div>
  )
}

export default TestPage
