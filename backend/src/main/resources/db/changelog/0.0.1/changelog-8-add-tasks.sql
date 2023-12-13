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

--changeset GrunskiiAleksei:22
--comment update configs table: renaming, add columns: 'start_game', 'count_tasks',
ALTER TABLE configs
    RENAME TO config;
INSERT INTO config (id_name) VALUES ('date_start_game');
INSERT INTO config (id_name) VALUES ('tasks_count');
--rollback ALTER TABLE config RENAME TO configs;
--rollback DELETE FROM config WHERE id_name = 'date_start_game';
--rollback DELETE FROM config WHERE id_name = 'tasks_count';
