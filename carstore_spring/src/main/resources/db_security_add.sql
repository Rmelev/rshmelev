CREATE TABLE roles (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO roles (name) VALUES ('GUEST');
INSERT INTO roles (name) VALUES ('USER');
INSERT INTO roles (name) VALUES ('ADMIN');

ALTER TABLE users ADD COLUMN role_id INTEGER;

ALTER TABLE users ADD CONSTRAINT roledist FOREIGN KEY (role_id) REFERENCES roles (id);

UPDATE users SET role_id = 3 WHERE id = 1;
UPDATE users SET role_id = 2 WHERE id = 2;
UPDATE users SET role_id = 1 WHERE id = 3;