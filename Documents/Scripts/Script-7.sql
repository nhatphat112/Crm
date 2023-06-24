drop database if exists cozastore;
create database if not exists cozastore;
use cozastore;
create table if not exists users(
	id bigint auto_increment,
	username varchar(255) not null,
	password varchar(255) not null,
	email varchar(255) not null,
	primary key (id)
);
create table if not exists country(
	id int auto_increment,
	name nvarchar(255),
	primary key (id)
);
create table if not exists coupon(
	id bigint auto_increment,
	code varchar(255),
	type varchar (255),
	promotion double,
	primary key(id)
);
create table if not exists blog (
	id bigint auto_increment,
	image varchar(255),
	content text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
	user_id bigint not null,
	primary key (id)
);
create table if not exists orders(
	id bigint auto_increment,
	total_price double, 
	country_id int not null,
	state nvarchar(255),
	postcode int,
	coupon_id bigint,
	user_id bigint not null,
	primary key (id)
);
create table if not exists tag (
	id bigint auto_increment,
	name varchar (255),
	primary key(id)
);
create table if not exists blog_tag(
	blog_id bigint not null,
	tag_id bigint not null,
	primary key(blog_id,tag_id)
);
create table if not exists comment(
	id bigint auto_increment,
	content text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
	name nvarchar(255),
	email varchar(255),
	website varchar(255),
	blog_id bigint not null,
	primary key (id)
);
create table if not exists category(
	id bigint auto_increment,
	name nvarchar(255),
	primary key(id)
);
create table if not exists product(
	id bigint auto_increment,
	image varchar(255),
	name nvarchar(255),
	price double,
	category_id bigint not null,
	description nvarchar(255),
	size_id int not null,
	color_id int not null,
	quantity int,
	list_image text,
	primary key (id)
	
);
create table if not exists sizes(
	id int auto_increment,
	name nvarchar(255),
	primary key(id)
);
create table if not exists color(
	id int auto_increment,
	name nvarchar(255),
	primary key(id)
);
create table if not exists order_product(
	order_id bigint not null,
	product_id bigint not null,
	quantity int,
	primary key (order_id,product_id)
);
alter table blog_tag
add constraint FK_blog_tag_blog_id foreign key (blog_id) references blog(id)
on update cascade
on delete cascade;
alter table blog_tag
add constraint FK_blog_tag_tag foreign key (tag_id) references tag(id)
on update cascade
on delete cascade;
alter table blog 
add constraint FK_blog_user_id foreign key (user_id) references users(id)
on update cascade
on delete cascade;
alter table orders 
add constraint FK_orders_country_id foreign key (country_id) references country(id)
on update cascade
on delete cascade;
alter table orders 
add constraint FK_orders_coupon_id foreign key (coupon_id) references coupon(id)
on update cascade
on delete cascade;
alter table orders 
add constraint FK_orders_user_id foreign key (user_id) references users(id)
on update cascade
on delete cascade;
alter table comment 
add constraint FK_comment_blog_id foreign key (blog_id) references blog(id)
on update cascade
on delete cascade;
alter table product
add constraint FK_product_category_id foreign key (category_id) references category(id)
on update cascade
on delete cascade;
alter table product 
add constraint FK_product_color_id foreign key (color_id) references color(id)
on update cascade
on delete cascade;
alter table product
add constraint FK_product_size_id foreign key (size_id) references sizes(id)
on update cascade
on delete cascade;

INSERT INTO users (username,email,password)
values("nguyenvana","nguyenvana@gmail.com","$2y$10$yR5FfHByCMD1jcjkoEi2kuNs5fhJo/GwMdpBeRFIIsht7pJl8nRDC");

SELECT * FROM users u ;
