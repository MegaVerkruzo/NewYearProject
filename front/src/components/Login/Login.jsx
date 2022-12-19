import React from 'react';
import {fields_login} from "../../static_data/RegisterData";
import Input from "./Input";
import store from "../../store/store";
import {login} from "../../http/userAPI";
import {observer} from "mobx-react-lite";
import {Link, useNavigate} from "react-router-dom";
import giftTop from "../../img/gift_top.png";
import giftBottom from "../../img/gift_bottom.png";
import {ReactComponent as Spinner} from "../../img/spinner.svg";

const Login = ({userData}) => {
    let navigate = useNavigate()
    React.useEffect(() => {
        store.setRegError(null)
    }, [])
    const onChangeInput = (field, data) => {
        store.changeUserData(field, data)
    }
    const onSignIn = async () => {
        try {
            store.setRegError('')
            if (!store.userData.phone || !store.userData.password) {
                store.setRegError('Не все поля заполнены')
                return
            }
            if (store.userData.phone.length < 10) {
                store.setRegError('Телефон неверно введен')
                return
            }
            store.setIsFormLoading(true)
            const data = await login({phone: userData.phone, password: userData.password})
            store.setIsFormLoading(false)
            if (data.token) {
                localStorage.setItem('token', data.token)
                navigate('/game')
            } else {
                if (data.exception === 'noUser') {
                    store.setRegError('Неверно введен номер или пароль')
                } else if (data.exception === 'hugeSizeField') {
                    store.setRegError('Размер данных в поле ввода не должен превышать 255 символов')
                }
            }
        } catch (e) {
            console.log(e.message)
            store.setIsFormLoading(false)
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
                            fields_login.map(item => <Input
                                value={userData[item.field]}
                                onChangeInput={(e) => onChangeInput(item.field, e.target.value)}
                                label={item.label}
                                placeholder={item.placeholder}
                                type={item.type}
                                key={item.id}
                            />)
                        }
                        {store.regError && <div className="error">{store.regError}</div>}
                        {store.isFormLoading && <div className="form_loading"><Spinner/></div>}
                        <div className="reg-form__btn" onClick={onSignIn}>
                            <button>Войти</button>
                        </div>
                        <div className="reg-form__bottom-text">
                            Нет аккаунта? <Link to="/register">Зарегистрироваться</Link>
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

export default observer(Login);
