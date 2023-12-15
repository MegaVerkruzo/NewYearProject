--liquibase formatted sql

--changeset GrunskiiAleksei:24
--comment make task table reusing answers
ALTER TABLE task
    DROP COLUMN date;
--rollback ALTER TABLE task ADD COLUMN date TIMESTAMP;

--changeset GrunskiiAleksei:25
--comment add bool to config
ALTER TABLE config
    ADD COLUMN boolean_property BOOLEAN;
--rollback  ALTER TABLE config DROP COLUMN boolean_property;

--changeset GrunskiiAleksei:26
--comment add new row
INSERT INTO config (id_name, boolean_property)
VALUES ('is_lottery_finish', FALSE);
--rollback DELETE FROM config WHERE id_name = 'is_lottery_finish';

--changeset GrunskiiAleksei:27
--comment add new column to users
ALTER TABLE users
    ADD COLUMN active_gifts INTEGER NOT NULL DEFAULT 0;
--rollback ALTER TABLE users DROP COLUMN active_gifts;
