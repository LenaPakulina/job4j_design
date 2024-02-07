create table houses
(
    id    serial primary key,
    address  varchar(255)
);

create table dogs (
    id   serial primary key,
    name varchar(255)
);

create table dogs_houses (
    id serial primary key,
    house_id int references houses(id),
    dog_id int references dogs(id)
);

insert into houses(address) values('A1'), ('t1'), ('u1');
insert into dogs(name) values('Rock'), ('Ville'), ('Pony');
insert into dogs_houses(house_id, dog_id) 
values 
(1,2), (1,3), (2, 1), (3, 3),
(1,2), (1,3), (2, 1), (3, 3),
(1,2), (1,3), (2, 1), (3, 3);

create view show_dogs_with_three_and_more_houses
as 
select dogs.name, count(houses.id) from dogs_houses as dh
join dogs  on dh.dog_id = dogs.id
join houses on dh.house_id = houses.id
group by dogs.name
having count(houses.id) > 3;
