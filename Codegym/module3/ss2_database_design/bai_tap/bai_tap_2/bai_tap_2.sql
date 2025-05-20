create database sell_management;

use sell_management;

create table khach_hang (
	c_id int primary key auto_increment,
    c_name varchar(100),
    c_age int
);

create table san_pham (
	p_id int primary key auto_increment,
    p_name varchar(100),
    p_price float
);

create table don_hang (
	o_id int primary key auto_increment,
    o_date date,
    o_total_price float,
    c_id int,
    foreign key(c_id) references khach_hang(c_id)
);

create table chi_tiet_don_hang (
	o_id int,
    p_id int,
    od_quantity int,
    primary key(o_id, p_id),
    foreign key(o_id) references don_hang(o_id),
    foreign key(p_id) references san_pham(p_id)
);