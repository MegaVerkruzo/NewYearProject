DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    phone         VARCHAR(15) UNIQUE NOT NULL,
    "name"        VARCHAR(255)       NOT NULL,
    surname       VARCHAR(255)       NOT NULL,
    middle_name   VARCHAR(255)       NOT NULL,
    email         VARCHAR(255)       NOT NULL,
    place         VARCHAR(255)       NOT NULL,
    password_hash VARCHAR(255)       NOT NULL,
    token         VARCHAR(255)       NOT NULL,
    feedback      TEXT
);

CREATE TABLE attempts
(
    id            SERIAL PRIMARY KEY,
    phone         VARCHAR(15)  NOT NULL,
    word          VARCHAR(200) NOT NULL,
    count_attempt INTEGER      NOT NULL,
    day_of_month  INTEGER      NOT NULL
);

CREATE TABLE answers
(
    id           SERIAL PRIMARY KEY,
    word         VARCHAR(200) NOT NULL,
    day_of_month INTEGER      NOT NULL,
    post_link    VARCHAR(255),
    description  TEXT
);

CREATE TABLE russian_words
(
    id   SERIAL PRIMARY KEY,
    word VARCHAR(200) PRIMARY KEY
);
