import MessageAngle from '../../assets/svgs/MessageAngleIcon'
import Tree from '../../assets/images/Tree/Tree6.png'
import { Message } from '../../components/Message.tsx/Message'

export const BeforeGame = () => {
  // React.useEffect(() => {
  // }, [navigate])

  return (
    <>
      {window.innerWidth > 672 ? (
        <div className="main-page__top align-center">
          <div className="main-page__column">
            <div className="main-page__title">
              <h1>Игра «Наряди ёлку»</h1>
            </div>
            <div className="main-page__text">
              <span>
                Первая игра начнётся 19 декабря в 9:00!
                <a href="https://t.me/+QnoomH1zp9k0OGUy" target="_blank">
                  «Благополучие-INFO»
                </a>
              </span>
              <MessageAngle />
            </div>
            <a
              href="https://app.simplenote.com/p/tmCzZ4"
              target="_blank"
              className="main-page__btn"
            >
              К правилам
            </a>
          </div>
          <div className="main-page__column">
            <img src={Tree} alt="Ёлочка" />
          </div>
        </div>
      ) : (
        <div className="main-page__top align-center">
          <div className="main-page__column">
            <div className="main-page__title">
              <h1>Игра «Наряди ёлку»</h1>
            </div>
          </div>
          <div className="main-page__column">
            <img src={Tree} alt="Ёлочка" />
          </div>
          <div className="main-page__column">
            <Message text="Первая игра начнётся 19 декабря в 9:00!" />
            <a
              href="https://app.simplenote.com/p/0RWqgQ"
              target="_blank"
              className="main-page__btn"
            >
              К правилам
            </a>
          </div>
        </div>
      )}
    </>
  )
}
