--liquibase formatted sql

--changeset GrunskiiAleksei:6
--comment update id column in table answers
ALTER TABLE answers
    ALTER COLUMN id TYPE BIGINT;
--rollback ALTER TABLE answers ALTER COLUMN id TYPE INTEGER

--changeset GrunskiiAleksei:7
--comment update id column in table attempts
ALTER TABLE attempts
    ALTER COLUMN id TYPE BIGINT;
--rollback ALTER TABLE attempts ALTER COLUMN id TYPE INTEGER

--changeset GrunskiiAleksei:8
--comment update id column in table users
ALTER TABLE users
    ALTER COLUMN id TYPE BIGINT;
--rollback ALTER TABLE users ALTER COLUMN id TYPE INTEGER
