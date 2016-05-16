--tables
create table if not exists roles (
	id serial primary key,
	name text
);

create table if not exists permissions (
	id serial primary key,
	name text,
	description text
);

create table if not exists role_permission (
	id serial primary key,
	role_id int references roles(id),
	permission_id int references permissions(id)
);

create table if not exists users (
	id serial primary key,
	name text,
	login text,
	password text,
	email text,
	create_date timestamp not null default now(),
	role_id int not null default 2 references roles(id)
);

create table if not exists items (
	id serial primary key,
	name text,
	description text,
	create_date timestamp not null default now(),
	user_id int references users(id)
);

create table if not exists comments (
	id serial primary key,
	description text,
	create_date timestamp not null default now(),
	item_id int references items(id)
);

create table if not exists files (
	id serial primary key,
	file bytea,
	item_id int references items(id)
);

create table if not exists statuses (
	id serial primary key,
	name text
);

create table if not exists Status_Item (
	id serial primary key,
	status_id int references statuses(id),
	item_id int references items(id)
);

create table if not exists categories (
	id serial primary key,
	name text
);

create table if not exists Category_Item (
	id serial primary key,
	category_id int references categories(id),
	item_id int references items(id)
);

-- add roles
insert into roles(name)
select 'administrator'
where not exists (select 1 from roles where name = 'administrator');

insert into roles(name)
select 'user'
where not exists (select 1 from roles where name = 'user');

-- add users, administrator
do $$
begin
if not exists (select * from users where name = 'administrator' and role_id = 1)
then
insert into users(name, login, password, email, role_id) values('administrator', 'root', 'root', 'root@root.com', 1);
end if;
end;
$$
--