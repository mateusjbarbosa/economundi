\c economundi;

BEGIN;

-- Inserindo black list.
INSERT INTO news_black_list (name, id) VALUES ('CONCURSO', nextval('news_black_list_id_seq'));
INSERT INTO news_black_list (name, id) VALUES ('LOTERIA FEDERAL', nextval('news_black_list_id_seq'));
INSERT INTO news_black_list (name, id) VALUES ('MEGA-SENA', nextval('news_black_list_id_seq'));
INSERT INTO news_black_list (name, id) VALUES ('QUINA', nextval('news_black_list_id_seq'));
INSERT INTO news_black_list (name, id) VALUES ('LOTOFÁCIL', nextval('news_black_list_id_seq'));


COMMIT;

