import React from 'react';
import store from "../../store/store";
import {observer} from "mobx-react-lite";
import GameField from "./GameField";
import {ReactComponent as MessageAngle} from '../../img/Message_angle.svg'
import cn from "classnames";
import {onDelete, onEnter, onWrite} from "./GameFunction";
import {russianLetters} from "../../static_data/GameData";
import Tree1 from './../../img/1.png'
import Tree2 from './../../img/2.png'
import Tree3 from './../../img/3.png'
import Tree4 from './../../img/4.png'
import Tree5 from './../../img/5.png'
import Tree6 from './../../img/7.png'

const photos = [{count: 0, img: Tree1}, {count: 1, img: Tree2}, {count: 2, img: Tree3},
    {count: 3, img: Tree4}, {count: 4, img: Tree5}, {count: 5, img: Tree6}]


const keyboardHandler = (e) => {
    if (!store.isEnd) {
        if (e.key === 'Backspace') {
            onDelete()
        } else if (e.key === 'Enter') {
            onEnter()
        } else if (e.key === ' ') {
            e.preventDefault()
        } else if (russianLetters.includes(e.key)) {
            onWrite(e.key)
        }
    }
}

const Game = () => {
    React.useEffect(() => {
        document.addEventListener('keydown', keyboardHandler)
        return () => {
            document.removeEventListener('keydown', keyboardHandler)
        }
    }, [])

    return (
        <div className="main-page__top game">
            <div className="main-page__column col1">
                <div className="main-page__title"><h1>Новогодняя викторина «Наряди свою ёлочку
                    Благополучия»</h1></div>
            </div>
            <div className={cn("main-page__column col2", {'keyboard-open': store.isKeyboardOpen})}>
                <div className="main-page__text">
                    <span>Сегодня 19 число, вас ждёт задание в телеграм канале Благополучие</span>
                    <MessageAngle/>
                </div>
                <GameField/>
            </div>
            <div className="main-page__column  col3">
                <img src={photos[store.countCorrectAnswersBefore]} alt="Ёлочка" className="game-tree"/>
            </div>
        </div>
    );
};

export default observer(Game);
