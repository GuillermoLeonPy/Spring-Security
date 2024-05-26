create table security_users(
	id integer not null primary key,
	user_name varchar_ignorecase(50) not null unique,
	password varchar_ignorecase(500) not null,
	enabled boolean not null
);

CREATE SEQUENCE security_users_user_id_sq AS INTEGER START WITH 1;

--create table security_authorities (
--	username varchar_ignorecase(50) not null,
--	authority varchar_ignorecase(50) not null,
--	constraint fk_authorities_users foreign key(username) references security_users(username)
--);
--create unique index ix_auth_username on security_authorities (username,authority);