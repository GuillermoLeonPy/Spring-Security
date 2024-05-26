insert into security_users (id, user_name, password, enabled)
values ((SELECT NEXT VALUE FOR security_users_user_id_sq),'guest','guest',true);

insert into security_authorities (user_id, authority)
values ((SELECT CURRENT VALUE FOR security_users_user_id_sq),'ROLE_GUEST');

insert into security_users (id, user_name, password, enabled)
values ((SELECT NEXT VALUE FOR security_users_user_id_sq),'admin','admin',true);

insert into security_authorities (user_id, authority)
values ((SELECT CURRENT VALUE FOR security_users_user_id_sq),'ROLE_GUEST');

insert into security_authorities (user_id, authority)
values ((SELECT CURRENT VALUE FOR security_users_user_id_sq),'ROLE_ADMIN');