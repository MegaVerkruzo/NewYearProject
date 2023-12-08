--liquibase formatted sql

--changeset GrunskiiAleksei:7
--comment add new username column to users table
ALTER TABLE users
    ADD COLUMN username VARCHAR(255)
--rollback ALTER TABLE users DROP COLUMN username
