create table loginuser (
user_name varchar (32) not null,
id_num varchar (18) not null,
passwd varchar (32) not null,
email varchar (255),
user_type int (11),
primary key (id_num)
);

create table role(
role_id int (11) not  null auto_increment,
role_name varchar (32) not null,
create_time timestamp,
primary key (role_id)
);

create table permissontable(
permission_id int (11) not null auto_increment,
primary key (permission_id)
);

create table applytable(
id_num varchar (32) not null,
user_id int (11) ,
photo_location varchar(255),
name varchar(32) ,
birth_date varchar(32) ,
sex varchar(1),
graduate_date varchar(32),
major varchar(255),
nation varchar(32),
native_palce varchar(32),
education varchar (32),
school varchar (32),
political_landscape varchar(32),
professional_title varchar(32),
work_date varchar(32),
come_date varchar(32),
apply_date varchar (32),
rc_type varchar(32),
apply_type varchar (32),
apply_money varchar (32),
is_firstschool varchar(32),
phone_num varchar (32),
company_type varchar (32),
company_name varchar (32),
company_address varchar (32),
company_contact varchar (32),
post varchar(32),
contact_phone varchar (32),
bank varchar (32),
bank_card varchar (32),
education_qrcode varchar (255),
attatchment varchar (255),
apply_time timestamp ,
chsi_return varchar (255),
apply_status varchar (32),
allowance_id int(11),
audit_comment varchar (255),
primary key (id_num)
);
create table archives(
id int (11) not null auto_increment,
user_name varchar(32),
id_num varchar(32),
status varchar (32),
is varchar (255),
primary key (id)
);

create table socialsecurity(
id int (11) not null auto_increment,
user_name varchar(32),
id_num varchar(32),
begin_time varchar(32),
last_time varchar (32),
monthes int ,
status varchar (32),
primary key (id)
);
create table resume(
resume_id int (11)  not null auto_increment,
id_num varchar (32),
time_scape varchar(255),
company varchar (255),
department varchar (255),
post varchar (32),
primary key (resume_id)
);

-- 补贴表
create table  allowance(
allownce_id int (11) not null auto_increment,
id_num varchar (32) not null unique ,
begin_time varchar (32) ,
last_time varchar (32) ,
sum_money int ,
last_money int,
monthes int,
allowancetype varchar (32),
updatetime varchar (32),
shebao varchar (32),
bank VARCHAR (32),
bank_card varchar (32),
phone varchar (32),
name varchar (32),
company varchar (32),
batch varchar (255),
primary key (allownce_id),
UNIQUE KEY id_num (id_num)
);

create table  allowancehistory(
id  int (11) not null auto_increment,
id_num varchar (32) ,
offer_time varchar (32) ,
offer_money int,
allowancetype varchar (32),
comment varchar (255),
shebao varchar (32),
bank VARCHAR (32),
bank_card varchar (32),
phone varchar (32),
name varchar (32),
company varchar (32),
batch varchar (255),
primary key (id),
);

create table applytablecompare(
id_num varchar (32) not null,
user_id int (11) ,
photo_location varchar(255),
name varchar(32) ,
birth_date varchar(32) ,
sex varchar(1),
graduate_date varchar(32),
major varchar(255),
nation varchar(32),
native_palce varchar(32),
education varchar (32),
political_landscape varchar(32),
professional_title varchar(32),
work_date varchar(32),
come_date varchar(32),
apply_date varchar (32),
rc_type varchar(32),
apply_type varchar (32),
apply_money varchar (32),
is_firstschool varchar(32),
phone_num varchar (32),
company_type varchar (32),
company_name varchar (32),
company_address varchar (32),
company_contact varchar (32),
post varchar(32),
contact_phone varchar (32),
bank varchar (32),
bank_card varchar (32),
education_qrcode varchar (255),
attatchment varchar (255),
apply_time timestamp ,
chsi_return varchar (255),
apply_status varchar (32),
allowance_id int(11),
audit_comment varchar (255),
education_type varchar (32),
school varchar (32),
isarchive int (11),
primary key (id_num)
);

create table topschool(
id int (11) not null auto_increment,
schoolname varchar (255),
primary key (id)
);

create table topmajor(
id int (11) not null auto_increment,
school varchar (255),
major varchar (255),
primary key (id)
);