create table security_users(
	id integer not null primary key,
	user_name varchar_ignorecase(50) not null unique,
	password varchar_ignorecase(500) not null,
	enabled boolean not null
);

CREATE SEQUENCE security_users_user_id_sq AS INTEGER START WITH 1;

create table security_authorities (
	user_id integer not null,
	authority varchar_ignorecase(50) not null,
	constraint fk_authorities_users foreign key(user_id) references security_users(id)
);

alter table security_authorities add constraint security_authorities_pk primary key(user_id,authority);