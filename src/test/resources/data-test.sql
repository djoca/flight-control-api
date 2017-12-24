insert into city values (1, 'São José dos Campos');
insert into city values (2, 'São Paulo');
insert into city values (3, 'Rio de Janeiro');

insert into airport values (1, 'SJK', 'Aeroporto de São José dos Campos', 1);
insert into airport values (2, 'GRU', 'Aeroporto Internacional Governador André Franco Montoro', 2);
insert into airport values (3, 'CGH', 'Aeroporto de Congonhas', 2);
insert into airport values (4, 'RIO', 'Aeroporto Internacional Antônio Carlos Jobim', 3);
insert into airport values (5, 'SDU', 'Aeroporto Santos Dumont', 3);

insert into flight values (1, '123', 'TAM', 1, 4, 'FLYING', '2017-12-21 18:20:00', '2017-12-21 18:25:00', null);
insert into flight values (2, '300', 'Gol', 2, 5, 'LANDED', '2017-12-21 14:50:00', '2017-12-21 15:00:00', '2017-12-21 17:20:00');
