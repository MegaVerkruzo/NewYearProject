--liquibase formatted sql

--changeset GrunskiiAleksei:5
--comment update id column in table answers
ALTER TABLE answers
    ALTER COLUMN id TYPE BIGINT
--rollback DROP TABLE users
