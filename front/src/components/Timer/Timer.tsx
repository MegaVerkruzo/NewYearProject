import { useRef, useEffect } from 'react'
import TimerSnow from '../../assets/images/TimerSnow.png'
import TimerBg from '../../assets/images/TimerBG.png'

export const Timer = () => {
  const timerRef = useRef<number | null>(null)

  // const onTimerChange = () => {
  //   const diffInMs =
  //     new Date('2024-01-01T00:00:00.000+03:00') -
  //     new Date(new Date().toLocaleString('en', { timeZone: 'Europe/Moscow' }))
  //   const days = parseInt(diffInMs / (1000 * 60 * 60 * 24))
  //   const hours = parseInt(diffInMs / (1000 * 60 * 60)) % 24
  //   const minutes = parseInt(diffInMs / (1000 * 60)) % 60
  //   store.setUntilNewYear({ days, hours, minutes })
  // }

  useEffect(() => {
    // onTimerChange()
    timerRef.current = setInterval(() => {
      // onTimerChange()
    }, 1000)

    return () => {
      if (!timerRef.current) return
      clearInterval(timerRef.current)
    }
  }, [])

  return (
    <div className="timer">
      <div className="header__timer">
        <div className="header__row">
          <img src={TimerSnow} alt="Снежок" className="timer__snow" />
          {/* <img src={TimerBg} alt="Timer Background" className="timer_bg" /> */}
          <div className="timer__title">До розыгрыша осталось:</div>
        </div>
        <div className="header__row">
          <div className="timer__main">
            <div className="timer__block">
              <div className="timer__num">2</div>
              <div className="timer__num">3</div>
              <div className="timer__text">Дней</div>
            </div>
            <div className="timer__splitter">
              <div className="splitter__circle" />
              <div className="splitter__circle" />
            </div>
            <div className="timer__block">
              <div className="timer__num">0</div>
              <div className="timer__num">6</div>
              <div className="timer__text">Часов</div>
            </div>
            <div className="timer__splitter">
              <div className="splitter__circle" />
              <div className="splitter__circle" />
            </div>
            <div className="timer__block">
              <div className="timer__num">1</div>
              <div className="timer__num">2</div>
              <div className="timer__text">Минут</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
