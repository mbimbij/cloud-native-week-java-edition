CREATE TABLE IF NOT EXISTS TODO_ITEMS
(
    id
    SERIAL
    PRIMARY
    KEY,
    name
    VARCHAR
(
    255
),
    state VARCHAR
(
    10
)
    );