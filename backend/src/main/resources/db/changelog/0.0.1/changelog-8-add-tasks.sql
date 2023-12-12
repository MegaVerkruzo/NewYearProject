--liquibase formatted sql

--changeset GrunskiiAleksei:20
--comment make task table reusing answers
ALTER TABLE answers
    RENAME TO task;
CREATE SEQUENCE task_seq START WITH 1 INCREMENT BY 1;
--rollback DROP TABLE task;
--rollback DROP SEQUENCE task_seq;

--changeset GrunskiiAleksei:21
--comment add new activePrizes, nonActivePrizes columns to task table
ALTER TABLE task
    ADD COLUMN active_prizes VARCHAR(255);
ALTER TABLE task
    ADD COLUMN non_active_prizes VARCHAR(255);
--rollback ALTER TABLE task DROP COLUMN activePrizes;
--rollback ALTER TABLE task DROP COLUMN nonActivePrizes;
