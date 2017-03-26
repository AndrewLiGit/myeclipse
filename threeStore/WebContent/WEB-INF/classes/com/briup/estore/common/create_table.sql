
create table kk_customer(
	id number primary key,
	username varchar2(20) not null,
	password varchar2(50) not null,
	address varchar2(100),
	telephone varchar2(13),
	email varchar2(50)
);
create table kk_order(
	id number primary key,
	orderDate date,
	cost number(8,2),
	--外键
	c_id references kk_customer(id)
);
create table kk_product(
	id number primary key,
	productName varchar2(20),
	price number(8,2)
);
create table kk_line(
	id number primary key,
	num number,
	--外键
	o_id references kk_order(id),
	p_id references kk_product(id)
);
