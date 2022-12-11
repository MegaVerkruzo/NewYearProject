import React from 'react';
import {fields_login} from "../../static_data/RegisterData";
import Input from "./Input";
import store from "../../store/store";
import {login} from "../../http/userAPI";
import {observer} from "mobx-react-lite";
import { useNavigate } from "react-router-dom";

const Login = ({userData}) => {
    let navigate = useNavigate()
    const onChangeInput = (field, data) => {
        store.changeUserData(field, data)
    }
    const onSignIn = async () => {
        try {
            const resp = await login({phone: userData.phone, password: userData.password})
            console.log(resp)
            navigate('/')
        } catch (e) {
            console.log(e.message)
        }
    }

    return (
        <div>
            login
            {
                fields_login.map(item => <Input
                    value={userData[item.field]}
                    onChangeInput={(e) => onChangeInput(item.field, e.target.value)}
                    label={item.label}
                    placeholder={item.placeholder}
                    type={item.type}
                    key={item.id}
                />)
            }
            <button onClick={onSignIn}>Войти</button>
        </div>
    );
};

export default observer(Login);
