import React from 'react';
import {observer} from "mobx-react-lite";
import store from "../../store/store";
import cn from "classnames";
import {getAllInfo} from "../../http/wordsAPI";
import {onEnter} from "./GameFunction";
import {useNavigate} from "react-router-dom";
import {ReactComponent as Spinner} from "../../img/spinner.svg";


const GameField = () => {
    let navigate = useNavigate()

    React.useEffect(() => {
        async function fetchData() {
            try {
                store.setIsFormLoading(true)
                const data = await getAllInfo()
                store.setIsFormLoading(false)
                if (data.exception === 'noUser') {
                    navigate('/login')
                } else {
                    store.setMainInfo(data)
                }
            } catch (e) {
                store.setIsFormLoading(false)
                store.setGameError('Произошла ошибка сервера')
            }
        }

        fetchData()
    }, [])

    return (
        <div className="game-field" id="game-field">
            <div className={"game-field__wrapper"}>
                {
                    store.attempts.map((item, index) =>
                        <div key={index}
                             className={cn("game-field__cell", {"gray": item.state === 'grey'},
                                 {"green": item.state === 'green'}, {"yellow": item.state === 'yellow'})}>
                            <div className="letter">{item.letter}</div>
                        </div>
                    )
                }
            </div>
            {store.gameError && <div className="error">{store.gameError}</div>}
            {store.isFormLoading && <div className="form_loading"><Spinner/></div>}
            {!store.isEnd && <div>
                <button className="main-page__btn" onClick={() => onEnter()}>Проверить</button>
                <div className="game-field__attempts">
                    Попыток осталось: {5 - store.currentAttempt.curRow}
                </div>
            </div>}
        </div>
    );
};

export default observer(GameField);
