drop database economundi;
create database economundi encoding 'utf-8';

\c economundi;

begin;

create table _user (
    id serial primary key,
    email character varying (100) unique NOT NULL,
    first_name character varying (20) NOT NULL,
    last_name character varying (50) NOT NULL,
    password character varying (200) check (length(password) > 8) NOT NULL,
    date_birth date check (date_birth < now()) NOT NULL,
    emailVerificationToken character varying (200), 
    permission character varying(30) check(permission in ('ADMIN','USER')) NOT NULL,
    economic_profile character varying (20) check (economic_profile in ('Conservative', 'Moderate', 'Moderate-Aggressive', 'Aggressive','None')) default 'None',
    date_hour_register timestamp without time zone default now() NOT NULL
);

create table news (
    id serial primary key,
    title text NOT NULL,
    description text,
    content text,
    source character varying (200) NOT NULL,
    link_image text,
    link text NOT NULL unique,
    date_hour timestamp without time zone default (now()) NOT NULL,
    locality character varying (6) check (locality in ('Brazil', 'World')) NOT NULL,
    engagement integer default (0) check (engagement >= 0) NOT NULL
);

create table user_like_news (
    id serial primary key,
    type_like character varying (12) check (type_like in ('Like', 'Did Not Like')) NOT NULL,
    user_id integer references _user(id) on update cascade NOT NULL,
    news_id integer references news(id) on update cascade on delete cascade NOT NULL,
    unique (user_id, news_id)
);

create table comment (
    id serial primary key,
    date_hour timestamp without time zone default (now()) NOT NULL,
    content text NOT NULL,
    user_writer_id integer references _user(id) on update cascade NOT NULL,
    news_id integer references news(id) on update cascade NOT NULL,
    comment_father_id integer references comment(id) on update cascade on delete cascade,
    unique (news_id, user_writer_id)
);

create table user_like_comment (
    id serial primary key,
    type_like character varying (12) check (type_like in ('Like', 'Did Not Like')) NOT NULL,
    user_id integer references _user(id) on update cascade NOT NULL,
    comment_id integer references comment(id) on update cascade on delete cascade NOT NULL,
    unique (user_id, comment_id)
);

create table word (
    id serial primary key,
    name character varying (50) unique NOT NULL,
    description text NOT NULL
);

create table solicitation (
    id serial primary key,
    name character varying (50) NOT NULL,
    description text,
    status character varying (50) check (status in ('Approved', 'Disapproved', 'Waiting')) default ('Waiting') NOT NULL,
    user_id integer references _user(id) on update cascade NOT NULL,
    word_id integer references word(id) on update cascade
);

create table word_access (
    id serial primary key,
    data_hour timestamp without time zone NOT NULL default now(),
    word_id integer references word(id) on update cascade,
    unique (word_id, data_hour)
);

create table news_black_list (
    id serial primary key,
    name character varying (50) unique NOT NULL
);

create table investment (
    id serial primary key,
    name character varying (50) unique NOT NULL,
    description text NOT NULL,
    _group character varying (50) NOT NULL, -- ENTRA UM CHECK QUANDO DECIDIRMOS QUAIS GRUPOS IRÃO EXISTIR
    period integer NOT NULL,
    yield double precision NOT NULL
);

create table currency (
    id serial primary key,
    name character varying (50) unique NOT NULL
);

create table quote (
    id serial primary key,
    data_hour timestamp without time zone unique NOT NULL default now(),
    buy money check (buy >= 0::money) NOT NULL,
    sell money check (sell >= 0::money),
    variation double precision NOT NULL
);

create table simulation (
    id serial primary key,
    initial_value money NOT NULL,
    final_value money NOT NULL,
    date_hour timestamp without time zone NOT NULL,
    user_id integer references _user(id) on update cascade NOT NULL,
    investment_id integer references investment(id) on update cascade NOT NULL
);

commit;