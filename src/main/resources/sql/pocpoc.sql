SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--CREATE SCHEMA pocpoc;


SET search_path = pocpoc, pg_catalog;

SET default_with_oids = false;
CREATE TABLE account (
    id integer NOT NULL,
    nom text,
    prenom text,
    adresse text,
    tel text
);

CREATE SEQUENCE account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE account_id_seq OWNED BY account.id;

CREATE TABLE contrat (
    id integer NOT NULL,
    numero text,
    datedebut date,
    datefin date,
    tel text
);

CREATE SEQUENCE contrat_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER SEQUENCE contrat_id_seq OWNED BY contrat.id;
ALTER TABLE ONLY account ALTER COLUMN id SET DEFAULT nextval('account_id_seq'::regclass);
ALTER TABLE ONLY contrat ALTER COLUMN id SET DEFAULT nextval('contrat_id_seq'::regclass);
ALTER TABLE ONLY account ADD CONSTRAINT account_pkey PRIMARY KEY (id);