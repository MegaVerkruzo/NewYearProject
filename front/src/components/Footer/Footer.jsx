import React from 'react';
import {ReactComponent as Logo} from "../../img/Logo.svg";
import {ReactComponent as Telegram} from "../../img/Telega.svg";

const Footer = () => {
    return (
        <footer className="footer">
            <div className="container">
                {window.innerWidth > 777 ?
                    <div className="footer__main">
                        <div className="footer__column">
                            <Logo/>
                        </div>
                        <div className="footer__column">
                            <div className="footer__header">
                                Меню
                            </div>
                        </div>
                        <div className="footer__column">
                            <div className="footer__header">
                                Техподдержка
                            </div>
                        </div>
                        <div className="footer__column">
                            <div className="footer__header">
                                Мы в соцсетях
                            </div>
                        </div>
                        <div className="footer__column">
                            <div className="footer__text text__light">
                                ©2022 «Благополучие»
                            </div>
                            <div className="footer__text text__light">
                                Все права защищены
                            </div>
                            <div className="footer__text">
                                <a href="https://app.simplenote.com/p/XV73L9" target="_blank" className="underline">
                                    Политика конфиденциальности
                                </a>
                            </div>
                        </div>
                        <div className="footer__column">
                            <div className="footer__text">
                                <a href="https://app.simplenote.com/p/tmCzZ4" target="_blank">Правила игры</a>
                            </div>
                        </div>
                        <div className="footer__column">
                            <div className="footer__text">
                                <a href="https://t.me/blago2023" target="_blank">
                                    Снегурочка на связи
                                </a>
                            </div>
                        </div>
                        <div className="footer__column">
                            <div className="footer__icon">
                                <a href="https://t.me/+QnoomH1zp9k0OGUy" target="_blank">
                                    <Telegram/>
                                </a>
                            </div>
                        </div>
                    </div> : <div className="footer__main">
                        <div className="footer__row">
                            <Logo/>
                        </div>
                        <div className="footer__row">
                            <div className="footer__columns">
                                <div className="footer__column">
                                    <div className="footer__header">
                                        Меню
                                    </div>
                                    <div className="footer__text">
                                        <a href="https://app.simplenote.com/p/tmCzZ4" target="_blank">Правила игры</a>
                                    </div>
                                </div>

                                <div className="footer__column">
                                    <div className="footer__header">
                                        Техподдержка
                                    </div>
                                    <div className="footer__text">
                                        <a href="https://t.me/blago2023" target="_blank">
                                            Снегурочка на связи
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div className="footer__row">
                            <div className="footer__icon">
                                <a href="https://t.me/+QnoomH1zp9k0OGUy" target="_blank">
                                    <Telegram/>
                                </a>
                            </div>
                        </div>
                        <div className="footer__row">
                            <div className="footer__column">
                                <div className="footer__text">
                                    <a href="https://app.simplenote.com/p/XV73L9" target="_blank" className="underline">
                                        Политика конфиденциальности
                                    </a>
                                </div>
                                <div className="footer__text text__light">
                                    ©2022 «Благополучие»
                                </div>
                                <div className="footer__text text__light">
                                    Все права защищены
                                </div>
                            </div>
                        </div>
                    </div>}
            </div>
        </footer>
    );
};

export default Footer;
