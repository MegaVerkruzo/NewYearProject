--liquibase formatted sql

--changeset GrunskiiAleksei:11
--comment add config table
CREATE TABLE IF NOT EXISTS configs
(
    id_name       VARCHAR(255) NOT NULL PRIMARY KEY,
    str_property  VARCHAR(255),
    date_property TIMESTAMP,
    long_property BIGINT
);
--rollback DROP TABLE configs;

--changeset GrunskiiAleksei:12
--comment delete phone column
ALTER TABLE attempts
    DROP COLUMN phone;
--rollback ALTER TABLE attempts ADD COLUMN phone VARCHAR(255);

--changeset GrunskiiAleksei:13
--comment add id_user
ALTER TABLE attempts
    ADD COLUMN id_user INTEGER REFERENCES users (id);
--rollback ALTER TABLE attempts DROP COLUMN id_user;

--changeset GrunskiiAleksei:14
--comment add row
INSERT INTO configs (id_name, long_property)
VALUES ('answers_delta', 1);
--rollback DELETE FROM configs WHERE id_name = 'answers_delta';

--changeset GrunskiiAleksei:15
--comment change id user type
ALTER TABLE attempts
    ALTER COLUMN id_user TYPE BIGINT;
--rollback ALTER TABLE attempts ALTER COLUMN id_user TYPE INTEGER;

--changeset GrunskiiAleksei:16
--comment change name
UPDATE configs
SET id_name = 'answers_delta_minutes'
WHERE id_name = 'answers_delta';
