import giftTop from '../..accets/images/gift_top.png'
import giftBottom from '../../accets/images/gift_bottom.png'
import { useState } from 'react'

export const Feedback = () => {
  const [isFeedbackJustSent, setIsFeedbackJustSent] = useState(false)
  const [feedbackText, setFeedbackText] = useState('')
  //   const onSendFeedback = async () => {
  //     try {
  //       store.setFeedbackError('')
  //       if (!store.feedback?.trim()) {
  //         store.setFeedbackError('Форма не может быть пустой')
  //         return
  //       }
  //       store.setIsFormLoading(true)
  //       const data = await sendFeedback(store.feedback)
  //       store.setIsFormLoading(false)
  //       if (data.exception === 'noUser') {
  //         store.setFeedbackError('Пользователь не зарегистрирован')
  //       } else if (data.exception === 'hadFeedback') {
  //         store.setFeedbackError('Форма уже была отправлена ранее')
  //       } else {
  //         store.setIsFeedbackJustSent()
  //       }
  //     } catch (e) {
  //       store.setIsFormLoading(false)
  //       store.setFeedbackError('Произошла ошибка сервера')
  //     }
  //   }

  const onSendFeedback = async () => {
    console.log('send feedback')
  }

  return (
    <div className="main-page__feedback">
      <div className="main__wrapper">
        <div className="top__gift">
          <img src={giftTop} alt="" />
        </div>
        {!isFeedbackJustSent ? (
          <div className="reg-form feedback-form">
            <div className="reg-form__wrapper">
              <div className="reg-form__title">
                <h3>
                  Для участия в розыгрыше супер-призов, ответьте на вопрос:
                  Какое мероприятие (активность, обучение и т.д.) нужно провести
                  в рамках программы «Благополучие» в следующем году?
                </h3>
              </div>
              <div className="reg-form__input">
                <label>
                  <textarea
                    placeholder="Проведите семейный спортивный марафон"
                    onChange={(e) => setFeedbackText(e.target.value)}
                    value={feedbackText}
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
    </div>
  )
}