CREATE TABLE users
(
    id       SERIAL,
    name     VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE animals
(
    id            SERIAL,
    name          VARCHAR(50) NOT NULL,
    species       VARCHAR(50) NOT NULL,
    sex           VARCHAR(50) NOT NULL,
    date_of_birth VARCHAR(50) NOT NULL,
    user_id       INT REFERENCES users (id),
    PRIMARY KEY (id)
);