create table departments (
	id serial primary key,
	name varchar(255)
);

create table employees (
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);

insert into departments(name)
values ('Отдел 1'), ('Отдел 2'), ('3 отдел');
insert into departments(name)
values ('Отдел 4 - новый');

insert into employees(name, department_id)
values 
('Виктория', 1), 
('Анатолий', null), 
('Антон', 2),
('Антон', 1), 
('Оксана', 3),
('Геннадий', null);

select * from employees as w
left join departments as d on w.department_id = d.id;

select * from employees as w
right join departments as d on w.department_id = d.id;

select * from employees as w
full join departments as d on w.department_id = d.id;

select * from employees as w cross join departments as d;

select * from departments as d
left join employees as w on w.department_id = d.id
where w.id is null;

select * from departments as d
left join employees as w on w.department_id = d.id;

select d.name, w.name 
from departments as d
left join employees as w on w.department_id = d.id;
select d.name, w.name  
from employees as w
right join departments as d on w.department_id = d.id;

