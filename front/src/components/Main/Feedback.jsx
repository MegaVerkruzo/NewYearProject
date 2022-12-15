import React from 'react';
import giftTop from "../../img/gift_top.png";
import giftBottom from "../../img/gift_bottom.png";

const Feedback = () => {
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
                                <textarea placeholder="Дорогой Дедушка Мороз, ..."/>
                            </label>
                        </div>
                        <div className="reg-form__btn feedback-form__btn">
                            <button>Отправить</button>
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

export default Feedback;
