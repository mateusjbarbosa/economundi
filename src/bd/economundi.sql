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
    unique (noticia_id, usuario_escritor_id)
);

create table usuario_curte_noticia (
    id serial primary key,
    tipo_curtida character varying (2) check in ('C', 'NC') NOT NULL,
    usuario_id integer references usuario(id) on update cascade NOT NULL,
    noticia_id integer references noticia(id) on update cascade on delete NOT NULL
    unique (usuario_id, noticia_id)
);

create table palavra (
    id serial primary key,
    nome character varying (50) unique NOT NULL,
    descricao text NOT NULL,
    usuario_id integer references usuario(id) on update cascade NOT NULL
);

create table usuario_pesquisa_palavra (
    id serial primary key,
    data_hora timestamp without timezone NOT NULL,
    usuario_id integer references usuario(id) on update cascade NOT NULL,
    palavra_id integer references palavra(id) on update cascade NOT NULL
    unique (usuario_id, palavra_id)
);

-- ALTERAÇÃO NA REGRA DE NEGÓCIO DO BANCO: Como agora é somente solicitada a edição, não é necessário
-- a nova descrição, somente o registro.
create table usuario_edita_palavra (
    id serial primary key,
    data_hora timestamp without timezone NOT NULL,
    isValida boolean default (false) NOT NULL,
    usuario_id integer references usuario(id) on update cascade NOT NULL,
    palavra_id integer references palavra(id) on update cascade NOT NULL
    unique (usuario_id, palavra_id)
);

-- ALTERAÇÃO NA REGRA DE NEGÓCIO DO BANCO: Como agora é somente solicitada a edição, não é necessário
-- a nova descrição, somente o registro, informando a palavra que se deseja cadastrar. Havia um erro também,
-- como referenciar o id de uma palavra que exisita ainda?
create table usuario_cadastra_palavra (
    id serial primary key,
    palavra character varying (50) NOT NULL,
    data_hora timestamp without timezone NOT NULL,
    isValida boolean default (false) NOT NULL,
    usuario_id integer references usuario(id) on update cascade NOT NULL,
    unique (usuario_id)
);

create table investimento (
    id serial primary key,
    nome character varying (50) unique NOT NULL,
    descricao text NOT NULL,
    grupo character varying (50) NOT NULL -- ENTRA UM CHECK QUANDO DECIDIRMOS QUAIS GRUPOS IRÃO EXISTIR
    periodo integer NOT NULL,
    rendimento double precision NOT NULL
);

create table simulacao (
    id serial primary key,
    valor_inicial money check in (valor_inicial > 0) NOT NULL,
    valor_final money NOT NULL,
    data_hora timestamp without timezone NOT NULL,
    usuario_id integer references usuario(id) on update cascade NOT NULL,
    investimento_id integer references investimento(id) on update cascade NOT NULL
);