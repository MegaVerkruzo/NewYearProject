import React from 'react';
import {keyboardData} from "../../static_data/GameData";
import KeyboardLetter from "./KeyboardLetter";

const Keyboard = () => {
    const onClickLetter = (key) => {
        console.log(key)
    }

    const onClickEnter = () => {

    }

    const onClickDelete = () => {

    }

    return (
        <div className="keyboard">
            {
                keyboardData.map(row => <div className="keyboard__row" key={row[0].id}>
                    {
                        row.map(item => item.id < 50 ? <KeyboardLetter key={item.id}
                                                                       letter={item.letter}
                                                                       isBig={false}
                                                                       onClickLetter={() => onClickLetter(item.letter)}
                        /> : <KeyboardLetter key={item.id}
                                             letter={item.letter}
                                             isBig={true}
                                             onClickLetter={() => onClickLetter(item.letter)}
                        />)
                    }
                </div>)
            }
        </div>
    );
};

export default Keyboard;
