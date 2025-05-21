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

insert into khach_hang (c_id, c_name, c_age) values
(1, 'Minh Quan', 10),
(2, 'Ngoc Oanh', 20),
(3, 'Hong Ha', 50);

insert into don_hang (o_id, c_id, o_date, o_total_price) values
(1, 1, '2006-03-21', NULL),
(2, 2, '2006-03-23', NULL),
(3, 1, '2006-03-16', NULL);


insert into san_pham (p_id, p_name, p_price) values
(1, 'May Giat', 3),
(2, 'Tu Lanh', 5),
(3, 'Dieu Hoa', 7),
(4, 'Quat', 1),
(5, 'Bep Dien', 2);


insert into chi_tiet_don_hang (o_id, p_id, od_quantity) values
(1, 1, 3),
(1, 3, 7),
(1, 4, 2),
(2, 1, 1),
(3, 1, 8),
(2, 5, 4),
(2, 3, 3);

-- 1. Hiển thị các thông tin  gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Order
select o_id, o_date, o_total_price
from don_hang;

-- 2. Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách
select 
	khach_hang.c_id as id,
    khach_hang.c_name as ten_khach_hang,
    khach_hang.c_age as tuoi,
    don_hang.o_id as order_id,
    don_hang.o_date as order_date,
    group_concat(san_pham.p_name separator ',') as san_pham_da_mua,
    sum(san_pham.p_price * chi_tiet_don_hang.od_quantity) as tong_tien
from khach_hang
join don_hang on khach_hang.c_id = don_hang.c_id
join chi_tiet_don_hang on don_hang.o_id = chi_tiet_don_hang.o_id
join san_pham on chi_tiet_don_hang.p_id = san_pham.p_id
group by khach_hang.c_id, khach_hang.c_name, khach_hang.c_age, don_hang.o_id, don_hang.o_date;

-- 3. Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào
select *
from khach_hang
where khach_hang.c_id not in (
	select distinct don_hang.c_id
    from don_hang
);

-- 4. Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn 
	-- (giá một hóa đơn được tính bằng tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn. 
	-- Giá bán của từng loại được tính = odQTY*pPrice)
select 
	don_hang.o_id as ma_hoa_don,
    don_hang.o_date as ngay_ban,
    group_concat(san_pham.p_name separator ',') as san_pham_da_mua,
    group_concat(concat(san_pham.p_name, ' x ', chi_tiet_don_hang.od_quantity) separator ', ' ) as so_luong_da_mua,
    sum(chi_tiet_don_hang.od_quantity * san_pham.p_price) as tong_tien
from don_hang
join chi_tiet_don_hang on don_hang.o_id = chi_tiet_don_hang.o_id
join san_pham on chi_tiet_don_hang.p_id = san_pham.p_id
group by don_hang.o_id, don_hang.o_date