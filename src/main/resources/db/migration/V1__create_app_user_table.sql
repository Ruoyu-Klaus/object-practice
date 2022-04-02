CREATE TABLE `app_user`
(
    id       INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(64) NOT NULL,
    password VARCHAR(64) NOT NULL,
    roles    VARCHAR(64)
);