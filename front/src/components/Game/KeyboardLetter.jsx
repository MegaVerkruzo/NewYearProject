import React from 'react';

const KeyboardLetter = ({letter, onClickLetter}) => {
    return (
        <div className="keyboard__letter" onClick={onClickLetter}>
            {letter}
        </div>
    );
};

export default KeyboardLetter;
