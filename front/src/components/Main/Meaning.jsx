import React from 'react';
import {ReactComponent as MessageAngle} from '../../img/Message_angle.svg'
import store from "../../store/store";

const Meaning = () => {
    return (
        <div className="main-page__meaning">
            <div className="meaning__title"><h1>Значение слова</h1></div>
            <div className="meaning__text">
                <span>{store.description}</span>
                <MessageAngle />
            </div>
        </div>
    );
};

export default Meaning;
