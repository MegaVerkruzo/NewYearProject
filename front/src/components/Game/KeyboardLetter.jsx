import React from 'react';
import cn from 'classnames'

const KeyboardLetter = ({letter, onClickLetter, isBig}) => {
    return (
        <div className={cn("keyboard__letter", isBig ? 'big' : '')} onClick={onClickLetter}>
            {letter}
        </div>
    );
};

export default KeyboardLetter;
