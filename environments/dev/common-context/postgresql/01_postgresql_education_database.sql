CREATE DATABASE education ENCODING 'UTF8';
CREATE USER liquibase_education with PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE education TO liquibase_education;
CREATE USER coursesdb WITH PASSWORD 'password';
GRANT CONNECT ON DATABASE education TO coursesdb;
GRANT SELECT, UPDATE, INSERT, DELETE ON ALL TABLES IN SCHEMA public TO coursesdb;
GRANT USAGE, SELECT ON ALL SEQUENCES IN SCHEMA public TO coursesdb;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA public to coursesdb;