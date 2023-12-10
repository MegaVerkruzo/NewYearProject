--liquibase formatted sql

--changeset GrunskiiAleksei:9
--comment delete excess column password_hash
ALTER TABLE users
    DROP COLUMN password_hash;
--rollback ALTER TABLE users ADD COLUMN password_hash VARCHAR(255);

--changeset GrunskiiAleksei:10
--comment delete excess column token
ALTER TABLE users
    DROP COLUMN token;
--rollback TABLE users ADD COLUMN token VARCHAR(255);
