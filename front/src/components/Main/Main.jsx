import React from 'react';
import {Routes, Route} from "react-router-dom";
import Login from "../Login/Login";
import Register from "../Login/Register";
import store from "../../store/store";

const Main = () => {
    return (
        <main className="main">
            <div className="container">
                <Routes>
                    <Route path="/login" element={<Login userData={store.userData}/>}/>
                    <Route path="/register" element={<Register userData={store.userData}/>}/>
                </Routes>
            </div>
        </main>
    );
};

export default Main;
