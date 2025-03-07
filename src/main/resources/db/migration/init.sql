CREATE TABLE users {
    id SERIAL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(80) NOT NULL,
    email VARCHAR(50) UNIQUE,
    PRIMARY KEY (id)
}

CREATE TABLE roles {
    id SERIAL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
}

INSERT INTO roles (name)
VALUES
('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO users(username, password, email)
VALUES
('user', '$2a$12$x8xjQPE5F/yhtf3e48qwF.EbekQoqa21W60YanjNBe/sNbVgLfmzG', 'user@gmail.com'), -- todo crypt password
('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');

CREATE TABLE users_roles {
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERECES user (id),
    FOREIGN KEY (role_id) REFERECES user (id)
}

INSERT INTO users_roles(user_id,role_id)
VALUES
(1,1),
(2,2);
