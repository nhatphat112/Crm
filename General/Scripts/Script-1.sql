 -- create database
 create database if not exists vnexpress;
-- use database
use vnexpress;

create table if not exists users (
	id bigint auto_increment,
	hoten nvarchar(255) not null,
	tuoi int,
	gioitinh nvarchar(10),
	ngaytao timestamp,
	ngaychinhsua timestamp default current_timestamp,
	-- timestamp : cho phep dien mui gio (UTC)
	-- audit : check du lieu do ai chinh sua
	primary key (id)
	
);

-- delete column
drop table users;
alter table users 
-- add column 
 -- modify column gioitinh boolean;

rename column hoten to hovaten