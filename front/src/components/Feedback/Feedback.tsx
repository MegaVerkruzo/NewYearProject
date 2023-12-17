import giftTop from '../../assets/images/gift_top.png'
import giftBottom from '../../assets/images/gift_bottom.png'
import { FC, useEffect, useState } from 'react'
import { useSendFeedback } from '../../api/sendFeedback'
import Spinner from '../../assets/svgs/Spinner'
import { AxiosError } from 'axios'
import { ApiError } from '../../types/error'

type FeedbackProps = {
  text: string
  afterFeedbackText: string
}

export const Feedback: FC<FeedbackProps> = ({ text, afterFeedbackText }) => {
  const [feedback, setFeedback] = useState('')
  const [error, setError] = useState<string | null>(null)
  const [isFeedbackJustSent, setIsFeedbackJustSent] = useState(false)

  const {
    mutate: sendFeedback,
    isError,
    isPending,
    error: mutateError,
  } = useSendFeedback({ setIsFeedbackJustSent })

  const onSendFeedback = () => {
    const trimmedFeedback = feedback.trim()
    if (trimmedFeedback.length === 0) return
    setError(null)
    sendFeedback({ feedback: trimmedFeedback })
  }

  useEffect(() => {
    if (isError && mutateError instanceof AxiosError) {
      const err = mutateError as AxiosError<ApiError>
      if (err.response?.status === 500) {
        return setError('Ошибка сервера')
      }
      setError('Некорректный запрос')
    }
  }, [isError, mutateError])

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
                  placeholder="Для продолжения игры нужно оставить отзыв..."
                  onChange={(e) => setFeedback(e.target.value)}
                  value={feedback}
                />
              </label>
            </div>
            {error && <div className="error">{error}</div>}
            {isPending && (
              <div className="form_loading">
                <Spinner />
              </div>
            )}
            <button
              className="reg-form__btn feedback-form__btn"
              onClick={() => onSendFeedback()}
            >
              Отправить
            </button>
          </div>
        </div>
      ) : (
        <div className="reg-form feedback-form">
          <div className="reg-form__wrapper">
            <div className="thanks">
              <h2>{afterFeedbackText}</h2>
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
