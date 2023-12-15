import giftTop from '../../assets/images/gift_top.png'
import giftBottom from '../../assets/images/gift_bottom.png'
import { FC, useState } from 'react'
import { useSendFeedback } from '../../api/sendFeedback'

type FeedbackProps = {
  text: string
}

export const Feedback: FC<FeedbackProps> = ({ text }) => {
  const [feedback, setFeedback] = useState('')
  const [isFeedbackJustSent, setIsFeedbackJustSent] = useState(false)

  // TODO: дописать прокидываение ошибок и setIsFeedbackJustSent при успешной отправке
  const { mutate: sendFeedback } = useSendFeedback()

  const onSendFeedback = () => {
    const trimmedFeedback = feedback.trim()
    if (trimmedFeedback.length === 0) return

    sendFeedback({ feedback: trimmedFeedback })
  }

  return (
    <div className="feedback">
      <div className="top__gift">
        <img src={giftTop} alt="" />
      </div>
      {!isFeedbackJustSent ? (
        <div className="reg-form feedback-form">
          <div className="reg-form__wrapper">
            <div className="reg-form__title">
              <h3>{text}</h3>
            </div>
            <div className="reg-form__input">
              <label>
                <textarea
                  placeholder="Дорогой Дедушка Мороз, ..."
                  onChange={(e) => setFeedback(e.target.value)}
                  value={feedback}
                />
              </label>
            </div>
            {/* {store.feedbackError && (
                <div className="error">{store.feedbackError}</div>
              )}
              {store.isFormLoading && (
                <div className="form_loading">
                  <Spinner />
                </div>
              )} */}
            <div className="reg-form__btn feedback-form__btn">
              <button onClick={() => onSendFeedback()}>Отправить</button>
            </div>
          </div>
        </div>
      ) : (
        <div className="reg-form feedback-form">
          <div className="reg-form__wrapper">
            <div className="thanks">
              <h2>
                Спасибо за участие в викторине! Итоги ищите в нашем
                telegram-канале.
                <br />
                <br />
                До встречи в Новом году.
                <br />
                <br />
                Ваше Благополучие
              </h2>
            </div>
          </div>
        </div>
      )}
      <div className="bottom__gift">
        <img src={giftBottom} alt="" />
      </div>
    </div>
  )
}
