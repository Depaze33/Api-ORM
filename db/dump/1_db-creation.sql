DROP TABLE if exists public.account CASCADE;

/* TODO : ajouter la création de la table "client" */

CREATE TABLE client
(
    id UUID PRIMARY KEY,
    first_name VARCHAR(100),
    last_name  VARCHAR(50),
    email      VARCHAR(50),
    birthdate   date
);

CREATE TABLE account
(
    id           SERIAL PRIMARY KEY,
    creationTime timestamp,
    balance      bigint,
    id_client UUID references client (id)
);
