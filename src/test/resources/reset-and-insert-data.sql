CREATE TABLE IF NOT EXISTS users (
  id bigserial NOT NULL PRIMARY KEY,
  login VARCHAR(255),
  request_count INT DEFAULT 0
);

TRUNCATE TABLE users;

INSERT INTO users (id, login, request_count)
VALUES ('7073', 'master', 1),
('198047', 'animal', 3);
