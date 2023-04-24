create table operational_section (id INT AUTO_INCREMENT primary key, section_full_name VARCHAR(255), section_short_name VARCHAR(255), section_conversational_name varchar(255));
insert into operational_section (section_full_name, section_short_name, section_conversational_name)
values ('TestSectionFullName1', 'TestSectionShortName1', 'TestSectionConversationalName1'),
('TestSectionFullName2', 'TestSectionShortName2', 'TestSectionConversationalName2'),
('TestSectionFullName3', 'TestSectionShortName3', 'TestSectionConversationalName3');

create table operational_area (id INT auto_increment primary key, full_name varchar(255), short_name varchar(255),
conversational_name varchar(255), section_id INT);
alter table operational_area add foreign key (section_id) references operational_section (id);
insert into operational_area (full_name, short_name, conversational_name, section_id)
values ('TestAreaFullName1', 'TestAreaShortName1','TestAreaConversationalName1','1'),
('TestAreaFullName2','TestAreaShortName2','TestAreaConversationalName2','1'),
('TestAreaFullName3','TestAreaShortName3','TestAreaConversationalName3','2'),
('TestAreaFullName4','TestAreaShortName4','TestAreaConversationalName4','3');

create table equipment_type (id INT auto_increment primary key, machine_type varchar(255));
insert into equipment_type (machine_type) values ('EquipmentType1'), ('EquipmentType2'), ('EquipmentType3');

create table equipment (id int auto_increment primary key, type_id int, machine_model varchar(255), manufacturer_country varchar(255), manufacturer varchar(255),
manufacturing_year varchar(255), machine_number varchar(255), details varchar(255), area_id int);
alter table equipment add foreign key (type_id) references equipment_type (id);
insert into equipment (machine_model, manufacturer_country , manufacturer, manufacturing_year, machine_number,
details, type_id, area_id) values
	('MachineModel1', 'ManufacturerCountry1', 'Manufacturer1', 'ManufacturingYear1', 'MachineNumber1', 'MachineDetails1', '1', '1'),
    ('MachineModel2', 'ManufacturerCountry2', 'Manufacturer2', 'ManufacturingYear2', 'MachineNumber2', 'MachineDetails2', '2', '2'),
    ('MachineModel3', 'ManufacturerCountry3', 'Manufacturer3', 'ManufacturingYear3', 'MachineNumber3', 'MachineDetails3', '3', '3');
    
create table user (id INT auto_increment primary key, username varchar(255), password varchar(255));
create table role (id INT auto_increment primary key, name varchar(255));
create table user_roles (user_id INT not NULL, roles_id INT not null, constraint PK_user_roles primary key (user_id, roles_id));
alter table user_roles add foreign key (user_id) references user (id);
alter table user_roles add foreign key (roles_id) references role (id);
insert into role (name) values ('ROLE_USER'), ('ROLE_ADMIN');

--/* Строка ниже выполняется отдельно после регистрации первого пользователя. Дает админские права пользователю с user_id == 1.
--INSERT INTO user_roles(user_id, roles_id) VALUES (1, 2);
--*/