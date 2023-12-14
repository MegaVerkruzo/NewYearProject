--liquibase formatted sql

--changeset GrunskiiAleksei:1
--comment add users table
CREATE TABLE IF NOT EXISTS users
(
    id          BIGINT NOT NULL PRIMARY KEY,
    phone       VARCHAR(15)                                      NOT NULL UNIQUE,
    name        VARCHAR(255)                                     NOT NULL,
    surname     VARCHAR(255)                                     NOT NULL,
    middle_name VARCHAR(255)                                     NOT NULL,
    email       VARCHAR(255)                                     NOT NULL,
    place       VARCHAR(255)                                     NOT NULL,
    token       VARCHAR(255)                                     NOT NULL UNIQUE
);
--rollback DROP TABLE users

--changeset GrunskiiAleksei:2
--comment add answers table
CREATE TABLE IF NOT EXISTS answers
(
    id          BIGINT NOT NULL PRIMARY KEY,
    word        VARCHAR(200)                                       NOT NULL,
    date        TIMESTAMP                                          NOT NULL,
    description TEXT
);
--rollback DROP TABLE answers

--changeset GrunskiiAleksei:3
--comment add attempts table
CREATE TABLE IF NOT EXISTS attempts
(
    id    BIGINT NOT NULL PRIMARY KEY,
    phone VARCHAR(15)                                         NOT NULL,
    word  VARCHAR(200)                                        NOT NULL,
    date  TIMESTAMP                                           NOT NULL
);
--rollback DROP TABLE attempts
