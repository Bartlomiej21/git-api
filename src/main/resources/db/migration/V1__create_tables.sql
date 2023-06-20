CREATE SCHEMA if NOT EXISTS github;

CREATE TABLE IF NOT EXISTS users (
  id bigserial NOT NULL PRIMARY KEY,
  login VARCHAR(255),
  request_count INT DEFAULT 0
);