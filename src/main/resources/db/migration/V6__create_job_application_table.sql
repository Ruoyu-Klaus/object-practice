CREATE TABLE job_application
(
    id           INT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    job_id       INT       NOT NULL,
    employer_id  INT       NOT NULL,
    jobseeker_id INT       NOT NULL,
    resume_id    INT,
    status       VARCHAR(64)        DEFAULT 'SUBMITTED',
    apply_date   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);