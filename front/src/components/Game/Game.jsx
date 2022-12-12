import React from 'react';
import store from "../../store/store";
import {observer} from "mobx-react-lite";
import {initialGameData} from "../../static_data/GameData";
import Keyboard from "./Keyboard";
// import {getAllInfo} from "../../http/wordsAPI";

const Game = () => {
    React.useEffect(() => {
        async function fetchData() {
            // const {data} = await getAllInfo()
            let data = initialGameData
            console.log(data)
            store.setMainInfo(data)
        }

        fetchData()
    }, [])

    return (
        <div>
            GAME PAGE
            <div className="game-field">
                {
                    store.attempts.map(row => <div className="field__row" key={row.id}>
                            {
                                row?.empty ? <></>
                                    : row.word.map(cell =>
                                        <div className="cell"
                                             key={`${row.id}_${cell.id}`}>
                                            {cell.letter}
                                        </div>)
                            }
                        </div>
                    )
                }
            </div>
            {window.innerWidth < 1024 ? <Keyboard/> : ''}
        </div>
    );
};

export default observer(Game);
