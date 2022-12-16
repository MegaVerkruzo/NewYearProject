import React from 'react';
import {ReactComponent as Logo} from '../../img/Logo.svg'
import {ReactComponent as Sound} from '../../img/Sound.svg'
import {ReactComponent as NoSound} from '../../img/NoSound.svg'
import TimerBG from '../../img/TimerBG.png'
import TimerSnow from '../../img/TimerSnow.svg'
import store from "../../store/store";
import {observer} from "mobx-react-lite";
import Timer from "./Timer";
import Music from "../../audio/AngelsOfLight.mp3";

// const onSetMusic = () => store.setMusic(Music)

const Header = () => {
    // const ref = React.useRef(null);

    // React.useEffect(() => {
        // ref.current.play()
        // ref.current.addEventListener('ended', onSetMusic);
        // return () => {
        //     ref.current.removeEventListener('ended', onSetMusic);
        // };
    // }, [])

    const toggleIsSound = () => {
        // console.log(ref.current)
        store.setToggleIsSound()
        // store.isSound ? ref.current.play() : ref.current.pause()
    }

    return (
        <header className="header">
            {/*<audio src={Music} ref={ref} muted={true}/>*/}
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
                                    {store.isSound ? <Sound/> : <NoSound/>}
                                </button>
                            </div>
                        </div>
                    </div> : <div className="header__top">
                        <div className="header__left">
                            <div className="header__sound">
                                <button className="sound__btn" onClick={toggleIsSound}>
                                    {store.isSound ? <Sound/> : <NoSound/>}
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
                        <Timer/>
                    </div>
                </div>
            </div>
        </header>
    );
};

export default observer(Header);
