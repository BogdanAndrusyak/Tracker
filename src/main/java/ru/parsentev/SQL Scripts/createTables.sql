create table roles (
	id serial primary key,
	name text
);

create table permissions (
	id serial primary key,
	name text,
	description text
);

create table role_permission (
	id serial primary key,
	role_id int references roles(id),
	permission_id int references permissions(id)
);

create table users (
	id serial primary key,
	name text,
	role_id int references roles(id)
);

create table items (
	id serial primary key,
	name text,
	description text,
	create_date timestamp not null default now(),
	user_id int references users(id)
); 

create table comments (
	id serial primary key,
	description text,
	create_date timestamp not null default now(),
	item_id int references items(id)
);

create table files (
	id serial primary key,
	file bytea,
	item_id int references items(id)
);

create table statuses (
	id serial primary key,
	name text
);

create table Status_Item (
	id serial primary key,
	status_id int references statuses(id),
	item_id int references items(id)
);

create table categories (
	id serial primary key,
	name text
);

create table Category_Item (
	id serial primary key,
	category_id int references categories(id),
	item_id int references items(id)
);