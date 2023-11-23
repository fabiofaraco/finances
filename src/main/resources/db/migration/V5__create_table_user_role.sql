CREATE TABLE f01_user_role (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT NOT NULL,
    `role_id` BIGINT NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`user_id`) REFERENCES f01_user(`id`),
    FOREIGN KEY(`role_id`) REFERENCES role(`id`)
);

INSERT INTO f01_user_role VALUES (1, 1, 1);