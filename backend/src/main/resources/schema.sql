DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id           SERIAL PRIMARY KEY,
    phone        VARCHAR(15)  NOT NULL,
    name         VARCHAR(255) NOT NULL,
    surname      VARCHAR(255) NOT NULL,
    middleName   VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL,
    place        VARCHAR(255) NOT NULL,
    passwordHash VARCHAR(255) NOT NULL
);