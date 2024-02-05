create table animals(
	id serial primary key,
	type varchar(255)
);

create table animators(
	id serial primary key,
	name varchar(255),
	animal_id int references animals(id)
);

insert into animals(type) values('Тигр');
insert into animals(type) values('Медведь');
insert into animals(type) values('Попугай');

insert into animators(name, animal_id)
values('Вилли', 1);
insert into animators(name, animal_id)
values('Старшина Вилли', 1);
insert into animators(name, animal_id)
values('Корнелий', 3);
insert into animators(name, animal_id)
values('Василий', 2);
insert into animators(name, animal_id)
values('Виктор');

select * from animators
join animals on animators.animal_id = animals.id;
select name as "Артист" from animators as mtr
join animals as ml on mtr.animal_id = ml.id;
select * from animators
join animals on animators.animal_id = animals.id and animal_id > 1;
select mtr.name as "Артист", ml.type as "Вид ЖИвотного" 
from animators as mtr
join animals as ml on mtr.animal_id = ml.id;