create table chairs(
	id serial primary key,
	name varchar(255),
	fabricator varchar(255),
	color varchar(255),
	height float
);
insert into chairs(name, fabricator, color, height) values ('Простой стул 2', 'Стул&CO', 'роз.', '123.32');
update chairs set name = 'Стул', height = '10.0';
delete from chairs;
select * from chairs;