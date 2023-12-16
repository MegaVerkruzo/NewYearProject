--liquibase formatted sql

--changeset GrunskiiAleksei:1
--comment add users table
CREATE TABLE IF NOT EXISTS users
(
    id            BIGINT       NOT NULL PRIMARY KEY,
    username      VARCHAR(255) NOT NULL UNIQUE,
    ticket_number SERIAL       NOT NULL UNIQUE,
    phone         VARCHAR(15)  NOT NULL,
    name          VARCHAR(255) NOT NULL,
    surname       VARCHAR(255) NOT NULL,
    middle_name   VARCHAR(255) NOT NULL,
    email         VARCHAR(255) NOT NULL,
    place         VARCHAR(255) NOT NULL,
    division      VARCHAR(255) NOT NULL,
    active_gifts  INTEGER      NOT NULL DEFAULT 0
);
--rollback DROP TABLE users

--changeset GrunskiiAleksei:2
--comment add answers table
CREATE TABLE IF NOT EXISTS task
(
    id                BIGINT       NOT NULL PRIMARY KEY,
    word              VARCHAR(200) NOT NULL,
    active_prizes     VARCHAR(255),
    non_active_prizes VARCHAR(255)
);
--rollback DROP TABLE task

--changeset GrunskiiAleksei:3
--comment add attempt table
CREATE TABLE IF NOT EXISTS attempt
(
    id      BIGINT                   NOT NULL PRIMARY KEY,
    user_id BIGINT REFERENCES users (id),
    word    VARCHAR(200)             NOT NULL,
    date    TIMESTAMP WITH TIME ZONE NOT NULL
);
--rollback DROP TABLE attempt

--changeset GrunskiiAleksei:4
--comment add config table
CREATE TABLE IF NOT EXISTS config
(
    id               VARCHAR(255) NOT NULL PRIMARY KEY,
    str_property     VARCHAR(255),
    date_property    TIMESTAMP WITH TIME ZONE,
    long_property    BIGINT,
    boolean_property BOOLEAN
);
--rollback DROP TABLE config;

--changeset GrunskiiAleksei:5
--comment add row
INSERT INTO config (id, long_property)
VALUES ('answers_delta_minutes', 1),
       ('tasks_count', 5);
INSERT INTO config (id, date_property)
VALUES ('date_start_game', NOW()),
       ('lottery_date', NOW());
INSERT INTO config (id, boolean_property)
VALUES ('is_lottery_finish', FALSE);
--rollback TRUNCATE TABLE config;

--changeset GrunskiiAleksei:6
--comment create attempts sequence
CREATE SEQUENCE attempt_seq START WITH 1 INCREMENT BY 1;
--rollback DROP SEQUENCE attempt_seq;

--changeset GrunskiiAleksei:7
--comment make task table reusing answers
CREATE SEQUENCE task_seq START WITH 1 INCREMENT BY 1;
--rollback DROP SEQUENCE task_seq;

--changeset GrunskiiAleksei:8
--comment make task table reusing answers
CREATE SEQUENCE feedback_seq START WITH 1 INCREMENT BY 1;
--rollback DROP SEQUENCE feedback_seq;

--changeset GrunskiiAleksei:9
--comment add feedback table
CREATE TABLE IF NOT EXISTS feedback
(
    id       BIGINT NOT NULL PRIMARY KEY,
    user_id  BIGINT REFERENCES users (id),
    task_id  BIGINT REFERENCES task (id),
    response TEXT
);
--rollback DROP TABLE feedback;

--changeset GrunskiiAleksei:10
--comment createIndex
CREATE UNIQUE INDEX IF NOT EXISTS ids_user_task_feedback
    ON feedback (user_id, task_id);
--rollback DROP INDEX IF EXISTS ids_user_task_feedback

--changeset GrunskiiAleksei:11
--comment pasteWords
INSERT INTO task (id, word, active_prizes, non_active_prizes)
VALUES (1, 'Атмосфера', '', ''),
       (2, 'Забота', '', ''),
       (3, 'Детство', '', ''),
       (4, 'Комфорт', '', ''),
       (5, 'Каталог', '', '')
--rollback TRUNCATE TABLE task;
