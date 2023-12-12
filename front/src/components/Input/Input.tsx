import { IMaskInput } from 'react-imask'
import { FieldsNames } from '../../types/register'

const PhoneMask = '+{7} (000) 000-00-00'

type InputProps = {
  type: string
  field: FieldsNames
  value: string
  label: string
  placeholder: string
  onChangeInput: (field: FieldsNames, value: string) => void
  icon?: string
}

export const Input = ({
  type,
  value,
  field,
  label,
  placeholder,
  onChangeInput,
  icon,
}: InputProps) => {
  return (
    <div className="reg-form__input">
      <label>
        <div className="label__text">{label}</div>
        {type === 'tel' ? (
          <IMaskInput
            mask={PhoneMask}
            value={value}
            onAccept={(_value, { unmaskedValue }) => {
              onChangeInput(
                'phone',
                unmaskedValue.slice(1, unmaskedValue.length),
              )
            }}
            placeholder="Введите номер телефона"
          />
        ) : (
          <input
            type={type}
            placeholder={placeholder}
            onChange={(e) => onChangeInput(field, e.target.value)}
            value={value}
          />
        )}
      </label>
    </div>
  )
}
