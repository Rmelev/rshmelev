CREATE DATABASE store;

CREATE TABLE IF NOT EXISTS roles (
	role_id serial PRIMARY KEY,
	role VARCHAR(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS music_type (
	mtype_id serial PRIMARY KEY,
	mtype VARCHAR(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS users (
	user_id serial PRIMARY KEY,
	login VARCHAR(255) UNIQUE,
	name VARCHAR(255),
	password VARCHAR(255),
	role INTEGER REFERENCES roles(role_id),
	address INTEGER UNIQUE
);

CREATE TABLE IF NOT EXISTS address (
	address_id serial PRIMARY KEY,
	city VARCHAR(255),
	street VARCHAR(255),
	house VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS comm_user_mtype (
	comm_id serial PRIMARY KEY,
	user_id INTEGER REFERENCES users(user_id),
	mtype_id INTEGER REFERENCES music_type(mtype_id)
);

ALTER TABLE users ADD CONSTRAINT ref_address 
FOREIGN KEY (address)
REFERENCES address(address_id);

insert into roles (role)
values ('USER'), ('MANDATOR'), ('ADMIN')
on conflict do nothing;

insert into music_type (mtype)
values ('RAP'), ('ROCK'), ('JAZZ'), ('SWING')
on conflict do nothing;




