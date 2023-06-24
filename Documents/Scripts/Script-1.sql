create database mybookstore;
use mybookstore;

create table product(
	product_id bigint auto_increment,
	product_name nvarchar(255),
	author_id bigint,
	publish_year smallint,
	import_price double,
	cost double,
	price double,
	quantity int,
	category_id bigint,
	language_product nvarchar(255),
	describe_product text,
	primary key (product_id)
);

create table author(
	author_id bigint auto_increment,
	author_name nvarchar(255),
	birthdate timestamp,
	story text,
	primary key (author_id)
);
create table category(
	category_id bigint auto_increment,
	category_name nvarchar(255),
	primary key (category_id)
);

create table customer(
	customer_id bigint auto_increment,
	username varchar(255),
	password varchar(255),
	customer_name nvarchar(255),
	sex nvarchar(10),
	address text,
	receive_address text,
	purchase_address text,
	birthdate timestamp,
	phone_number varchar(20),
	email varchar(100),
	subscrible_news_mail varchar(20),
	primary key (customer_id)
);

create table orders(
	order_id bigint auto_increment,
	customer_id bigint,
	purchase_address text,
	receive_address text,
	status nvarchar(255),
	purchase_method nvarchar(255),
	purchase_status nvarchar(255),
	purschased_money double,
	debt_money double,
	date_order timestamp default current_timestamp,
	date_delivery timestamp,
	primary key (order_id)
);

create table orderdetail (
	orderdetail_id bigint auto_increment,
	order_id bigint,
	product_id bigint,
	quantity int,
	productjacket_price double,
	discount double,
	price double,
	vat double,
	total_price double,
	primary key(orderdetail_id)
);	
-- add constraint
alter table product
add constraint FK_product_category_id foreign key(category_id)
references category(category_id)
on update cascade
on delete cascade;
alter table orders
add constraint FK_orders_customer_id foreign key(customer_id)
references customer(customer_id)
on update cascade
on delete cascade;
alter table orderdetail
add constraint FK_orderdetail_product_id foreign key(product_id)
references product(product_id)
on update cascade
on delete cascade;
alter table orderdetail
add constraint FK_orderdetail_order_id foreign key(order_id)
references orders(order_id)
on update cascade
on delete cascade;

-- chen du lieu vao ban the loai
INSERT into category(category_name)
values (
	"Tinh Cam"
);
INSERT into category(category_name)
values (
	"Hanh Dong"
);
INSERT into category (category_name)
values (
	"Phieu Luu"
);
INSERT into category (category_name)
values (
	"Kinh Di"
);
INSERT into category (category_name)
values (
	"Hoc Duong"
);
INSERT into category (category_name)
values (
	"Phep Thuat"
);
-- chen du lieu vao bang tac gia author
insert into author(author_name)
values (
	"Le Thi Loc"
);
insert into author (author_name)
values (
	"Truong Nhat Phat"
);

insert into author (author_name)
values (
	"Tran Hong Phan"
);
insert into author (author_name)
values (
	"Le Thanh Nguyen"
);

insert into author (author_name)
values (
	"Nguyen Phuong Thao"
);
insert into author (author_name)
values (
	"Nguyen Binh Phuoc"
);
-- chen thong tin vao bang khach hang customer
insert into customer (customer_name)
values (
	"Dinh Hue Man"
);
insert into customer (customer_name)
values (
	"Nguyen Le Anh Phuc"
);
insert into customer (customer_name)
values (
	"Phan Vu Dang"
);
insert into customer (customer_name)
values (
	"Phan Nhat Lan"
);
insert into customer (customer_name)
values (
	"Le Thi Dang Khoa"
);
-- chen thong tin vao bang  san pham (product)
insert into product (product_name,author_id,category_id,price)
values (
	"Dragon Ball",
	1,
	2,
	500000
);
-- chen thong tin vao bang  san pham (product)
insert into product (product_name,author_id,category_id,price)
values (
	"Naruto",
	3,
	2,
	600000
);
-- chen thong tin vao bang  san pham (product)
insert into product (product_name,author_id,category_id,price)
values (
	"Doremon",
	4,
	2,
	200000
);
-- chen thong tin vao bang  san pham (product)
insert into product (product_name,author_id,category_id,price)
values (
	"Luffy",
	2,
	5,
	400000
);
-- chen thong tin vao bang  san pham (product)
insert into product (product_name,author_id,category_id,price)
values (
	"Sasuke",
	1,
	1,
	800000
);
-- chen thong tin vao bang  san pham (product)
insert into product (product_name,author_id,category_id,price)
values (
	"Code",
	4,
	4,
	300000
);
-- chen thong tin vao bang  orders
insert into orders (customer_id,purschased_money)
values(
	1,
	500000
);
insert into orders (customer_id,purschased_money)
values(
	1,
	200000
);

insert into orders (customer_id,purschased_money)
values(
	3,
	500000
);

insert into orders (customer_id,purschased_money)
values(
	2,
	400000
);
insert into orders (customer_id,purschased_money)
values(
	2,
	600000
);
insert into orders (customer_id,purschased_money)
values(
	4,
	200000
);
-- chen thong tin vao bang  orderdetail
INSERT into orderdetail (order_id,product_id)
values(
	1,
	2
);
INSERT into orderdetail (order_id,product_id)
values(
	4,
	1
);
INSERT into orderdetail (order_id,product_id)
values(
	2,
	5
);
INSERT into orderdetail (order_id,product_id)
values(
	3,
	2
);
INSERT into orderdetail (order_id,product_id)
values(
	5,
	2
);









