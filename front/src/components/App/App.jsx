import React, {useState} from "react";
import './App.css';
import Header from "../Header/Header";
import Main from "../Main/Main";
import Footer from "../Footer/Footer";
import {observer} from "mobx-react-lite";
import {check} from "../../http/userAPI";
import {useNavigate} from "react-router-dom";
import Keyboard from "../Game/Keyboard";
import IsLoading from "../Main/isLoading";
import store from "../../store/store";

const App = () => {
    let navigate = useNavigate()
    React.useEffect(() => {
        const fetchData = async () => {
            store.setIsLoading(true)
            if (localStorage.getItem('token')) {
                const data = await check()
                if (data?.token) {
                    localStorage.setItem('token', data.token)
                } else {
                    if (new Date() < new Date('2022-12-19T00:00:00.000+03:00')) {
                        navigate('/register')
                    } else {
                        navigate('/login')
                    }
                }
            } else {
                if (new Date() < new Date('2022-12-19T00:00:00.000+03:00')) {
                    navigate('/register')
                } else {
                    navigate('/login')
                }
            }
            store.setIsLoading(false)
        }
        fetchData()
    }, [])

    return (
        <div className="wrapper">
            {
                <>
                    <Header/>
                    {store.isLoading && <IsLoading/>}
                    <Main/>
                    <Keyboard/>
                    <Footer/>
                </>
            }
        </div>

    );
}

export default observer(App);
