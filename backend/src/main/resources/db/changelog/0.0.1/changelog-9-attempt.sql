--liquibase formatted sql

--changeset GrunskiiAleksei:24
--comment make task table reusing answers
ALTER TABLE task
    DROP COLUMN date;
--rollback ALTER TABLE task ADD COLUMN date TIMESTAMP;

