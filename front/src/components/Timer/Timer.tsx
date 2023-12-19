import { useRef, useEffect, useState, useCallback } from 'react'
import TimerSnow from '../../assets/images/TimerSnow.png'
import TimerBg from '../../assets/images/TimerBG.png'
import { getTimeDiff } from '../../utils/getTimeDiff'
import { useMainStore } from '../../store/mainStore'

type Time = {
  days: number
  hours: number
  minutes: number
}

export const Timer = () => {
  const timer = useMainStore((state) => state.timer)
  const [time, setTime] = useState<null | Time>(null)
  const timerRef = useRef<number | null>(null)

  const calcTime = useCallback(() => {
    const curTime = getTimeDiff(timer)

    setTime((_prev) => {
      if (!curTime) return curTime
      return { ...curTime }
    })
  }, [timer])

  useEffect(() => {
    calcTime()
    timerRef.current = setInterval(() => {
      calcTime()
    }, 60000)

    return () => {
      if (!timerRef.current) return
      clearInterval(timerRef.current)
    }
  }, [calcTime])

  return (
    <>
      {time ? (
        <div className="timer">
          <div className="header__timer">
            <div className="header__row">
              <img src={TimerSnow} alt="Снежок" className="timer__snow" />
              <div className="timer__title">До розыгрыша осталось:</div>
            </div>
            <div className="header__row timer-row">
              <div className="timer__main">
                <div className="timer__block">
                  <div className="timer__num">{Math.floor(time.days / 10)}</div>
                  <div className="timer__num">{time.days % 10}</div>
                  <div className="timer__text">Дней</div>
                </div>
                <div className="timer__splitter">
                  <div className="splitter__circle" />
                  <div className="splitter__circle" />
                </div>
                <div className="timer__block">
                  <div className="timer__num">
                    {Math.floor(time.hours / 10)}
                  </div>
                  <div className="timer__num">{time.hours % 10}</div>
                  <div className="timer__text">Часов</div>
                </div>
                <div className="timer__splitter">
                  <div className="splitter__circle" />
                  <div className="splitter__circle" />
                </div>
                <div className="timer__block">
                  <div className="timer__num">
                    {Math.floor(time.minutes / 10)}
                  </div>
                  <div className="timer__num">{time.minutes % 10}</div>
                  <div className="timer__text">Минут</div>
                </div>
              </div>
              <img src={TimerBg} alt="Timer Background" className="timer_bg" />
            </div>
          </div>
        </div>
      ) : (
        <></>
      )}
    </>
  )
}
