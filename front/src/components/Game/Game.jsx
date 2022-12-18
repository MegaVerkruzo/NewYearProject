import React from 'react';
import store from "../../store/store";
import {observer} from "mobx-react-lite";
import Tree from '../../img/Tree.png'
import GameField from "./GameField";
import {ReactComponent as MessageAngle} from '../../img/Message_angle.svg'
import cn from "classnames";
import {onDelete, onEnter, onWrite} from "./GameFunction";
import {russianLetters} from "../../static_data/GameData";

const keyboardHandler = (e) => {
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
                <img src={Tree} alt="Ёлочка" className="game-tree"/>
            </div>
        </div>
    );
};

export default observer(Game);
