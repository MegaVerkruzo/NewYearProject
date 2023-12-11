--liquibase formatted sql

--changeset GrunskiiAleksei:17
--comment create users sequence
CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1;
--rollback DROP SEQUENCE users_seq;

--changeset GrunskiiAleksei:18
--comment create attempts sequence
CREATE SEQUENCE attempts_seq START WITH 1 INCREMENT BY 1;
--rollback DROP SEQUENCE attempts_seq;
