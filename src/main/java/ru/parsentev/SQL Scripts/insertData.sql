insert into roles(name) values ('admin');
insert into roles(name) values ('guest');

insert into permissions(name, description) values ('add_item', 'ability to add items'); 
insert into permissions(name, description) values ('edit_item', 'ability to change items');
insert into permissions(name, description) values ('view_item', 'ability to view items');

insert into role_permission(role_id, permission_id) values (1, 1);
insert into role_permission(role_id, permission_id) values (1, 2);
insert into role_permission(role_id, permission_id) values (1, 3);
insert into role_permission(role_id, permission_id) values (2, 1);
insert into role_permission(role_id, permission_id) values (2, 3);

insert into users(name, role_id) values ('Petr Arsentev', 1);
insert into users(name, role_id) values ('Bogdan Andrusyak', 2);

insert into items(name, description, user_id) values ('Item1', 'write database creation script', 1);

insert into comments(description, item_id) values ('easy', 1);

--todo insert files to item

insert into statuses(name) values('active');
insert into statuses(name) values('inactive');

insert into status_item(status_id, item_id) values(1, 1);

insert into categories(name) values ('must_do');

insert into category_item(category_id, item_id) values (1, 1);