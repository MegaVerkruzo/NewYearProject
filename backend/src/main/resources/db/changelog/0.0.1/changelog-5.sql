--liquibase formatted sql

--changeset GrunskiiAleksei:5
--comment update id column in table users
ALTER TABLE users
    ALTER COLUMN id TYPE BIGINT
--rollback DROP TABLE users
