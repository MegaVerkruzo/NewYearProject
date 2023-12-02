CREATE TABLE IF NOT EXISTS users
(
    id            SERIAL PRIMARY KEY,
    phone         VARCHAR(15) UNIQUE  NOT NULL,
    "name"        VARCHAR(255)        NOT NULL,
    surname       VARCHAR(255)        NOT NULL,
    middle_name   VARCHAR(255)        NOT NULL,
    email         VARCHAR(255)        NOT NULL,
    place         VARCHAR(255)        NOT NULL,
    password_hash VARCHAR(255)        NOT NULL,
    token         VARCHAR(255) UNIQUE NOT NULL,
    feedback      TEXT
);

CREATE TABLE IF NOT EXISTS attempts
(
    id     SERIAL PRIMARY KEY,
    phone  VARCHAR(15)  NOT NULL,
    word   VARCHAR(200) NOT NULL,
    date TIMESTAMP       NOT NULL
);

CREATE TABLE IF NOT EXISTS answers
(
    id          SERIAL PRIMARY KEY,
    word        VARCHAR(200) NOT NULL,
    date        TIMESTAMP NOT NULL,
    description TEXT
);
