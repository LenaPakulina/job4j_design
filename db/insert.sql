insert into roles(name) values ('ADMIN');
insert into roles(name) values ('SIMPLE');

insert into users(name, role_id) values ('Elena', 1);
insert into users(name, role_id) values ('Victor', 2);

insert into rules(name) values ('create');
insert into rules(name) values ('read');
insert into rules(name) values ('update');
insert into rules(name) values ('delete');

insert into categories(name) values ('difficult');
insert into categories(name) values ('urgent');

insert into states(name) values ('begin');
insert into states(name) values ('end');

insert into items(name, user_id, category_id, state_id) values ('first', 1,1,1);
insert into items(name, user_id, category_id, state_id) values ('second', 2,2,2);

insert into comments(name, item_id) values ('text text', 2);

insert into attachs(name, item_id) values ('sdjf.pdf', 1);
insert into attachs(name, item_id) values ('text.txt', 2);

insert into role_rule(role_id, rule_id) values (1, 1);