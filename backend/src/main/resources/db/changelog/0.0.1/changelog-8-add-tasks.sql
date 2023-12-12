--liquibase formatted sql

--changeset GrunskiiAleksei:20
--comment make task table reusing answers
ALTER TABLE answers RENAME TO task;
CREATE SEQUENCE task_seq START WITH 1 INCREMENT BY 1;
--rollback DROP TABLE task;
--rollback DROP SEQUENCE task_seq;
