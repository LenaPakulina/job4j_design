create table city(
    id serial primary key,
    name varchar(255)
);

create table person(
    id serial primary key,
    name varchar(255),
    city_id int references city(id)
);

insert into city(name) values ('Ekaterinburg');
insert into person(name, city_id) VALUES ('Elena', 1);

select * from person;
select * from city where id in (select city_id from person);