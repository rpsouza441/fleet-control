-- Create the roles table
CREATE TABLE role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

-- Create the users table
CREATE TABLE user_entity (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    active BOOLEAN DEFAULT TRUE
);

-- Create the user_roles join table
CREATE TABLE user_roles (
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES user_entity(id),
    FOREIGN KEY (role_id) REFERENCES role(id)
);

-- Create the cars table
CREATE TABLE car (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    license_plate VARCHAR(50) NOT NULL UNIQUE,
    model VARCHAR(100) NOT NULL,
    mileage BIGINT NOT NULL
);

-- Create the car logs table
CREATE TABLE car_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    car_id BIGINT,
    user_id BIGINT,
    action VARCHAR(50),
    mileage BIGINT,
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (car_id) REFERENCES car(id),
    FOREIGN KEY (user_id) REFERENCES user_entity(id)
);

-- Insert default roles
INSERT INTO role (name) VALUES ('USER'), ('ADMIN');
