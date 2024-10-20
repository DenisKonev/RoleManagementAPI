DROP TABLE IF EXISTS roles CASCADE;

CREATE TABLE roles
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(50) UNIQUE NOT NULL,
    description VARCHAR(255)
);
