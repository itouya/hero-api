create table if not exists hero (
	id int primary key auto_increment,
	name varchar(50) not null,
	account_image varchar(50) not null
);