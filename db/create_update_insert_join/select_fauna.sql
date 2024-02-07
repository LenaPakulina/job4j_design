create table fauna (
	id serial primary key,
	name text,
	avg_age int,
	discovery_date date
);

insert into fauna(name, avg_age, discovery_date)
values ('Белые fish', 1, '2021-02-05');
insert into fauna(name, avg_age, discovery_date)
values ('Черные fish', 10500, '2021-02-05');
insert into fauna(name, avg_age, discovery_date)
values ('Желтые fish', 20000, '2021-02-05');

insert into fauna(name, avg_age, discovery_date)
values ('Фикус', 1500, null);
insert into fauna(name, avg_age)
values ('Медведь', 40000);
insert into fauna(name, avg_age, discovery_date)
values ('Береза', 150000, '01.01.1800');

insert into fauna(name, avg_age, discovery_date)
values ('Страус', 20000, '01.05.1700');
insert into fauna(name, avg_age, discovery_date)
values ('Тигр', 40000, '01.01.2000');

select * from fauna where name like '%fish%';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';