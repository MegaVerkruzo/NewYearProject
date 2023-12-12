--liquibase formatted sql

--changeset GrunskiiAleksei:19
--comment drop users sequence
DROP SEQUENCE IF EXISTS users_seq;
--rollback CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1;;
