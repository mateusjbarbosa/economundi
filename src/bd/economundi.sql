create database 'economundi' encoding 'utf-8';

\c economundi;

create table usuario (
    id serial primary key,
    email character varying (100) unique NOT NULL,
    nome character varying (20) NOT NULL,
    sobrenome character varying (50) NOT NULL,
    senha character varying (30) check in (senha > 8) NOT NULL,
    data_nasc date check in (data_nasc < now()) NOT NULL,
    perfil_economico character varying (20) check in ('Conservador', 'Moderado', 'Moderado-agressivo', 'Agressivo'),
    data_hora_cadastro timestamp without timezone default now() NOT NULL
);

create table noticia (
    id serial primary key,
    manchete character varying (100) NOT NULL,
    descricao character varying (200) NOT NULL,
    conteudo text,
    fonte character varying (100) NOT NULL,
    link_imagem character varying (100),
    link character varying (100) NOT NULL,
    localidade character varying (6) check in ('Brasil', 'Mundo') NOT NULL,
    engajamento integer default (0) check in (engajamento >= 0) NOT NULL
);

create table comentario (
    id serial primary key,
    data_hora timestamp without timezone default (now()) NOT NULL,
    conteudo text NOT NULL,
    usuario_escritor_id integer references usuario(id) on update cascade NOT NULL,
    usuario_reacao_id integer references usuario(id) on update cascade NOT NULL,
    noticia_id integer references noticia(id) on update cascade NOT NULL,
    comentario_pai_id integer references comentario(id) on update cascade on delete
);