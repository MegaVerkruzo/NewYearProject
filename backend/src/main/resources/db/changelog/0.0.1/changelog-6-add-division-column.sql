--liquibase formatted sql

--changeset GrunskiiAleksei:6
--comment add new division column to users table
ALTER TABLE users
    ADD COLUMN division VARCHAR(255)
--rollback ALTER TABLE users DROP COLUMN division
