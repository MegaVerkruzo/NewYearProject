import React, {useState} from "react";
import './App.css';
import Header from "../Header/Header";
import Main from "../Main/Main";
import Footer from "../Footer/Footer";
import {observer} from "mobx-react-lite";
import {check} from "../../http/userAPI";
import store from "../../store/store";
import {useNavigate} from "react-router-dom";

const App = () => {
    const [isLoading, setIsLoading] = useState(false)
    let navigate = useNavigate()
    React.useEffect(() => {
        const fetchData = async () => {
            setIsLoading(true)
            if (localStorage.getItem('token')) {
                const data = await check()
                if (data?.token) {
                    localStorage.setItem('token', data.token)
                } else {
                    navigate('/login')
                    alert('Вы не авторизованы')
                }
            } else {
                navigate('/login')
                alert('Вы не авторизованы')
            }
            setIsLoading(false)
        }
        // fetchData()
    }, [])

    return (
        <div className="wrapper">
            {
                isLoading ? 'Загрузка' :
                    <>
                        <Header/>
                        <Main/>
                        <Footer/>
                    </>
            }
        </div>

    );
}

export default observer(App);
