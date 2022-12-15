import React from 'react';
import Input from "./Input";
import store from "../../store/store";
import {observer} from "mobx-react-lite";
import {fields_reg} from "../../static_data/RegisterData";
import {registration} from "../../http/userAPI";
import {Link, useNavigate} from "react-router-dom";
import giftTop from '../../img/gift_top.png'
import giftBottom from '../../img/gift_bottom.png'

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
        <div className="main__wrapper">
            <div className="top__gift">
                <img src={giftTop} alt=""/>
            </div>
            <div className="reg-form">
                <div className="reg-form__wrapper">
                    <div className="reg-form__title">
                        <h1>Укажите ваши данные</h1>
                        Чтобы Дед Мороз знал, куда везти подарки
                    </div>
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
                    <div className="reg-form__input last">
                        <div className="checkbox">
                            <input type="checkbox" id="checkbox2" className="checkbox"
                                   onChange={(e) => onChangeInput('isAgreePolicy', !userData.isAgreePolicy)}/>
                            <label htmlFor="checkbox2">Я подтверждаю свое согласие <br/> с
                                <a href="#" target="_blank" className="underline">политикой конфиденциальности</a>
                            </label>
                        </div>
                    </div>
                    <div className="reg-form__btn">
                        <button onClick={onSignUp} disabled={!userData.isAgreePolicy}>Зарегистрироваться</button>
                    </div>
                    <div className="reg-form__bottom-text">
                        Уже есть аккаунт? <Link to="/login">Войти</Link>
                    </div>
                </div>
            </div>
            <div className="bottom__gift">
                <img src={giftBottom} alt=""/>
            </div>
        </div>
    );
};

export default observer(Register);
