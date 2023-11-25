import React from 'react';
import {observer} from "mobx-react-lite";
import store from "../../store/store";

const Timer = () => {
    const onTimerChange = () => {
        const diffInMs = new Date('2024-01-01T00:00:00.000+03:00') - new Date(new Date().toLocaleString('en', {timeZone: 'Europe/Moscow'}))
        const days = parseInt(diffInMs / (1000 * 60 * 60 * 24));
        const hours = parseInt(diffInMs / (1000 * 60 * 60)) % 24;
        const minutes = parseInt(diffInMs / (1000 * 60)) % 60;
        store.setUntilNewYear({days, hours, minutes})
    }

    React.useEffect(() => {
        onTimerChange()
        let newYearTimer = setInterval(() => {
            onTimerChange()
        }, 60000)

        return () => {
            window.clearInterval(newYearTimer);
        }
    }, [])
    return (
        <div className="timer__main">
            <div className="timer__block">
                <div className="timer__num">{parseInt(store.untilNewYear.days / 10)}</div>
                <div className="timer__num">{store.untilNewYear.days % 10}</div>
                <div className="timer__text">Дней</div>
            </div>
            <div className="timer__splitter">
                <div className="splitter__circle"/>
                <div className="splitter__circle"/>
            </div>
            <div className="timer__block">
                <div className="timer__num">{parseInt(store.untilNewYear.hours / 10)}</div>
                <div className="timer__num">{store.untilNewYear.hours % 10}</div>
                <div className="timer__text">Часов</div>
            </div>
            <div className="timer__splitter">
                <div className="splitter__circle"/>
                <div className="splitter__circle"/>
            </div>
            <div className="timer__block">
                <div className="timer__num">{parseInt(store.untilNewYear.minutes / 10)}</div>
                <div className="timer__num">{store.untilNewYear.minutes % 10}</div>
                <div className="timer__text">Минут</div>
            </div>
        </div>
    );
};

export default observer(Timer);
