create database demo_product;

use demo_product;

create table Products (
	id int primary key auto_increment,
    productCode varchar(50) not null,
    productName varchar(100),
    productPrice decimal(10,2),
    productAmount int,
    productDescription varchar(255),
    productStatus varchar(20)
);

insert into Products (productCode, productName, productPrice, productAmount, productDescription, productStatus) values
('P001', 'Keyboard', 150.00, 20, 'Mechanical keyboard', 'Available'),
('P002', 'Mouse', 50.00, 50, 'Wireless mouse', 'Available'),
('P003', 'Monitor', 300.00, 15, '24 inch HD monitor', 'Out of Stock'),
('P004', 'Laptop', 1200.00, 10, 'Gaming laptop', 'Available'),
('P005', 'Webcam', 80.00, 30, 'HD webcam', 'Available');

-- Tạo Unique Index trên bảng Products (sử dụng cột productCode để tạo chỉ mục)
create unique index idx_product_code on Products(productCode);

-- Tạo Composite Index trên bảng Products (sử dụng 2 cột productName và productPrice)
create index idx_product_name_price on Products(productName, productPrice);

-- Sử dụng câu lệnh EXPLAIN để biết được câu lệnh SQL của bạn thực thi như nào
explain select * from Products where productCode - "P002";

explain select * from Products where productName = 'Mouse' and productPrice = 50.00;

-- So sánh câu truy vấn trước và sau khi tạo index
/*  1. Sau khi tạo index, cột key trong kết quả EXPLAIN sẽ không còn là NULL.
	2. Loại bỏ full table scan, tăng tốc độ truy vấn. 
*/
 
-- Tạo view lấy về các thông tin: productCode, productName, productPrice, productStatus từ bảng products.
create view v_product_basic_info as
select productCode, productName, productPrice, productStatus
from Products;

-- Tiến hành sửa đổi view
create or replace view v_product_basic_info as
select productCode, productName, productStatus
from Products;

select * from v_product_basic_info;
-- Tiến hành xoá view
drop view v_product_basic_info;

-- Tạo store procedure lấy tất cả thông tin của tất cả các sản phẩm trong bảng product
delimiter //
create procedure sp_get_all_products()
begin
    select * from Products;
end;
//
delimiter ;

call sp_get_all_products();

-- Tạo store procedure thêm một sản phẩm mới
delimiter //
create procedure sp_add_product(
    in p_code varchar(50),
    in p_name varchar(100),
    in p_price decimal(10,2),
    in p_amount int,
    in p_desc text,
    in p_status varchar(20)
)
begin
    insert into Products(productCode, productName, productPrice, productAmount, productDescription, productStatus)
    values(p_code, p_name, p_price, p_amount, p_desc, p_status);
end;
//
delimiter ;

call sp_add_product('P006', 'MSI', 500, 30, 'Best of the best', 'Available');

-- Tạo store procedure sửa thông tin sản phẩm theo id
delimiter //
create procedure sp_update_product(
    in p_id int,
    in p_name varchar(100),
    in p_price decimal(10,2),
    in p_amount int,
    in p_desc text,
    in p_status varchar(20)
)
begin
    update Products
    set productName = p_name,
        productPrice = p_price,
        productAmount = p_amount,
        productDescription = p_desc,
        productStatus = p_status
    where id = p_id;
end;
//
delimiter ;
call sp_update_product(2,'Wireless Mouse Pro',60.00,45,'Upgraded wireless mouse','Available');

-- Tạo store procedure xoá sản phẩm theo id
delimiter //
create procedure sp_delete_product(in p_id int)
begin
	delete from Products where id = p_id;
end;
//
delimiter ;

call sp_delete_product(3);
