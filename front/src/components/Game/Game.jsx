import React from 'react';
import store from "../../store/store";
import {observer} from "mobx-react-lite";
import {initialGameData} from "../../static_data/GameData";
import Tree from '../../img/Tree.png'
import GameField from "./GameField";
import {ReactComponent as MessageAngle} from '../../img/Message_angle.svg'
import cn from "classnames";
import {getAllInfo} from "../../http/wordsAPI";

const Game = () => {
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
