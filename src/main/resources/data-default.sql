insert into city values (1, 'São José dos Campos');
insert into city values (2, 'São Paulo');
insert into city values (3, 'Rio de Janeiro');
insert into city values (4, 'Brasília');
insert into city values (5, 'Porto Alegre');

insert into airport values (1, 'SJK', 'Aeroporto de São José dos Campos', 1);
insert into airport values (2, 'GRU', 'Aeroporto Internacional Governador André Franco Montoro', 2);
insert into airport values (3, 'CGH', 'Aeroporto de Congonhas', 2);
insert into airport values (4, 'RIO', 'Aeroporto Internacional Antônio Carlos Jobim', 3);
insert into airport values (5, 'SDU', 'Aeroporto Santos Dumont', 3);
insert into airport values (6, 'BSB', 'Aeroporto Internacional Presidente Juscelino Kubitschek', 4);
insert into airport values (7, 'POA', 'Aeroporto Internacional Salgado Filho', 5);

insert into aircraft values (1, 'Embraer', '190', 'PT-HYC', 90, 3186, 1765);
insert into aircraft values (2, 'Boeing', '737', 'PP-GOR', 110, 12637, 8637);

insert into pilot values (1, 'Jack Black');
insert into pilot values (2, 'Bob Bobblehead')

insert into flight values (1, '123', 'Azul', 1, 1, 1, 6, 'FLYING', '2017-12-21 18:20:00', '2017-12-21 18:25:00', null);
insert into flight values (2, '300', 'Gol', 2, 2, 2, 7, 'LANDED', '2017-12-21 14:50:00', '2017-12-21 15:00:00', '2017-12-21 17:20:00');
