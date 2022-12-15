import React from 'react';

const GameField = () => {
    return (
        <div className="game-field">
            <div className="game-field__wrapper">
                <div className="game-field__cell grey">
                    <div className="letter">г</div>
                </div>
                <div className="game-field__cell green">
                    <div className="letter">е</div>
                </div>
                <div className="game-field__cell grey">
                    <div className="letter">т</div>
                </div>
                <div className="game-field__cell grey">
                    <div className="letter">т</div>
                </div>
                <div className="game-field__cell yellow">
                    <div className="letter">о</div>
                </div>

                <div className="game-field__cell empty"/>
                <div className="game-field__cell empty"/>
                <div className="game-field__cell empty"/>
                <div className="game-field__cell empty"/>
                <div className="game-field__cell empty"/>

                <div className="game-field__cell empty"/>
                <div className="game-field__cell empty"/>
                <div className="game-field__cell empty"/>
                <div className="game-field__cell empty"/>
                <div className="game-field__cell empty"/>

                <div className="game-field__cell empty"/>
                <div className="game-field__cell empty"/>
                <div className="game-field__cell empty"/>
                <div className="game-field__cell empty"/>
                <div className="game-field__cell empty"/>

                <div className="game-field__cell empty"/>
                <div className="game-field__cell empty"/>
                <div className="game-field__cell empty"/>
                <div className="game-field__cell empty"/>
                <div className="game-field__cell empty"/>
            </div>
            <button className="main-page__btn">Проверить</button>
            <div className="game-field__attempts">
                Попыток осталось: 5
            </div>
        </div>
    );
};

export default GameField;
