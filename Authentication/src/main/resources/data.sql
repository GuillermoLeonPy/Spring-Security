insert into security_users (id, user_name, password, enabled)
values ((SELECT NEXT VALUE FOR security_users_user_id_sq),'guest','guest',true);

insert into security_users (id, user_name, password, enabled)
values ((SELECT NEXT VALUE FOR security_users_user_id_sq),'admin','admin',true);

--insert into security_authorities (username, authority)
--values ('guest','ROLE_GUEST');
--
--insert into security_authorities (username, authority)
--values ('admin','ROLE_GUEST');
--
--insert into security_authorities (username, authority)
--values ('admin','ROLE_ADMIN');