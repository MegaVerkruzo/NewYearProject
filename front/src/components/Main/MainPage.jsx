import React from 'react';
import Tree from '../../img/Tree.png'
import {ReactComponent as MessageAngle} from '../../img/Message_angle.svg'

const MainPage = () => {
    return (
        <div className="main-page__top align-center">
            <div className="main-page__column">
                <div className="main-page__title"><h1>Новогодняя викторина «Наряди свою ёлочку Благополучия»</h1></div>
                <div className="main-page__text">
                    <span>Первый вопрос будет ждать вас 19 декабря в 09:00 мск в Telegram-канале <a
                        href="https://t.me/+QnoomH1zp9k0OGUy" target="_blank">«Благополучие-INFO»</a></span>
                    <MessageAngle/>
                </div>
                <a href="#rules" className="main-page__btn">К правилам</a>
            </div>
            <div className="main-page__column">
                <img src={Tree} alt="Ёлочка"/>
            </div>
        </div>

    );
};

export default MainPage;
