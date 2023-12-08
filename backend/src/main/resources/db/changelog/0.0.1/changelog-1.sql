--liquibase formatted sql

--changeset GrunskiiAlexey:1
--comment first demo
CREATE TABLE demo
(
    id    INTEGER,
    title VARCHAR(32)
);

INSERT INTO demo
VALUES (1, 'First Title'),
       (2, 'Second Title');
--rollback DROP TABLE demo


