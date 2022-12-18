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
    React.useEffect(() => {
        store.setRegError(null)
    }, [])
    const navigate = useNavigate()
    const onChangeInput = (field, data) => {
        store.changeUserData(field, data)
    }
    const onSignUp = async () => {
        try {
            if (store.userData.password !== store.userData.password_repeat) {
                store.setRegError('Пароли не совпадают')
                return
            }
            if (!store.userData.name || !store.userData.surname || !store.userData.middlename
                || !store.userData.email || !store.userData.phone || !store.userData.password
                || !store.userData.place) {
                store.setRegError('Не все поля заполнены')
                return
            }
            if (store.userData.phone.length < 10) {
                store.setRegError('Телефон неверно введен')
                return
            }
            if (!store.userData.isAgreePolicy) {
                store.setRegError('Подтвердите согласие с политикой конфиденциальности')
                return
            }

            const data = await registration(userData)
            if (data.token) {
                localStorage.setItem('token', data.token)
                navigate('/')
            } else {
                if (data.exception === 'userExists') {
                    store.setRegError('Пользователь уже существует')
                } else if (data.exception === 'hugeSizeField') {
                    store.setRegError('Размер данных в поле ввода не должен превышать 255 символов')
                }
            }
        } catch (e) {
            console.log(e.message)
            store.setRegError('Произошла ошибка сервера')
        }
    }

    return (
        <div className="main-page__login">
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
                                       onChange={(e) => onChangeInput('isAgreePolicy', !userData.isAgreePolicy)}
                                       value={userData.isAgreePolicy}/>
                                <label htmlFor="checkbox2">Я подтверждаю свое согласие <br/>
                                    <a href="https://app.simplenote.com/p/XV73L9" target="_blank" className="underline">с
                                        политикой
                                        конфиденциальности</a>
                                </label>
                            </div>
                        </div>
                        {store.regError && <div className="error">{store.regError}</div>}
                        <div className="reg-form__btn" onClick={onSignUp}>
                            <button>Зарегистрироваться</button>
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
        </div>
    );
};

export default observer(Register);
