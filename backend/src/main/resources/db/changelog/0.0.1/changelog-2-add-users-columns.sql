--liquibase formatted sql

--changeset GrunskiiAleksei:4
--comment add new division column to users table
ALTER TABLE users
    ADD COLUMN division VARCHAR(255);
--rollback ALTER TABLE users DROP COLUMN division

--changeset GrunskiiAleksei:5
--comment add new username column to users table
ALTER TABLE users
    ADD COLUMN username VARCHAR(255);
--rollback ALTER TABLE users DROP COLUMN username
