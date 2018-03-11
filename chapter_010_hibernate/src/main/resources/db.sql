CREATE DATABASE carstore;

CREATE TABLE body (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE transmission (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE engine (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE brand (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  login VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE model (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE,
  id_brand INTEGER REFERENCES brand(id) NOT NULL
);

CREATE TABLE cars (
  id SERIAL PRIMARY KEY,
  color VARCHAR(255),
  id_model INTEGER REFERENCES model(id) NOT NULL,
  id_body INTEGER REFERENCES body(id) NOT NULL,
  id_transmission INTEGER REFERENCES transmission(id) NOT NULL,
  id_engine INTEGER REFERENCES engine(id) NOT NULL
);

CREATE TABLE orders (
  id SERIAL PRIMARY KEY,
  description VARCHAR(255),
  price INTEGER,
  sold BOOLEAN,
  date TIMESTAMP DEFAULT now(),
  id_user INTEGER NOT NULL,
  id_car INTEGER NOT NULL
);

CREATE TABLE images (
  id SERIAL PRIMARY KEY,
  url VARCHAR(255) NOT NULL,
  id_order INTEGER REFERENCES orders(id) NOT NULL
);

INSERT INTO body(name) VALUES ('sedan');
INSERT INTO body(name) VALUES ('jeep');
INSERT INTO body(name) VALUES ('coupe');

INSERT INTO transmission(name) VALUES ('mechanic');
INSERT INTO transmission(name) VALUES ('auto');
INSERT INTO transmission(name) VALUES ('musk');

INSERT INTO engine(name) VALUES ('gas');
INSERT INTO engine(name) VALUES ('diesel');
INSERT INTO engine(name) VALUES ('hybrid');

INSERT INTO brand(name) VALUES ('BMW');
INSERT INTO brand(name) VALUES ('Audi');
INSERT INTO brand(name) VALUES ('Tesla');

INSERT INTO model (name, id_brand) VALUES ('745i', 1);
INSERT INTO model (name, id_brand) VALUES ('X6', 1);
INSERT INTO model (name, id_brand) VALUES ('A5', 2);
INSERT INTO model (name, id_brand) VALUES ('A8', 2);
INSERT INTO model (name, id_brand) VALUES ('Q7', 2);
INSERT INTO model (name, id_brand) VALUES ('Model S', 3);
INSERT INTO model (name, id_brand) VALUES ('Model X', 3);
INSERT INTO model (name, id_brand) VALUES ('Model Fire', 3);
