INSERT INTO klient (imie,nazwisko) VALUES ('Jacek', 'Braun');
INSERT INTO klient (imie,nazwisko) VALUES ('Chloe', 'Brian');
INSERT INTO klient (imie,nazwisko) VALUES ('Kim', 'Bauer');
INSERT INTO klient (imie,nazwisko) VALUES ('Michelle', 'Dessler');

INSERT INTO auto (nr_rejestracyjny, cena, model, marka, typ_paliwa) VALUES ('CCH56H21', 40.5, 'Ford', 'Focus', 'diesel');
INSERT INTO auto (nr_rejestracyjny, cena, model, marka, typ_paliwa) VALUES ('GDA45GT1', 25.5, 'Ford', 'Fiesta', 'benzyna');
INSERT INTO auto (nr_rejestracyjny, cena, model, marka, typ_paliwa) VALUES ('GD54D66A', 40.0, 'Opel', 'Corsa', 'gaz');

INSERT INTO wypozyczenie (data_wypozyczenia, data_zwrotu, klient_id_klient) VALUES ('10-03-2019', '29-03-2019', 1);

INSERT INTO wypozyczenie_auta(wypozyczenie_id_wypozyczenie, auta_id_auto) VALUES (0, 2), (0,1);