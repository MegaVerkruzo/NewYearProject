import { useState } from 'react'
import Logo from '../../assets/svgs/LogoIcon'
import NoSound from '../../assets/svgs/NoSoundIcon'
import Sound from '../../assets/svgs/SoundIcon'
import TimerSnow from '../../assets/images/TimerSnow.png'
import { Timer } from '../Timer/Timer'
import cn from 'classnames'
import TimerBg from '../../assets/images/TimerBG.png'

export const Header = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false)
  const [isSound, setIsSound] = useState(false)

  const toggleIsSound = () => {
    setIsSound((prev) => !prev)
  }

  const onMenuOpen = () => {
    setIsMenuOpen((prev) => {
      document.body.style.overflowY = !prev ? 'hidden' : 'scroll'
      return !prev
    })
  }

  return (
    <header className="header">
      <div className="container">
        <div className="header__row">
          <div className="header__top">
            <div className="header__left">
              <div className="header__sound">
                <button className="sound__btn" onClick={toggleIsSound}>
                  {isSound ? <Sound /> : <NoSound />}
                </button>
              </div>
            </div>
            {/* <div>
              <img src={TimerSnow} alt="Снежок" className="timer__snow" />
            </div> */}
            <div className="header__right">
              <div
                className={cn('header__burger', {
                  'menu-open': isMenuOpen,
                })}
                onClick={onMenuOpen}
              >
                <span />
              </div>
              <nav
                className={cn('menu__nav', { 'menu-open': isMenuOpen })}
                onClick={onMenuOpen}
              >
                <div className="header__logo">
                  <Logo />
                </div>
                <div className="header__help">
                  <a href="https://t.me/snegurochkablago" target="_blank">
                    Снегурочка на связи
                  </a>
                </div>
              </nav>
            </div>
          </div>
        </div>

        {/* <div className="header__row">
          <div className="header__timer">
            <img src={TimerBg} alt="Timer Background" className="timer_bg" />
            <div className="timer__title">
              {window.innerWidth > 1140 && (
                <img src={TimerSnow} alt="Снежок" className="timer__snow" />
              )}
              До нового года осталось:
            </div>
            <Timer />
          </div>
        </div>
         */}
      </div>
    </header>
  )
}
