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

create table aircraft (
  aircraft_id numeric,
  aircraft_manufacturer varchar(40),
  aircraft_model varchar(20),
  aircraft_registry varchar(10),
  number_seats numeric,
  flight_hours numeric,
  flight_cycles numeric,
  primary key (aircraft_id)
);

create table pilot (
  pilot_id numeric,
  pilot_name varchar(100),
  primary key (pilot_id)
);

create table flight (
  flight_id numeric,
  number numeric,
  company_name varchar(100),
  aircraft_id numeric,
  pilot_id numeric,
  origin_airport_id numeric,
  destination_airport_id numeric,
  status varchar(10),
  scheduled_departure_time datetime,
  departure_time datetime,
  arrival_time datetime,
  primary key (flight_id),
  foreign key (aircraft_id) references aircraft(aircraft_id),
  foreign key (pilot_id) references pilot(pilot_id),
  foreign key (origin_airport_id) references airport(airport_id),
  foreign key (destination_airport_id) references airport(airport_id)
);