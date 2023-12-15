--liquibase formatted sql

--changeset GrunskiiAleksei:0
--comment drop tables
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS attempts;
DROP TABLE IF EXISTS users;
