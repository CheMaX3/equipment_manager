create database `pk-pik`;
use `pk-pik`;
create table operational_section (id INT auto_increment primary key, section_full_name VARCHAR(255), section_short_name VARCHAR(255), section_conversational_name varchar(255));
insert into operational_section (section_full_name, section_short_name, section_conversational_name) values ("Цех металлообработки", "ЦМО", "ЦМО"), 
("Цех продольной резки рулонов #1", "АПР 1","АПР 1"),
("Цех продольной резки рулонов #2","АПР 2","АПР 2"),
("Цех металлопроката","ЦМП","Прокатый цех"),
("Цех пресс-ножниц","","Будка"),
("Цех порошковой окраски","ЦПО","ЦПО"),
("Цех горячего цинкования","ЦГЦ-2","ЦГЦ-2");

create table operational_area (id INT auto_increment primary key, full_name varchar(255), short_name varchar(255), conversational_name varchar(255),
section_id INT);
alter table operational_area add foreign key (section_id) references operational_section (id);
insert into operational_area (full_name, short_name, conversational_name, section_id) values ("Цех металлообработки первый пролет","ЦМО 1-ый пролет","ЦМО 1-ый пролет","1"),
("Цех металлообработки второй пролет","ЦМО 2-ой пролет","ЦМО 2-ой пролет","1"),
("Цех металлообработки первый закуток","ЦМО закуток 1","ЦМО закуток 1","1"),
("Цех металлообработки второй закуток ","ЦМО закуток 2","ЦМО закуток 2","1"),
("Цех металлообработки пристрой","ЦМО пристрой","ЦМО пристрой","1"),
("Цех продольной резки рулонов #1","АПР 1","АПР 1","2"),
("Цех продольной резки рулонов #2","АПР 2","АПР 2","3"),
("Цех металлопроката","ЦМП","Прокатный цех","4"),
("Цех пресс-ножниц","","Будка","5"),
("Цех порошковой окраски","ЦПО","ЦПО","6"),
("Цех горячего цинкования второй пролет","ЦГЦ-2 2-ой пролет","ЦГЦ-2 2-ой пролет","7"),
("Цех горячего цинкования третий пролет","ЦГЦ-2 3-ий пролет","ЦГЦ-2 3-ий пролет","7");

create table equipment_type (id INT auto_increment primary key, machine_type varchar(255));
insert into equipment_type (machine_type) values ("Линия раскроя рулонов"), ("Станок раскроя листового металла"), ("Прокатный стан"), ("Линия мелкой детали"),
("Линия перфорации"), ("Листогиб"), ("Пресс кривошипный"), ("Координатно-пробивной пресс"), ("Вальцы гибочные"), ("Станок ленточнопильный"), ("Станок вертикально-сверлильный"),
("Труборез"), ("Трубогиб"), ("Линия автоматической сварки"), ("Пресс-ножницы комбинированные"), ("Пресс для правки"), ("Гильотинные ножницы");
create table equipment (id int auto_increment primary key, type_id int, machine_model varchar(255), manufacturer_country varchar(255), manufacturer varchar(255),
manufacturing_year varchar(255), machine_number varchar(255), details varchar(255), area_id int);
alter table equipment add foreign key (type_id) references equipment_type (id);