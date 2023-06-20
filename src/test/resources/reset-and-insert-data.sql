CREATE TABLE IF NOT EXISTS users_test (
  id bigserial NOT NULL PRIMARY KEY,
  login VARCHAR(255),
  request_count INT DEFAULT 0
);

TRUNCATE TABLE users_test;

INSERT INTO users_test (id, login, request_count)
VALUES ('7073', 'master', 1),
('198047', 'animal', 3);
