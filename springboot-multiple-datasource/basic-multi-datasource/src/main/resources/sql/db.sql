create
database multiple_datasource character set utf8mb4;
use
multiple_datasource;
drop table if exists multiple_datasource.tbl_user;
create table tbl_user
(
    id   int(11) primary key auto_increment,
    name varchar(50)
);
insert into tbl_user(name)
values ("lisi");

create
database multiple_datasource2 character set utf8mb4;
use
multiple_datasource2;
drop table if exists multiple_datasource2.tbl_user;
create table tbl_user
(
    id   int(11) primary key auto_increment,
    name varchar(50)
);

insert into tbl_user(name)
values ("zhangsan");