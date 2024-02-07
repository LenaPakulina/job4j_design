 create table president(
     id serial primary key,
     name varchar(255)
 );
 
 create table capital(
     id serial primary key,
     name varchar(255)
 );
 
 create table country(
     id serial primary key,
	 name varchar(255),
     president_id int references president(id) unique,
     capital_id int references capital(id) unique
 );
 
 insert into president(name) values ('Putin');
 insert into capital(name) values ('Moscow');
 
 insert into country(name, president_id, capital_id) values ('Russia',1,1);
 select * from country;