import { useEffect, useState } from 'react'
import Logo from '../../assets/svgs/LogoIcon'
import NoSound from '../../assets/svgs/NoSoundIcon'
import Sound from '../../assets/svgs/SoundIcon'
import { Timer } from '../Timer/Timer'
import cn from 'classnames'

export const Header = () => {
  const [isMenuOpen, setIsMenuOpen] = useState(false)
  const [isSound, setIsSound] = useState(true)

  const toggleIsSound = () => {
    setIsSound((prev) => {
      localStorage.setItem('isSound', (!prev).toString())
      return !prev
    })
  }

  const onMenuOpen = () => {
    setIsMenuOpen((prev) => {
      document.body.style.overflowY = !prev ? 'hidden' : 'scroll'
      return !prev
    })
  }

  useEffect(() => {
    const value = localStorage.getItem('isSound')
    if (value !== null) {
      setIsSound(value === 'true' ? true : false)
    } else {
      localStorage.setItem('isSound', 'true')
    }
  }, [])

  useEffect(() => {
    console.log(isSound, localStorage.getItem('isSound'))
  }, [isSound])

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
            <Timer />
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
      </div>
    </header>
  )
}
