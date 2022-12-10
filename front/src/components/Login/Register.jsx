import React from 'react';
import Input from "./Input";
import store from "../../store/store";
import {observer} from "mobx-react-lite";
import {fields_reg} from "../../static_data/RegisterData";

const Register = ({user_data}) => {
    const onChangeInput = (field, data) => {
        store.changeUserData(field, data)
        console.log(user_data)
    }
    const onClickReg = () => {
        console.log(user_data)
    }

    return (
        <div className="reg-form">
            register
            {
                fields_reg.map(item => <Input
                        value={user_data[item.field]}
                        onChangeInput={(e) => onChangeInput(item.field, e.target.value)}
                        label={item.label}
                        placeholder={item.placeholder}
                        type={item.type}
                        key={item.id}
                    />)
            }
            <div>
                <input type="checkbox"
                       onChange={(e) => onChangeInput('isAgreePolicy', !user_data.isAgreePolicy)}/>
                <label>Я согласен с политикой конфиденциальности</label>
            </div>
            <button onClick={onClickReg} disabled={!user_data.isAgreePolicy}>Зарегистрироваться</button>
        </div>
    );
};

export default observer(Register);
