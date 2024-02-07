create table teens(
	id serial primary key,
	name varchar(255),
	gender int
);
insert into teens(name, gender)
values
('Lena', 1),
('Victor', 2),
('Ksu', 1),
('Anton', 2),
('Rita', 1),
('Omar', 2);

select (n1.name || '-' || n2.name) as "pair" 
from teens as n1
cross join teens as n2
where n1.gender != n2.gender and n1.gender = 2;

