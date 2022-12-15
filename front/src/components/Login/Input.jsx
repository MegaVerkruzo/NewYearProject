import React from 'react';

const Input = ({value, icon, label, placeholder, onChangeInput, validate, type}) => {
    return (
        <div className="reg-form__input">
            <label>
                <div className="label__text">{label}</div>
                <input type={type} placeholder={placeholder} onChange={onChangeInput} value={value}/>
            </label>
        </div>
    );
};

export default Input;
