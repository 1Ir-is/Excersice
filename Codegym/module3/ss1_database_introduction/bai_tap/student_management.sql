create database student_management;

use student_management;

create table Class (
	id int,
    name varchar(255),
    primary key(id)
);

create table Teacher (
	id int,
    name varchar(255),
    age int,
    country varchar(255),
    primary key(id)
);

select * from Class;

insert into Class (id, name) values 
(1,'10A1'),
(2,'10A2'),
(3,'11B1'),
(4,'12C1');


select * from Teacher;

insert into Teacher (id, name, age, country) values 
(1,'Nguyen Van An', 35, 'Vietnam'),
(2,'Tran Thi Binh', 42, 'Vietnam'),
(3,'John Smith', 38, 'USA'),
(4,'Nguyen Thi Lan', 29, 'Vietnam');

update Teacher
set name = 'Huynh Minh Huy'
where id = 1;

delete from Teacher where id = 4;
