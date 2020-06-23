CREATE TABLE users
(
    id          BIGINT      NOT NULL,
    username    VARCHAR(25) NOT NULL,
    email       varchar(50) NOT NULL,
    first_name   varchar(50) NOT NULL,
    last_name    varchar(50) NOT NULL,
    birthDate   date        NOT NULL,
    password    varchar(128) NOT NULL,
    PRIMARY KEY (id)
);
CREATE SEQUENCE  hibernate_sequence;