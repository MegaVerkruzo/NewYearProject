import React from 'react';
import {ReactComponent as Rule1} from '../../img/Rule1.svg'
import {ReactComponent as Rule2} from '../../img/Rule2.svg'
import {ReactComponent as Rule3} from '../../img/Rule3.svg'
import {ReactComponent as Rule4} from '../../img/Rule4.svg'

const Rules = () => {
    return (
        <div className="main-page__rules" id="rules">
            <div className="rules__title"><h1>Правила игры</h1></div>
            <div className="rules__container">
                <div className="rules__item">
                    <div className="rule__icon">
                        <Rule1 />
                    </div>
                    <div className="rule__title">Найдите загадку</div>
                    <div className="rule__text">Каждый день в Telegram-канале будет ждать новая загадка
                    </div>
                </div>
                <div className="rules__item">
                    <div className="rule__icon">
                        <Rule2 />
                    </div>
                    <div className="rule__title">Угадайте слово</div>
                    <div className="rule__text">Ответ на загадку – одно слово. Введите его на этой странице
                    </div>
                </div>
                <div className="rules__item">
                    <div className="rule__icon">
                        <Rule3 />
                    </div>
                    <div className="rule__title">Нарядите ёлочку</div>
                    <div className="rule__text">
                        За каждое угаданное слово на ёлочке загорится новая игрушка
                    </div>
                </div>
                <div className="rules__item">
                    <div className="rule__icon">
                        <Rule4 />
                    </div>
                    <div className="rule__title">Получите призы</div>
                    <div className="rule__text">
                        За полностью наряженную ёлку Дед Мороз принесёт вам подарок!
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Rules;
