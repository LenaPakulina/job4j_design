create table types(
	id serial primary key,
	name varchar(255)
);
create table products(
	id serial primary key,
	name varchar(255),
	type_id int references types(id),
	expired_date timestamp,
	price float
);
insert into types(name)
values ('СЫР'), ('Хлеб'), ('МОЛОКО'), ('Сладкое');

insert into products(name, type_id, expired_date, price)
values
('Сыр плавленный', 1, '07.02.2024', 110.02),
('Сыр моцарелла', 1, '08.02.2024', 90.02),
('Сыр простой', 1, '08.01.2024', 11.01),
('Хлеб черный', 2, '04.02.2024', 30),
('Хлеб белый', 2, '07.02.2024', 30),
('Молоко 1', 3, '04.02.2024', 45),
('Молоко 2', 3, '10.02.2024', 70),
('Мороженое 1', 4, '01.01.2025', 50),
('Мороженое 1', 4, '01.01.2025', 50),
('Мороженое 2', 4, '01.01.2025', 50),
('Мороженое 3', 4, '01.01.2025', 50),
('Мороженое 4', 4, '01.01.2025', 50),
('Мороженое 5', 4, '01.01.2025', 50),
('Мороженое 6', 4, '01.01.2025', 50),
('Мороженое 7', 4, '01.01.2025', 50),
('Мороженое 8', 4, '01.01.2025', 50),
('Мороженое 9', 4, '01.01.2025', 50),
('Мороженое 10', 4, '01.01.2025', 50),
('Мороженое 11', 4, '01.01.2025', 50),
('Мороженое 12', 4, '01.01.2025', 50);

select * from products as pr 
join types
on pr.type_id = types.id
and types.name like 'СЫР';

select * from products where name like '%Мороженое%';

select * from products 
where expired_date < current_date;

select types.name, max(pr.price) from products as pr
join types on pr.type_id = types.id
group by types.name;

select types.name, count(pr.type_id) from products as pr
join types on pr.type_id = types.id
group by types.name;

select * from products as pr
join types on pr.type_id = types.id and 
(types.name like '%СЫР%' or types.name like '%МОЛОКО%');

select types.name, count(pr.type_id) from products as pr
join types on pr.type_id = types.id
group by types.name
having count(pr.type_id) < 10;

select * from products as pr join types on pr.type_id = types.id order by pr.name desc;