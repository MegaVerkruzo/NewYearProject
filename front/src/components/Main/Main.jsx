import React from 'react';
import {Routes, Route} from "react-router-dom";
import Login from "../Login/Login";
import Register from "../Login/Register";
import store from "../../store/store";
import Game from "../Game/Game";
import MainPage from "./MainPage";
import Rules from "./Rules";
import HowToPlay from "./HowToPlay";
import Meaning from "./Meaning";
import Feedback from "./Feedback";
import {observer} from "mobx-react-lite";

const isContainsTarget = (element) => {
    if (element.classList.contains('game-field__wrapper')) {
        return true;
    }
    if (element.classList.contains('main')) {
        return false;
    }

    return isContainsTarget(element.parentElement)
}

const Main = () => {
    return (
        <main className="main" onClick={(e) => {
            if (!store.isEnd) {
                isContainsTarget(e.target) ? store.setIsKeyBoardOpen(true) :
                    store.setIsKeyBoardOpen(false)
            }
        }}>
            <div className="main__content">
                <Routes>
                    <Route path="/" element={
                        <div className="main-page">
                            <div className="container">
                                <MainPage/>
                                <Rules/>
                            </div>
                        </div>
                    }/>
                    <Route path="/login" element={<Login userData={store.userData}/>}/>
                    <Route path="/register" element={<Register userData={store.userData}/>}/>
                    <Route path="/game" element={
                        <div className="main-page">
                            <div className="container">
                                <Game/>
                                {store.isEnd && <Meaning/>}
                                <Rules/>
                                <HowToPlay/>
                            </div>
                            {store.isPuttedFeedback && <Feedback/>}
                        </div>
                    }/>
                </Routes>
            </div>
        </main>
    );
};

export default observer(Main);
