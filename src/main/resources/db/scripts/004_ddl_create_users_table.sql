CREATE TABLE j_users
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(2000),
    role_id INT NOT NULL REFERENCES j_roles (id)
);