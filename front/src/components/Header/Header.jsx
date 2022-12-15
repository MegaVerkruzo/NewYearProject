import React from 'react';
import {ReactComponent as Logo} from '../../img/Logo.svg'
import {ReactComponent as Sound} from '../../img/Sound.svg'
import {ReactComponent as NoSound} from '../../img/NoSound.svg'
import TimerBG from '../../img/TimerBG.png'
import TimerSnow from '../../img/TimerSnow.svg'
import store from "../../store/store";
import {observer} from "mobx-react-lite";

const Header = () => {
    const toggleIsSound = () => {
        store.setToggleIsSound()
    }

    return (
        <header className="header">
            <div className="container">
                <div className="header__row">
                    {window.innerWidth > 1140 ? <div className="header__top">
                        <div className="header__left">
                            <div className="header__logo">
                                <Logo/>
                            </div>
                        </div>
                        <div className="header__right">
                            <div className="header__burger"/>
                            <div className="header__help">
                                <a href="#">Снегурочка на связи</a>
                            </div>
                            <div className="header__sound">
                                <button className="sound__btn" onClick={toggleIsSound}>
                                    {store.isSound ? <Sound/> : <NoSound />}
                                </button>
                            </div>
                        </div>
                    </div> : <div className="header__top">
                        <div className="header__left">
                            <div className="header__sound">
                                <button className="sound__btn" onClick={toggleIsSound}>
                                    {store.isSound ? <Sound/> : <NoSound />}
                                </button>
                            </div>
                        </div>
                        <div>
                            <img src={TimerSnow} alt="Снежок" className="timer__snow"/>
                        </div>
                        <div className="header__right">
                            <div className="header__burger"><span/></div>
                        </div>
                    </div>}
                </div>
                <div className="header__row">
                    <div className="header__timer">
                        <img src={TimerBG} alt="Timer Background" className="timer_bg"/>
                        <div className="timer__title">
                            {window.innerWidth > 1140 ?
                                <img src={TimerSnow} alt="Снежок" className="timer__snow"/> : ''}
                            До нового года осталось:
                        </div>
                        <div className="timer__main">
                            <div className="timer__block">
                                <div className="timer__num">2</div>
                                <div className="timer__num">3</div>
                                <div className="timer__text">Дней</div>
                            </div>
                            <div className="timer__splitter">
                                <div className="splitter__circle"/>
                                <div className="splitter__circle"/>
                            </div>
                            <div className="timer__block">
                                <div className="timer__num">0</div>
                                <div className="timer__num">6</div>
                                <div className="timer__text">Часов</div>
                            </div>
                            <div className="timer__splitter">
                                <div className="splitter__circle"/>
                                <div className="splitter__circle"/>
                            </div>
                            <div className="timer__block">
                                <div className="timer__num">1</div>
                                <div className="timer__num">2</div>
                                <div className="timer__text">Минут</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
    );
};

export default observer(Header);
