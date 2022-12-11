import React from 'react';
import Input from "./Input";
import store from "../../store/store";
import {observer} from "mobx-react-lite";
import {fields_reg} from "../../static_data/RegisterData";
import {registration} from "../../http/userAPI";
import { useNavigate } from "react-router-dom";

const Register = ({userData}) => {
    const navigate = useNavigate()
    const onChangeInput = (field, data) => {
        store.changeUserData(field, data)
    }
    const onSignUp = async () => {
        try {
            const resp = await registration(userData)
            console.log(resp)
            navigate('/')
        } catch (e) {
            navigate('/')
            console.log(e.message)
        }
    }

    return (
        <div className="reg-form">
            register
            {
                fields_reg.map(item => <Input
                    value={userData[item.field]}
                    onChangeInput={(e) => onChangeInput(item.field, e.target.value)}
                    label={item.label}
                    placeholder={item.placeholder}
                    type={item.type}
                    key={item.id}
                />)
            }
            <div>
                <input type="checkbox"
                       onChange={(e) => onChangeInput('isAgreePolicy', !userData.isAgreePolicy)}/>
                <label>Я согласен с политикой конфиденциальности</label>
            </div>
            <button onClick={onSignUp} disabled={!userData.isAgreePolicy}>Зарегистрироваться</button>
        </div>
    );
};

export default observer(Register);
