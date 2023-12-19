import giftTop from '../../assets/images/gift_top.png'
import giftBottom from '../../assets/images/gift_bottom.png'
import { useEffect, useState } from 'react'
import Spinner from '../../assets/svgs/Spinner'
import { fieldsData } from './registerData'
import { Input } from '../../components/Input/Input'
import { RegisterFields, FieldsNames } from '../../types/register'
import { useRegister } from '../../api/register'
import { AxiosError } from 'axios'
import { ApiError } from '../../types/error'

const initialFields: RegisterFields = {
  name: '',
  surname: '',
  middleName: '',
  email: '',
  phone: '',
  place: '',
  division: '',
}

export const Register = () => {
  const [fields, setFields] = useState<RegisterFields>(initialFields)
  const [isAgreePolicy, setIsAgreePolicy] = useState(false)
  const [error, setError] = useState<string | null>(null)

  const {
    mutate: register,
    isError,
    error: mutateError,
    isPending,
  } = useRegister()

  const onSignUp = () => {
    setError(null)

    let isCorrect = true
    Object.values(fields).forEach((value) => {
      if (value === '') {
        setError('Не все поля заполнены')
        isCorrect = false
        return
      }
      if (value.length > 255) {
        setError('Размер данных в поле ввода не должен превышать 255 символов')
        isCorrect = false
        return
      }
    })

    if (!isCorrect) return

    if (fields.phone.length < 10) {
      setError('Телефон неверно введен')
      return
    }

    if (!isAgreePolicy) {
      setError('Подтвердите согласие с политикой конфиденциальности')
      return
    }

    register(fields)
  }

  useEffect(() => {
    if (isError && mutateError instanceof AxiosError) {
      const err = mutateError as AxiosError<ApiError>
      if (err.response?.status === 500) {
        setError('Ошибка сервера')
        return
      }
      setError('Некорректный запрос')
    }
  }, [isError, mutateError])

  const onChangeInput = (field: FieldsNames, value: string) => {
    setFields((prev) => ({ ...prev, [field]: value }))
  }

  return (
    <div className="main-page__login">
      <div className="main__wrapper">
        <div className="reg__wrapper">
          <div className="top__gift">
            <img src={giftTop} alt="" />
          </div>
          <div className="reg-form">
            <div className="reg-form__wrapper">
              <div className="reg-form__title">
                <h1>Укажите ваши данные,</h1>
                чтобы Дед Мороз знал, куда везти подарки
              </div>
              {fieldsData.map((item) => (
                <Input
                  field={item.field}
                  value={fields[item.field]}
                  onChangeInput={onChangeInput}
                  label={item.label}
                  placeholder={item.placeholder}
                  type={item.type}
                  key={item.id}
                />
              ))}
              <div className="reg-form__input last">
                <div className="checkbox">
                  <input
                    type="checkbox"
                    id="checkbox2"
                    className="checkbox"
                    onChange={() => setIsAgreePolicy((prev) => !prev)}
                    checked={isAgreePolicy}
                  />
                  <label htmlFor="checkbox2">
                    Я подтверждаю свое согласие <br />
                    <a
                      href="https://telegra.ph/Politika-v-otnoshenii-obrabotki-personalnyh-dannyh-12-18-6"
                      target="_blank"
                      className="underline"
                    >
                      с политикой конфиденциальности
                    </a>
                  </label>
                </div>
              </div>
              {error && <div className="error">{error}</div>}
              {isPending && (
                <div className="form_loading">
                  <Spinner />
                </div>
              )}
              <div className="reg-form__btn" onClick={onSignUp}>
                <button>Зарегистрироваться</button>
              </div>
            </div>
          </div>
          <div className="bottom__gift">
            <img src={giftBottom} alt="" />
          </div>
        </div>
      </div>
    </div>
  )
}
