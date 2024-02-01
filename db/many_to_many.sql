 create table train(
     id serial primary key,
     name varchar(255)
 );
 
 create table person(
     id serial primary key,
     name varchar(255)
 );
 
 create table train_trip(
     id serial primary key,
     train_id int references train(id),
     person_id int references person(id)
 );
 
 insert into train(name) values ('OEM_23');
 insert into train(name) values ('OEM_24');
 insert into person(name) values ('Helen');
 insert into person(name) values ('Elena');
 
 insert into train_trip(train_id, person_id) values (1,2);