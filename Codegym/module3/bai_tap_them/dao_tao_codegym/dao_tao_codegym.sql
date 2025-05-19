create database codegym_management;

use codegym_management;

drop database codegym_management;

create table class (
	class_id int auto_increment primary key,
    class_name varchar(255)
);

create table student (
	student_id int auto_increment primary key,
    name varchar(255),
    birth_date date,
    gender boolean,
    email varchar(255),
    score float,
    class_id int,
    foreign key(class_id) references class(class_id)
);

create table account (
	account_id int auto_increment primary key,
    user_name varchar(255),
    password varchar(255),
    student_id int unique,
    foreign key(student_id) references student(student_id)
);

create table room_type (
	room_type_id int auto_increment primary key,
    type_name varchar(100)
);

create table room (
	room_id int auto_increment primary key,
    room_name varchar(100),
    class_id int,
    room_type_id int,
    foreign key(class_id) references class(class_id),
    foreign key(room_type_id) references room_type(room_type_id)
);

create table teacher (
	teacher_id int auto_increment primary key,
    name varchar(100),
    birth_date date,
    salary float
);

create table teacher_class (
	class_id int,
    teacher_id int,
    primary key(class_id, teacher_id),
    foreign key(class_id) references class(class_id),
    foreign key(teacher_id) references teacher(teacher_id)
);

select * from class;
select * from student;

insert into class (class_name) values 
('Java Web Fullstack'), 
('PHP Laravel Backend');


