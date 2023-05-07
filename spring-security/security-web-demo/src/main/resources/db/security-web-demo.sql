drop database if exists spring_security_demo;
create
    database `spring_security_demo` character set `utf8mb4`;
use `spring_security_demo`;

drop table if exists users;
create table users
(
    id       bigint primary key auto_increment,
    username varchar(20) unique not null,
    password varchar(100)
);
-- 密码 atguigu
insert into users
values (1, '张
san', '$2a$10$2R/M6iU3mCZt3ByG7kwYTeeW0w7/UqdeXrb27zkBIizBvAven0/na');
-- 密码 atguigu
insert into users
values (2, '李
si', '$2a$10$2R/M6iU3mCZt3ByG7kwYTeeW0w7/UqdeXrb27zkBIizBvAven0/na');
insert into users
values (3, 'test', 'test');
create table role
(
    id   bigint primary key auto_increment,
    name varchar(20)
);
insert into role
values (1, '管理员');
insert into role
values (2, '普通用户');
create table role_user
(
    uid bigint,
    rid bigint
);
insert into role_user
values (1, 1);
insert into role_user
values (2, 2);
create table menu
(
    id         bigint primary key auto_increment,
    name       varchar(20),
    url        varchar(100),
    parentid   bigint,
    permission varchar(20)
);
insert into menu
values (1, '系统管理', '', 0, 'menu:system');
insert into menu
values (2, '用户管理', '', 0, 'menu:user');
create table role_menu
(
    mid bigint,
    rid bigint
);
insert into role_menu
values (1, 1);
insert into role_menu
values (2, 1);
insert into role_menu
values (2, 2);

CREATE TABLE `persistent_logins`
(
    `username`  varchar(64) NOT NULL,
    `series`    varchar(64) NOT NULL,
    `token`     varchar(64) NOT NULL,
    `last_used` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE
        CURRENT_TIMESTAMP,
    PRIMARY KEY (`series`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;