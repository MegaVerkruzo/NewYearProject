import React from 'react';
import {Routes, Route} from "react-router-dom";
import Login from "../Login/Login";
import Register from "../Login/Register";
import store from "../../store/store";
import Game from "../Game/Game";
import MainPage from "./MainPage";
import Rules from "./Rules";
import HowToPlay from "./How2Play";
import Meaning from "./Meaning";
import Feedback from "./Feedback";

const Main = () => {
    return (
        <main className="main">
            <div className="container">
                <div className="main__content">
                    <Routes>
                        <Route path="/" element={
                            <div className="main-page">
                                <MainPage/>
                                <Rules/>
                            </div>
                        }/>
                        <Route path="/login" element={<Login userData={store.userData}/>}/>
                        <Route path="/register" element={<Register userData={store.userData}/>}/>
                        <Route path="/game" element={
                            <div className="main-page">
                                <Game/>
                                <Meaning/>
                                <Rules/>
                                <HowToPlay/>
                                <Feedback/>
                            </div>
                        }/>
                    </Routes>
                </div>
            </div>
        </main>
    );
};

export default Main;
