import React from 'react';
import giftTop from "../../img/gift_top.png";
import giftBottom from "../../img/gift_bottom.png";
import store from "../../store/store";
import {observer} from "mobx-react-lite";
import {sendFeedback} from "../../http/userAPI";

const Feedback = () => {
    const onSendFeedback = async () => {
        try {
            if (!store.feedback?.trim()) {
                store.setFeedbackError('Форма не может быть пустой')
                return
            }
            const data = await sendFeedback(store.feedback)
            console.log(data)
            if (data.status === 'ok') {
                store.setFeedbackError('Спасибо за обратную связь!')
            } else {
                if (data.exception === 'noUser') {
                    store.setFeedbackError('Пользователь не зарегистрирован')
                } else if (data.exception === 'alreadySent') {
                    store.setFeedbackError('Форма уже была отправлена ранее')
                }
            }
        } catch (e) {
            store.setFeedbackError('Произошла ошибка сервера')
        }
    }

    return (
        <div className="main-page__feedback">
            <div className="main__wrapper">
                <div className="top__gift">
                    <img src={giftTop} alt=""/>
                </div>
                <div className="reg-form feedback-form">
                    <div className="reg-form__wrapper">
                        <div className="reg-form__title">
                            <h1>Деду Морозу хочется узнать твое мнение о подарке</h1>
                        </div>
                        <div className="reg-form__input">
                            <label>
                                <div className="label__text">Напишите, что вы думаете о игре</div>
                                <textarea placeholder="Дорогой Дедушка Мороз, ..."
                                          onChange={(e) => store.setFeedbackText(e.target.value)}
                                          value={store.feedback}/>
                            </label>
                        </div>
                        {store.feedbackError && <div className="error">{store.feedbackError}</div>}
                        <div className="reg-form__btn feedback-form__btn">
                            <button onClick={() => onSendFeedback()}>Отправить</button>
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

export default observer(Feedback);
