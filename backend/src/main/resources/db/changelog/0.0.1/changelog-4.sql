--liquibase formatted sql

--changeset GrunskiiAleksei:5
--comment update id column in table attempts
ALTER TABLE attempts
    ALTER COLUMN id TYPE BIGINT
--rollback DROP TABLE users
