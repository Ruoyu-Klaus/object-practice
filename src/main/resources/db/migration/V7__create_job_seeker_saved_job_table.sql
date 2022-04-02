CREATE TABLE jobseeker_saved_job
(
    id           INT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    job_id       INT       NOT NULL,
    jobseeker_id INT       NOT NULL
);