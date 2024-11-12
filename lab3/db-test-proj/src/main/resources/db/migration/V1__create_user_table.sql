CREATE TABLE users(
    user_id    serial PRIMARY KEY,
    first_name text                        NOT NULL,
    last_name  text                        NOT NULL,
    email      text                        NOT NULL UNIQUE,
    created_at timestamp WITHOUT TIME ZONE NOT NULL
)