import React from 'react';

const HowToPlay = () => {
    return (
        <div className="main-page__how2play">
            <div className="how2play__title"><h1>Как проходит игра?</h1></div>
            <div className="how2play__main">
                <div className="how2play__container">
                    <div className="how2play__subtitle">
                        Если вы ввели слово, то каждая буква окраситься в соответсвующий цвет:
                    </div>
                    <div className="how2play__letters">
                        <div className="letter__item">
                            <div className="example__cell green">
                                <div className="letter">г</div>
                            </div>
                            <div className="letter__text">Зеленый цвет ячейки означает, что буква угадана.</div>
                        </div>
                        <div className="letter__item">
                            <div className="example__cell yellow">
                                <div className="letter">о</div>
                            </div>
                            <div className="letter__text">Желтый цвет ячейки означает, что буква угадана, но
                                стоит не на своем месте
                            </div>
                        </div>
                        <div className="letter__item">
                            <div className="example__cell gray">
                                <div className="letter">т</div>
                            </div>
                            <div className="letter__text">Серый цвет ячейки означает, что буквы нет в слове
                            </div>
                        </div>
                    </div>
                    <div className="how2play__example">
                        <div className="example__wrapper">
                            <div className="example__column">
                                <div className="example__title">Пример:</div>
                                <div className="example__subtitle">Загаданное слово: ВЬЮГА</div>
                            </div>
                            <div className="example__column">
                                <div className="example__grid">
                                    <div className="example__cell yellow">
                                        <div className="letter">г</div>
                                    </div>
                                    <div className="example__cell gray">
                                        <div className="letter">е</div>
                                    </div>
                                    <div className="example__cell gray">
                                        <div className="letter">р</div>
                                    </div>
                                    <div className="example__cell gray">
                                        <div className="letter">о</div>
                                    </div>
                                    <div className="example__cell gray">
                                        <div className="letter">й</div>
                                    </div>
                                    <div className="example__cell green">
                                        <div className="letter">в</div>
                                    </div>
                                    <div className="example__cell gray">
                                        <div className="letter">и</div>
                                    </div>
                                    <div className="example__cell gray">
                                        <div className="letter">л</div>
                                    </div>
                                    <div className="example__cell gray">
                                        <div className="letter">л</div>
                                    </div>
                                    <div className="example__cell gray">
                                        <div className="letter">а</div>
                                    </div>
                                    <div className="example__cell green">
                                        <div className="letter">в</div>
                                    </div>
                                    <div className="example__cell green">
                                        <div className="letter">ь</div>
                                    </div>
                                    <div className="example__cell green">
                                        <div className="letter">ю</div>
                                    </div>
                                    <div className="example__cell green">
                                        <div className="letter">г</div>
                                    </div>
                                    <div className="example__cell green">
                                        <div className="letter">а</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default HowToPlay;
