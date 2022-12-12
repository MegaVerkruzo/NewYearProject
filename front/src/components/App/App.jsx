import React, {useState} from "react";
import './App.css';
import Header from "../Header/Header";
import Main from "../Main/Main";
import Footer from "../Footer/Footer";
import {observer} from "mobx-react-lite";
import {check} from "../../http/userAPI";
import store from "../../store/store";

const App = () => {
    const [isLoading, setIsLoading] = useState(false)
    React.useEffect(() => {
        // setIsLoading(true)
        // check().then(data => {
        //     store.setUserData(data)
        // }).finally(() => setIsLoading(false))
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
