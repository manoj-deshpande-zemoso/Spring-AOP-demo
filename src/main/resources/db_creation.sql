create database emp_db;
\c emp_db;
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
SET timezone TO 'UTC';
CREATE USER emp_user WITH PASSWORD 'admin';
GRANT ALL PRIVILEGES  ON emp_db books TO emp_user;