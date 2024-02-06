create table car_bodies(
	id serial primary key, name varchar(255)
);
create table car_engines(
	id serial primary key, name varchar(255)
);
create table car_transmissions(
	id serial primary key, name varchar(255)
);
create table cars(
	id serial primary key, 
	name varchar(255),
	car_bodie_id int references car_bodies(id),
	car_engine_id int references car_engines(id),
	car_transmission_id int references car_transmissions(id)
);
insert into car_bodies(name)
values ('Пикап'), ('седан'), ('неизвестный'), ('хетчбек'), ('прямой');
insert into car_engines(name)
values ('engine1'), ('engine2'), ('engine3'), ('engine4');
insert into car_transmissions(name)
values ('transmission1'), ('transmission2'), ('transmission3'), ('transmission4');
insert into cars(name, car_bodie_id, car_engine_id, car_transmission_id)
values
('golf', 4, 3, 3),
('creta', 3, 2, 2),
('m1', 1, 1, 1),
('m2', 2, 1, 1),
('sportage', 3, 2, 2),
('ix35', 3, 2, 1),
('lada', null, 3, null);

select cars.id, cars.name, body.name, engine.name, transmission.name
from cars 
left join car_bodies as body on cars.car_bodie_id = body.id
left join car_engines as engine on cars.car_engine_id = engine.id
left join car_transmissions as transmission on cars.car_transmission_id = transmission.id;

select * from car_bodies as body
left join cars on cars.car_bodie_id = body.id
where cars.id is null;

select * from car_engines as en
left join cars on cars.car_engine_id = en.id
where cars.id is null;

select * from car_transmissions as tr
left join cars on cars.car_transmission_id = tr.id
where cars.id is null;