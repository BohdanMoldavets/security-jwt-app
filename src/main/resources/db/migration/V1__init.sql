CREATE TABLE users (
    id       SERIAL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(80) NOT NULL,
    email    VARCHAR(50) UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE roles (
    id   SERIAL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO roles (name)
VALUES
('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO users(username, password, email)
VALUES
('user', '$2a$10$onZox5u53DoJeYk0LM2bsuJ6yNj3rHX9c7QKkVJvsG38GHm1ISzFK', 'user@gmail.com'),
('admin', '$2a$10$onZox5u53DoJeYk0LM2bsuJ6yNj3rHX9c7QKkVJvsG38GHm1ISzFK', 'admin@gmail.com');


INSERT INTO users_roles(user_id,role_id)
VALUES
(1,2),
(2,1);
