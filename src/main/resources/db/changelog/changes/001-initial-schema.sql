CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL
);

ALTER TABLE users
ADD CONSTRAINT check_user_role CHECK
    (users.role IN ('ROLE_ADMIN', 'ROLE_POSTS', 'ROLE_USERS', 'ROLE_ALBUMS'));
