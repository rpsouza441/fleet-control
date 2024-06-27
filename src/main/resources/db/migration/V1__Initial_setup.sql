-- Create the roles table
CREATE TABLE tb_role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Create the users table
CREATE TABLE tb_user_entity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    active BOOLEAN DEFAULT TRUE,
    roles_id BIGINT,
    CONSTRAINT fk_roles FOREIGN KEY (roles_id) REFERENCES tb_role(id)
);

-- Create the cars table
CREATE TABLE tb_car (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    license_plate VARCHAR(50) NOT NULL UNIQUE,
    model VARCHAR(100) NOT NULL,
    mileage BIGINT NOT NULL
);

-- Create the car logs table
CREATE TABLE tb_car_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    car_id BIGINT,
    user_id BIGINT,
    action VARCHAR(50),
    mileage BIGINT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (car_id) REFERENCES tb_car(id),
    FOREIGN KEY (user_id) REFERENCES tb_user_entity(id)
);

-- Insert default roles
INSERT INTO tb_role (name) VALUES ('ROLE_USER'), ('ROLE_ADMIN');
