import React, {useEffect} from 'react';
import {Routes, Route} from "react-router-dom";
import Login from "../Login/Login";
import Register from "../Login/Register";
import store from "../../store/store";

const Main = () => {
    useEffect(() => {
    }, [])

    return (
        <main className="main">
            <div className="container">
                <Routes>
                    <Route path="/login" element={<Login user_data={store.user_data}/>}/>
                    <Route path="/register" element={<Register user_data={store.user_data}/>}/>
                </Routes>
            </div>
        </main>
    );
};

export default Main;
