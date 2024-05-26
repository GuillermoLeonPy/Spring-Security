insert into users (username, password, enabled)
values ('guest','guest',true);

insert into users (username, password, enabled)
values ('admin','admin',true);

insert into authorities (username, authority)
values ('guest','ROLE_GUEST');

insert into authorities (username, authority)
values ('admin','ROLE_GUEST');

insert into authorities (username, authority)
values ('admin','ROLE_ADMIN');