CREATE TABLE events (
    id BIGINT PRIMARY KEY,
    event_time TIMESTAMP NOT NULL,
    username VARCHAR(100),
    method VARCHAR(20) NOT NULL,
    code INT NOT NULL,
    uri VARCHAR(256) NOT NULL
);