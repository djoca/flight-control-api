create table city (
  city_id numeric,
  city_name varchar(100),
  primary key (city_id)
);

create table airport (
  airport_id numeric,
  iata_code varchar(3),
  airport_name varchar(100),
  city_id numeric,
  primary key (airport_id),
  foreign key (city_id) references city(city_id)
);

create table flight (
  flight_id numeric,
  number numeric,
  company_name varchar(100),
  origin_airport_id numeric,
  destination_airport_id numeric,
  status varchar(10),
  scheduled_departure_time datetime,
  departure_time datetime,
  arrival_time datetime,
  primary key (flight_id),
  foreign key (origin_airport_id) references airport(airport_id),
  foreign key (destination_airport_id) references airport(airport_id),
);