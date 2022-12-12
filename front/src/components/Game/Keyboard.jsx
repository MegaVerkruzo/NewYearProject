import React from 'react';
import {keyboardData} from "../../static_data/GameData";
import KeyboardLetter from "./KeyboardLetter";

const Keyboard = () => {
    const onClickLetter = (key) => {
        console.log(key)
    }

    return (
        <div className="keyboard">
            {
                keyboardData.map(row => <div className="keyboard__row" key={row[0].id}>
                    {
                        row.map(item => <KeyboardLetter key={item.id}
                                                        letter={item.letter}
                                                        onClickLetter={() => onClickLetter(item.letter)}
                        />)
                    }
                </div>)
            }
        </div>
    );
};

export default Keyboard;
