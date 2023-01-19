--data.sql will only executes when we are using InMemoryDatabase

insert into user_details(id, date_of_birth, name)
values(1001, current_date(), 'Prabhakar');

insert into user_details(id, date_of_birth, name)
values(1002, current_date(), 'Naman');

insert into user_details(id, date_of_birth, name)
values(1003, current_date(), 'Satish');

insert into post(id, description, user_id)
values(2001, 'I want to learn AWS', 1001);

insert into post(id, description, user_id)
values(2002, 'I want to learn GCP', 1001);

insert into post(id, description, user_id)
values(2003, 'I want to be AWS certified', 1002);

insert into post(id, description, user_id)
values(2004, 'I want to be GCP certified', 1002);
