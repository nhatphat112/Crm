--  create db CRM
create database crm;
use crm;
create table Users (
	id bigint auto_increment,
	fullname nvarchar(255),
	phone varchar(24),
	gender varchar(3),
	mail varchar(255),
	password varchar(255),
	role_id bigint not null,
	primary key (id)
	
);
create table role (
	id bigint auto_increment,
	rolename nvarchar(255),
	primary key (id)
);
create table project (
	id bigint auto_increment,
	projectname nvarchar(255),
	datebegin timestamp,
	dateend timestamp,
	primary key (id)
);
create table job (
	id bigint auto_increment,
	jobname nvarchar(255),
	primary key (id)
);
create table status(
	id bigint auto_increment,
	statusname nvarchar(255),
	primary key (id)
);
create table workon (
	user_id bigint not null,
	project_id bigint not null,
	job_id bigint not null,
	status_id bigint not null,
	datebegin timestamp,
	dateend timestamp,
	primary key (user_id,project_id,job_id,status_id)
);
-- create constraint
alter table Users 
add constraint FK_Users_role_id foreign key (role_id) references role(id)
on update cascade
on delete cascade
;
alter table workon 
add constraint FK_workon_user_id foreign key (user_id) references Users(id)
on update cascade
on delete cascade
;
alter table workon 
add constraint FK_workon_project_id foreign key (project_id) references project(id)
on update cascade
on delete cascade
;
alter table workon 
add constraint FK_workon_job_id foreign key (job_id) references job(id)
on update cascade
on delete cascade
;
alter table workon 
add constraint FK_workon_status_id foreign key (status_id) references status(id)
on update cascade
on delete cascade
;
SELECT * FROM 
project p ;
use crm;
ALTER table `role`  
add column description nvarchar(255);
INSERT INTO role( rolename , description ) VALUES ("ROLE_ADMIN", "Quản trị hệ thống");
INSERT INTO role( rolename, description ) VALUES ("ROLE_MANAGER", "Quản lý");
INSERT INTO role( rolename, description ) VALUES ("ROLE_USER", "Nhân viên");


INSERT INTO status( statusname) VALUES ("Chưa thực hiện");
INSERT INTO status( statusname ) VALUES ("Đang thực hiện");
INSERT INTO status( statusname ) VALUES ("Đã hoàn thành");

INSERT INTO users(email,password,fullname,avatar,role_id)
values ("nguyenvanb@gmail.com","123456","Nguyen Van B ","",2);
INSERT INTO users(email,password,fullname,avatar,role_id)
values ("nguyenvanc@gmail.com","123456","Nguyen Van C ","",3);
INSERT INTO  job(jobname) Values("Tạo project");
INSERT INTO  job(jobname) Values("Thiết kế font-end");
INSERT INTO  job(jobname) Values("Xây dựng database");
INSERT INTO workon (user_id,project_id,job_id,status_id)
values (1,2,2,3);
INSERT INTO workon (user_id,project_id,job_id,status_id)
values (3,2,1,2);
INSERT INTO workon (user_id,project_id,job_id,status_id)
values (2,2,3,1);

SELECT *
FROM project p ;
SELECT * FROM `role` WHERE id = 1;

UPDATE project SET projectname = ?,datebegin = ?,dateend = ? WHERE id =?;
SELECT j.id as job_id ,j.jobname ,w.status_id 
FROM job j inner join workon w 
on j.id  = w.user_id 
WHERE w.user_id  = 1;
SELECT *
FROM status s ;
ALTER table users 
add column country varchar(150);
SELECT *
FROM workon w ;
SELECT  j.id as job_id, j.jobname , u.id  as user_id, u.fullname, p.id  as project_id, p.projectname , w.datebegin , w.dateend , s.id as status_id ,s.statusname  
FROM job j inner join workon w  on j.id  = w.job_id inner JOIN users u on u.id =w.user_id inner join project p on p.id = w.project_id INNER JOIN status s on s.id  = w.status_id  ;
UPDATE  workon Set datebegin  = "2023-02-08",dateend = "2023-09-08" WHERE user_id  =2;
SELECT *
FROM `role` r ;
UPDATE `role` SET rolename =?,description =? WHERE id = ?;
select *
FROM  `role` r ;
SELECT * 
FROM users u join `role` r ON 
u.role_id  = r.id WHERE email = "nguyenvana@gmail.com"  and password ="123456";
SELECT j.id as job_id ,j.jobname ,w.status_id FROM job j inner join workon w on j.id  = w.user_id;
SELECT * 
FROM users u ;
I
-- UPDATE users SET fullname =?,phone =?,email =?,password =?,role_id =?,country =?,gender =?,avatar =? WHERE id = ?;
SELECT  s.id as status_id , s.statusname , w.datebegin ,w.dateend ,j.id  as job_id,j.jobname 
FROM (select u.id, u.fullname ,u.email ,u.avatar FROM users u WHERE id = 1)
AS u
join workon w 
on u.id  = w.user_id
JOIN status s 
on s.id  = w.status_id 
JOIN job j 
on j.id  = w.job_id ;
use crm;
INSERT NSERT  INTO users (fullname,email,password)
Values ("Tran van V","tranvanv@gmail.com","123456");
SELECT *
FROM users u ;
use crm_app;
SELECT *
FROM  users u ;
UPDATE users 
SET email  = "nguyenvana@gmail.com" WHERE  id =28;
use crm;
select u.id ,u.fullname ,u.role_id 
FROM users u join workon w on u.id  = w.user_id 
			JOIN (select id from project p WHERE p.id = 2) as p on p.id  = w.project_id 
			JOIN `role` r on r.id =u.role_id ;
		SELECT j.id as job_id ,j.jobname ,w.status_id FROM job j inner join workon w on j.id = w.user_id
	 JOIN (select id from project p WHERE p.id = ?) as p on w.project_id  = p.id  WHERE w.user_id  = ?;
	create database test1;
use test1;
create table if not exists blog (
	id bigint auto_increment,
	image varchar(255),
	content text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
	user_id bigint not null,
	primary key (id)
);










