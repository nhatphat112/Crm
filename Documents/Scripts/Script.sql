create database cybersoft_shop;

use cybersoft_shop;
create table loaisanpham (
	maloaisp bigint  auto_increment,
	tenloaisp varchar(255),
	primary key(maloaisp)
);
create table sanpham(
	masp bigint  auto_increment,
	mota text,
	tensp varchar(255),
	gia int,
	maloaisp bigint,
	primary key(masp)
);
create table chua(
	chua_id bigint  auto_increment,
	soluong int,
	giaban double,
	masp bigint,
	mahoadon bigint,
	primary key (chua_id)
);
create table hoadon(
	mahoadon bigint auto_increment,
	ngaymua timestamp default current_timestamp,
	makh bigint,
	primary key(mahoadon)
	
);
create table khachhang(
	makh bigint,
	diachi text,
	email varchar(255),
	ho varchar(50),
	ten varchar(50),
	sodt varchar(10),
	primary key(makh)
);
-- rang buoc khoa ngoai
alter table sanpham
add constraint FK_sanpham_maloaisp foreign key (maloaisp)
references loaisanpham(maloaisp)
on UPDATE cascade
on delete cascade;
alter table chua
add constraint FK_chua_masp foreign key (masp)
references sanpham(masp)
on UPDATE cascade
on delete cascade;
alter table chua
add constraint FK_chua_mahoadon foreign key (mahoadon)
references hoadon(mahoadon)
on UPDATE cascade
on delete cascade;
alter table hoadon
add constraint FK_hoadon_makh foreign key (makh)
references khachhang(makh)
on UPDATE cascade
on delete cascade;


use mybookstore;
select *
from customer   ;
DELETE FROM customer 
WHERE username ='nhatphat112';



