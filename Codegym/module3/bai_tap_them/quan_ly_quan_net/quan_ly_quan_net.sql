create database quan_ly_phong_game;

use quan_ly_phong_game;

drop database quan_ly_phong_game;

alter table su_dung_dich_vu
add tong_tien int;


create table loai_khach_hang (
	ma_loai_khach_hang int primary key,
    loai_khach_hang varchar(50)
);

create table khach_hang (
	ma_khach_hang int primary key,
    ten varchar(255),
    so_dien_thoai varchar(100),
    email varchar(100),
    ma_loai_khach_hang int,
    foreign key(ma_loai_khach_hang) references loai_khach_hang(ma_loai_khach_hang)
);

create table hang_san_xuat (
    ma_hang int primary key,
    ten_hang varchar(100)
);

drop table su_dung_dich_vu;
drop table chi_tiet_su_dung;
drop table may_tinh;

create table may_tinh (
    ma_may int primary key,
    ma_hang int,
    vi_tri_dat_may varchar(100),
    foreign key (ma_hang) references hang_san_xuat(ma_hang)
);

create table su_dung_dich_vu (
	ma_su_dung_dich_vu int primary key,
    thoi_gian_bat_dau datetime,
    thoi_gian_ket_thuc datetime,
    ma_khach_hang int,
    ma_may int,
    foreign key(ma_khach_hang) references khach_hang(ma_khach_hang),
    foreign key(ma_may) references may_tinh(ma_may)
);

create table dich_vu_di_kem (
	ma_dich_vu int primary key,
    ten_dich_vu varchar(100),
    gia_dich_vu int
);

create table chi_tiet_su_dung (
	ma_su_dung_dich_vu int,
    ma_dich_vu int,
    so_luong int,
    primary key (ma_su_dung_dich_vu, ma_dich_vu),
    foreign key (ma_su_dung_dich_vu) references su_dung_dich_vu(ma_su_dung_dich_vu),
    foreign key (ma_dich_vu) references dich_vu_di_kem(ma_dich_vu)
);

insert into loai_khach_hang values 
(1, 'VIP'),
(2, 'Member'),
(3, 'Other');

insert into khach_hang values 
(001, 'Huynh Minh Huy', '0123456789', 'huy@gmail.com', 1),
(002, 'Tran Trung Chien', '0123456789', 'chien@gmail.com', 2),
(003, 'Nguyen Duc Vinh', '0123456789', 'vinh@gmail.com', 2),
(004, 'Phan Ta Anh Vuong', '0123456789', 'vuong@gmail.com', 3),
(005, 'Ton That Duy', '0123456789', 'duy@gmail.com', 1);

insert into hang_san_xuat values
(1, 'Dell'),
(2, 'HP'),
(3, 'ASUS'),
(4, 'MSI'),
(5, 'ACER'),
(6, 'VICTUS');

insert into may_tinh values 
(1, 1, 'T1 - Goc trai phong'),
(2, 2, 'T1 - Goc phai phong'),
(3, 3, 'T2 - Goc trai phong'),
(4, 4, 'T2 - Goc trong cung'),
(5, 5, 'T3 - Goc phai phong'),
(6, 6, 'T3 - Goc trai phong');

insert into dich_vu_di_kem values 
(1, 'Nuoc ngot', 20000),
(2, 'Banh Mi', 15000),
(3, 'Mi Tom', 10000);

insert into su_dung_dich_vu values 
(1, '2024-05-01 09:00:00', '2024-05-01 11:00:00', 001, 1, 60000),
(2, '2024-05-02 10:00:00', '2024-05-02 12:00:00', 002, 2, 70000);

insert into chi_tiet_su_dung values 
(1, 1, 1),  -- luot 1 dung 1 nuoc ngot
(1, 3, 2),  -- luot 1 dung 2 my tom 
(2, 2, 1);  -- luot 2 dung 1 banh 

select * from chi_tiet_su_dung;
select * from dich_vu_di_kem;
select * from khach_hang;
select * from loai_khach_hang;
select * from hang_san_xuat;
select * from may_tinh;
select * from su_dung_dich_vu;





