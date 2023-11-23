create table f01_user (
    id bigint not null auto_increment,
    name varchar(255) not null,
    email varchar(255) not null,
    primary key(id)
);

insert into f01_user values (1, 'Fábio Faraco', 'fabio.faraco@mailinator.com');