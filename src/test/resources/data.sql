create table operational_section (id INT AUTO_INCREMENT primary key, section_full_name VARCHAR(255),
section_short_name VARCHAR(255), section_conversational_name varchar(255));

create table operational_area (id INT auto_increment primary key, full_name varchar(255), short_name varchar(255),
conversational_name varchar(255), section_id INT);
alter table operational_area add foreign key (section_id) references operational_section (id);

create table equipment_type (id INT auto_increment primary key, machine_type varchar(255));

create table equipment (id int auto_increment primary key, type_id int, machine_model varchar(255),
manufacturer_country varchar(255), manufacturer varchar(255), manufacturing_year varchar(255),
machine_number varchar(255), details varchar(255), area_id int);
alter table equipment add foreign key (type_id) references equipment_type (id);

create table user (id INT auto_increment primary key, username varchar(255), password varchar(255));
create table role (id INT auto_increment primary key, name varchar(255));
create table user_roles (user_id INT not NULL, roles_id INT not null, constraint PK_user_roles primary key (user_id, roles_id));
alter table user_roles add foreign key (user_id) references user (id);
alter table user_roles add foreign key (roles_id) references role (id);
insert into role (name) values ('ROLE_USER'), ('ROLE_ADMIN');

--/* Строка ниже выполняется отдельно после регистрации первого пользователя. Дает админские права пользователю с user_id == 1.
--INSERT INTO user_roles(user_id, roles_id) VALUES (1, 2);
--*/