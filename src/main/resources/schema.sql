create table flight (
  id numeric, 
  number numeric, 
  company_name varchar(100),
  origin varchar(3), 
  destination varchar(3),
  status varchar(10),
  scheduled_departure_time datetime,
  departure_time datetime,
  arrival_time datetime
);
