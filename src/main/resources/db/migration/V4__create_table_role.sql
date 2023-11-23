create table role (
    `id` bigint not null auto_increment,
    `name` VARCHAR(50),
    PRIMARY KEY(`id`)
);

INSERT INTO role VALUES (1, 'READ_WRITE');