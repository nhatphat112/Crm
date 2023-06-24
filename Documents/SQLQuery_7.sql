USE NhanSu
-- CREATE TABLE NhanVien 
-- (
--     MaNV VARCHAR(10) PRIMARY KEY,
--     TenNV NVARCHAR(50) NOT NULL,
--     NgaySinh DATE,
--     Nam BIT,
--     DiaChi NVARCHAR(100),
--     TienLuong FLOAT DEFAULT 1.0

-- )
-- ALTER Table NhanVien
-- ALTER COLUMN TienLuong FLOAT
-- Insert into NhanVien
-- (MaNV,TenNV,NgaySinh,Nam,DiaChi)
-- VALUES
-- (
--     '1',
--     N'Trường',
--     '20001023',
--     1,
--     N'Long Hồ'
-- )
INSERT into NhanVien
(MaNV,TenNV,NgaySinh,Nam,DiaChi)
VALUES
(
    '2',
    N'Tiến',
    '20001107',
    1,
    N'Tiền Giang'
)
INSERT into NhanVien
(MaNV,TenNV,NgaySinh,Nam,DiaChi)
VALUES
(
    '2',
    N'Tiến',
    '20001107',
    1,
    N'Tiền Giang'
)INSERT into NhanVien
(MaNV,TenNV,NgaySinh,Nam,DiaChi)
VALUES
(
    '3',
    N'Hiền',
    '20001107',
    0,
    N'Tiền Giang'
)

ALTER TABLE NhanVien
Update NhanVien SET TienLuong = 5000 WHERE TienLuong = 1