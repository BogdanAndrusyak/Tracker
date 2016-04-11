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
	role_id serial,
	foreign key (role_id) references roles(id),
	permission_id serial,
	foreign key (permission_id) references permissions(id)
);

create table users (
	id serial primary key,
	name text,
	role_id serial,
	foreign key (role_id) references roles(id)
);

create table items (
	id serial primary key,
	name text,
	description text,
	create_date timestamp not null default now(),
	user_id serial,
	foreign key (user_id) references users(id)
); 

create table comments (
	id serial primary key,
	description text,
	create_date timestamp not null default now(),
	item_id serial,
	foreign key (item_id) references items(id)
);

create table files (
	id serial primary key,
	file bytea,
	item_id serial,
	foreign key (item_id) references items(id)
);

create table statuses (
	id serial primary key,
	name text
);

create table Status_Item (
	id serial primary key,
	status_id serial,
	foreign key (status_id) references statuses(id),
	item_id serial,
	foreign key (item_id) references items(id)
);

create table categories (
	id serial primary key,
	name text
);

create table Category_Item (
	id serial primary key,
	category_id serial,
	foreign key (category_id) references categories(id),
	item_id serial,
	foreign key (item_id) references items(id)
);