import React from 'react';
import store from "../../store/store";
import {observer} from "mobx-react-lite";
import {initialGameData} from "../../static_data/GameData";
import Keyboard from "./Keyboard";
import Tree from '../../img/Tree.png'
import GameField from "./GameField";
import {ReactComponent as MessageAngle} from '../../img/Message_angle.svg'
// import {getAllInfo} from "../../http/wordsAPI";

const Game = () => {
    React.useEffect(() => {
        async function fetchData() {
            // const {data} = await getAllInfo()
            let data = initialGameData
            console.log(data)
            store.setMainInfo(data)
        }

        // fetchData()
    }, [])

    return (
        <div className="main-page__top">
            <div className="main-page__column">
                <div className="main-page__title"><h1>Игра «Наряди ёлку»</h1></div>
                <div className="main-page__text">
                    <span>Сегодня 19 число, вас ждёт задание в телеграм канале Благополучие</span>
                    <MessageAngle/>
                </div>
                <GameField/>
            </div>
            <div className="main-page__column">
                <img src={Tree} alt="Ёлочка"/>
            </div>
        </div>
    );
};

export default observer(Game);
