import React from 'react';
import {IMaskInput} from "react-imask";
import store from "../../store/store";

const PhoneMask = '+{7} (000) 000-00-00';
const EmailMask = /^\S*@?\S*$/;

const Input = ({value, icon, label, placeholder, onChangeInput, validate, type}) => {
    return (
        <div className="reg-form__input">
            <label>
                <div className="label__text">{label}</div>
                {type === 'tel' ? <IMaskInput
                    mask={PhoneMask}
                    value={value}
                    onAccept={(value, {unmaskedValue}) => {
                        store.changeUserData('phone', unmaskedValue.slice(1, unmaskedValue.length))
                    }}
                    placeholder="Введите номер телефона"
                /> : type === 'email' ? <IMaskInput
                        mask={EmailMask}
                        value={value}
                        onAccept={(value, {unmaskedValue}) => {
                            store.changeUserData('email', unmaskedValue)
                        }}
                        placeholder="Введите email"
                    />
                    : <input type={type} placeholder={placeholder} onChange={onChangeInput} value={value}/>
                }
            </label>
        </div>
    );
};

export default Input;
