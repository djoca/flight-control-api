insert into city values (1, 'São José dos Campos');
insert into city values (2, 'São Paulo');
insert into city values (3, 'Rio de Janeiro');

insert into airport values (1, 'SJK', 'Aeroporto de São José dos Campos', 1);
insert into airport values (2, 'GRU', 'Aeroporto Internacional Governador André Franco Montoro', 2);
insert into airport values (3, 'CGH', 'Aeroporto de Congonhas', 2);
insert into airport values (4, 'RIO', 'Aeroporto Internacional Antônio Carlos Jobim', 3);
insert into airport values (5, 'SDU', 'Aeroporto Santos Dumont', 3);

insert into aircraft values (1, 'Embraer', '175', 'PT-CAS', 70, 5986, 3445);
insert into aircraft values (2, 'Boeing', '737', 'PP-GOL', 110, 15234, 7862);

insert into pilot values (1, 'Jack Black');
insert into pilot values (2, 'Bob Bobblehead')

insert into flight values (1, '123', 'TAM', 1, 1, 1, 4, 'FLYING', '2017-12-21 18:20:00', '2017-12-21 18:25:00', null);
insert into flight values (2, '300', 'Gol', 2, 2, 2, 5, 'LANDED', '2017-12-21 14:50:00', '2017-12-21 15:00:00', '2017-12-21 17:20:00');
insert into flight values (3, '500', 'Azul', 2, 1, 5, 1, 'ON_TIME', '2018-12-12 16:00:00', null, null);
