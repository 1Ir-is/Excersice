create database book_management;

drop database bookdatabase;

use bookdatabase;

select * from vai_tro;
select * from nguoi_dung;
select * from sach;
select * from danh_muc;
select * from don_hang;
select * from chi_tiet_don_hang;
SELECT * FROM nguoi_dung WHERE email = 'huy@gmail.com' AND mat_khau = '123';

-- Insert sample users
INSERT INTO nguoi_dung (ten, email, mat_khau, so_dien_thoai, dia_chi, ma_vai_tro, avatar_url, trang_thai)
VALUES 
('Nguyen Van A', 'a@example.com', 'password123', '0123456789', 'Hanoi', 1, NULL, TRUE),
('Tran Thi B', 'b@example.com', 'password123', '0987654321', 'Ho Chi Minh City', 1, NULL, TRUE);

-- Insert sample orders
INSERT INTO don_hang (ngay_dat, trang_thai, ma_nguoi_dung)
VALUES 
(CURDATE(), 'Processing', 2),
(CURDATE(), 'Processing', 2),
(CURDATE(), 'Processing', 6);

drop table don_hang;
drop table chi_tiet_don_hang;


-- Insert sample books
INSERT INTO sach (ten_sach, tac_gia, nha_xuat_ban, gia, mo_ta, ma_danh_muc, img_url, nam_xuat_ban)
VALUES 
('Book 1', 'Author 1', 'Publisher 1', 100.0, 'Description 1', 1, NULL, 2023),
('Book 2', 'Author 2', 'Publisher 2', 200.0, 'Description 2', 1, NULL, 2023);

-- Insert sample order details
INSERT INTO chi_tiet_don_hang (ma_don_hang, ma_sach, so_luong, gia)
VALUES 
(1, 3, 3, 300.0),  -- gộp số lượng và giá
(2, 3, 1, 100.0);

INSERT INTO chi_tiet_don_hang (ma_don_hang, ma_sach, so_luong, gia)
VALUES 
(3, 3, 1, 100.0);

INSERT INTO chi_tiet_don_hang (ma_don_hang, ma_sach, so_luong, gia)
VALUES 
(5, 3, 1, 100.0);

SELECT dh.ma_don_hang, 
       s.ten_sach,
       ctdh.so_luong,
       ctdh.gia,
       (ctdh.so_luong * ctdh.gia) AS thanh_tien
FROM don_hang dh
JOIN chi_tiet_don_hang ctdh ON dh.ma_don_hang = ctdh.ma_don_hang
JOIN sach s ON ctdh.ma_sach = s.ma_sach
WHERE s.ten_sach LIKE '%Trống Đồng%'
  AND dh.ma_don_hang = 1;

UPDATE chi_tiet_don_hang
SET gia = 100
WHERE ma_don_hang = 1 AND ma_sach = 3;

UPDATE don_hang
SET trang_thai = 'Đang xử lý'
WHERE ma_don_hang in (1,2,3);

UPDATE don_hang
SET trang_thai = 'Đang xử lý'
WHERE ma_don_hang = 1;

select * from don_hang;

drop table nguoi_dung;
drop table vai_tro;




