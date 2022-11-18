drop database if exists blockbuster;
create database blockbuster;
use blockbuster;
create table category (
	id int not null auto_increment primary key,
    category_name varchar(40) not null
);
create table movie (
	id int not null auto_increment primary key,
    title varchar(100) not null,
    summary varchar(300) not null,
    price decimal(9,2) not null,
    stock int not null,
    image_url varchar(500),
    category_id int,
    foreign key (category_id)
    references category(id)
    on delete set null
);
create table admin (
	id int not null auto_increment primary key,
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    email varchar(40) not null,
    password varchar(20) not null
);
create table customer (
	id int not null auto_increment primary key,
	first_name varchar(20) not null,
    last_name varchar(20) not null,
    phone varchar(20),
    address varchar(50),
    email varchar(40) not null,
    password varchar(20) not null
);
create table order_details (
	id varchar(40) not null primary key,
    customer_id int not null,
    total decimal(9,2) not null default 0,
    foreign key (customer_id)
    references customer(id)
);
create table payment (
	id int not null auto_increment primary key,
    order_id varchar(40),
    amount decimal(9,2) not null,
    payment_status varchar(20) not null,
    card_number int,
    foreign key (order_id)
    references order_details(id)
    on delete cascade
);
create table order_item(
	id int not null auto_increment primary key,
    order_id varchar(40),
    movie_id int,
    quantity int,
    foreign key (order_id)
    references order_details(id),
    foreign key (movie_id)
    references movie(id)
);
