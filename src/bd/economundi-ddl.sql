create database economundi encoding 'utf-8';

\c economundi;

create table usuario (
    id serial primary key,
    email character varying (100) unique NOT NULL,
    nome character varying (20) NOT NULL,
    sobrenome character varying (50) NOT NULL,
    senha character varying (30) check (length(senha) > 8) NOT NULL,
    data_nasc date check (data_nasc < now()) NOT NULL,
    perfil_economico character varying (20) check (perfil_economico in ('Conservador', 'Moderado', 'Moderado-agressivo', 'Agressivo')),
    data_hora_cadastro timestamp without time zone default now() NOT NULL
);

create table noticia (
    id serial primary key,
    manchete character varying (100) NOT NULL,
    descricao character varying (200) NOT NULL,
    conteudo text,
    fonte character varying (100) NOT NULL,
    link_imagem character varying (100),
    link character varying (100) NOT NULL,
    localidade character varying (6) check (localidade in ('Brasil', 'Mundo')) NOT NULL,
    engajamento integer default (0) check (engajamento >= 0) NOT NULL
);

create table usuario_curte_noticia (
    id serial primary key,
    tipo_curtida character varying (2) check (tipo_curtida in ('Curtiu', 'Não Curtiu')) NOT NULL,
    usuario_id integer references usuario(id) on update cascade NOT NULL,
    noticia_id integer references noticia(id) on update cascade on delete cascade NOT NULL,
    unique (usuario_id, noticia_id)
);

create table comentario (
    id serial primary key,
    data_hora timestamp without time zone default (now()) NOT NULL,
    conteudo text NOT NULL,
    usuario_escritor_id integer references usuario(id) on update cascade NOT NULL,
    noticia_id integer references noticia(id) on update cascade NOT NULL,
    comentario_pai_id integer references comentario(id) on update cascade on delete cascade,
    unique (noticia_id, usuario_escritor_id)
);

create table usuario_curte_comentario (
    id serial primary key,
    tipo_curtida character varying (2) check (tipo_curtida in ('Curtiu', 'Não Curtiu')) NOT NULL,
    usuario_id integer references usuario(id) on update cascade NOT NULL,
    comentario_id integer references comentario(id) on update cascade on delete cascade NOT NULL,
    unique (usuario_id, comentario_id)
);

create table palavra (
    id serial primary key,
    nome character varying (50) unique NOT NULL,
    descricao text NOT NULL
);

create table solicitacao (
    id serial primary key,
    nome character varying (50) NOT NULL,
    descricao text,
    status character varying (50) check (status in ('Aprovado', 'Reprovado', 'Aguardando')) default ('Aguardando') NOT NULL,
    usuario_id integer references usuario(id) on update cascade NOT NULL,
    palavra_id integer references palavra(id) on update cascade NOT NULL
);

create table usuario_pesquisa_palavra (
    id serial primary key,
    data_hora timestamp without time zone NOT NULL,
    usuario_id integer references usuario(id) on update cascade NOT NULL,
    palavra_id integer references palavra(id) on update cascade,
    unique (usuario_id, palavra_id)
);

create table investimento (
    id serial primary key,
    nome character varying (50) unique NOT NULL,
    descricao text NOT NULL,
    grupo character varying (50) NOT NULL, -- ENTRA UM CHECK QUANDO DECIDIRMOS QUAIS GRUPOS IRÃO EXISTIR
    periodo integer NOT NULL,
    rendimento double precision NOT NULL
);

create table simulacao (
    id serial primary key,
    valor_inicial money NOT NULL,
    valor_final money NOT NULL,
    data_hora timestamp without time zone NOT NULL,
    usuario_id integer references usuario(id) on update cascade NOT NULL,
    investimento_id integer references investimento(id) on update cascade NOT NULL
);
