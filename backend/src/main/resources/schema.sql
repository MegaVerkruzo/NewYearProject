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
    id           SERIAL PRIMARY KEY,
    phone        VARCHAR(15) UNIQUE NOT NULL,
    word         VARCHAR(255)       NOT NULL,
    countAttempt INTEGER            NOT NULL,
    dayOfWeek        VARCHAR(255)       NOT NULL
);
