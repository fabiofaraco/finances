create table transaction (
    id bigint not null auto_increment,
    description varchar(100),
    amount DOUBLE,
    date datetime,
    type varchar(20),
    primary key(id)
)