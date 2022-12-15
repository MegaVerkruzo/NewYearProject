import React from 'react';
import {ReactComponent as MessageAngle} from '../../img/Message_angle.svg'

const Meaning = () => {
    return (
        <div className="main-page__meaning">
            <div className="meaning__title"><h1>Значение слова</h1></div>
            <div className="meaning__text">
                <span>Геро́й — человек исключительной смелости и доблести, либо одно из главных действующих лиц литературного (литературный герой) или иного произведения культуры (кинематографический, игровой герой).</span>
                <MessageAngle />
            </div>
        </div>
    );
};

export default Meaning;
