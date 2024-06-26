INSERT INTO user (email, password, is_active) VALUES ('admin@xyz.com', '{bcrypt}$2a$10$DowcNoxZ4H9g8B9nx0Wye.uJmRtnV6eGzLNPZAN3RhzyUMThyF4aK', true);
INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);
