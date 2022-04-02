CREATE TABLE job
(
    id          INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title       VARCHAR(64) NOT NULL,
    type        VARCHAR(64) NOT NULL,
    employer_id INT         NOT NULL,
    post_date   TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);