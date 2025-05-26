create database book_management;

drop database book_management;

use book_management;

-- Bảng vai trò
create table vai_tro (
    ma_vai_tro int primary key,
    ten_vai_tro varchar(50)
);

-- Bảng người dùng
create table nguoi_dung (
    ma_nguoi_dung int primary key auto_increment,
    ten varchar(100),
    email varchar(100) unique,
    mat_khau varchar(255),
    so_dien_thoai varchar(20),
    dia_chi varchar(255),
    ma_vai_tro int,
    foreign key (ma_vai_tro) references vai_tro(ma_vai_tro)
);

select * from vai_tro;
select * from nguoi_dung;


drop table nguoi_dung;
drop table vai_tro;
