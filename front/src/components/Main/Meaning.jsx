import React from 'react';
import {ReactComponent as MessageAngle} from '../../img/Message_angle.svg'
import store from "../../store/store";

const Meaning = () => {
    return (
        <div className="main-page__meaning">
            <div className="meaning__title"><h1>Слово дня</h1></div>
            <div className="meaning__text">
                <div>{store.description === "1" &&
                    <div color={"black"}>
                        Мы загадали приставку «Кибер», потому что в «Снабжении» проводится кибертурнир – ежегодное
                        командное соревнование для всех любителей виртуальных баталий.
    \n\n
                        Вы знаете, что 300 миллионов человек во всем мире участвует в киберспортивных дисциплинах? И эта
                        цифра только растет!
/n/n
                        В нашей компании тоже немало таких ребят. В видеоиграх участники доказывают друг другу и
                        зрителям, что именно они быстрее принимают решения, придумывают и реализуют лучшие стратегии,
                        менее подвержены эмоциям – здесь всё, как и в реальном спорте!

                        Кстати, по итогам кибертурнира «Благополучия» наша команда NoMeta стала участником Лиги
                        чемпионов бизнеса, где отлично себя проявила и стала серебряным призером!

                        Новое слово ищите в telegram-канале завтра в 16.00 мск!
                    </div>}{store.description === "2" &&
                    <div>Правильный ответ – вызов! Ведь речь идет о большом командном спортивном турнире «Вызов
                        принят!», который прошел в «Снабжении» этим летом.

                        Сначала сотрудники объединились в команды с коллегами или членами своих семей, а затем начали
                        зарабатывать баллы за каждый километр, зафиксированный в тренировках.

                        Участники бегали, ходили, плавали и ездили на велосипеде. Всего за месяц они преодолели 27 000
                        километров. Отличная цифра, так держать!

                        Кстати, многим так понравилось соревноваться, что турнир «Благополучия» был продлен еще на
                        несколько недель по просьбам спортсменов.

                        Новое слово ищите в telegram-канале завтра в 16.00 мск!</div>}</div>
                <MessageAngle/>
            </div>
        </div>
    );
};

export default Meaning;
