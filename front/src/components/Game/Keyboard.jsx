import React from 'react';
import KeyboardLetter from "./KeyboardLetter";
import cn from "classnames";
import store from "../../store/store";
import {observer} from "mobx-react-lite";
import {onDelete, onEnter, onWrite} from "./GameFunction";

const Keyboard = () => {
    const onClickLetter = (key) => {
        console.log(key)
        onWrite(key)
    }

    const onClickEnter = async () => {
        console.log('enter')
        onEnter()
    }

    const onClickDelete = () => {
        console.log('delete')
        onDelete()
    }

    return (
        <div className={cn("keyboard", {'open': store.isKeyboardOpen})}>
            {
                store.keyboardData.map(row => <div className="keyboard__row" key={row[0].id}>
                    {
                        row.map(item => item.id === 52 ? <KeyboardLetter key={item.id}
                                                                         id={item.id}
                                                                         letter={item.letter}
                                                                         onClickLetter={() => onClickEnter()}
                        /> : item.id === 51 ? <KeyboardLetter key={item.id}
                                                              id={item.id}
                                                              letter={item.letter}
                                                              onClickLetter={() => onClickDelete()}
                        /> : <KeyboardLetter key={item.id}
                                             id={item.id}
                                             state={item.state}
                                             letter={item.letter}
                                             onClickLetter={() => onClickLetter(item.letter)}/>)
                    }
                </div>)
            }
        </div>
    );
};

export default observer(Keyboard);
