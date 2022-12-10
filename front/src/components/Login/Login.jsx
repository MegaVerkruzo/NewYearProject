import React from 'react';
import {fields_login} from "../../static_data/RegisterData";
import Input from "./Input";
import store from "../../store/store";

const Login = ({user_data}) => {
    const onClickLogin = () => {
        console.log('login')
    }
    const onChangeInput = (field, data) => {
        store.changeUserData(field, data)
        console.log(user_data)
    }

    return (
        <div>
            login
            {
                fields_login.map(item => <Input
                    value={user_data[item.field]}
                    onChangeInput={(e) => onChangeInput(item.field, e.target.value)}
                    label={item.label}
                    placeholder={item.placeholder}
                    type={item.type}
                    key={item.id}
                />)
            }
            <button onClick={onClickLogin}>Войти</button>
        </div>
    );
};

export default Login;
