delete from persons;
delete from sensors;
insert into sensors (id, purpose, min_value, max_value)
 values(100000, 'oxygen sensor', 100, 200);
 insert into persons (id, name, email, sensor_id) 
 values (123, 'Vasya', 'vasya@gmail.com', 100000);
  insert into persons (id, name, email, sensor_id) 
 values (124, 'Sara', 'sara@gmail.com', 100000);