import React from 'react';
import cn from 'classnames'
import {ReactComponent as LeftArrow} from "../../img/left-arrow.svg";
import {ReactComponent as CheckMark} from "../../img/check-mark.svg";
import store from "../../store/store";
import {observer} from "mobx-react-lite";

const KeyboardLetter = ({letter, onClickLetter, id, state}) => {
    return (
        <button className={cn("keyboard__letter", state, {'active': id === 52 && store.isCanSendAttempt})}
                onClick={onClickLetter}>
            {id === 51 ? <LeftArrow/> : id === 52 ? <CheckMark/> : letter}
        </button>
    );
};

export default observer(KeyboardLetter);
