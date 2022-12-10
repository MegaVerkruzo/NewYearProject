import React from 'react';

const Input = ({value, icon, label, placeholder, onChangeInput, validate, type}) => {
    return (
        <div className="reg-form__input">
            <label>{label}</label>
            <input type={type} placeholder={placeholder} onChange={onChangeInput} value={value}/>
        </div>
    );
};

export default Input;
