DROP TABLE IF EXISTS t_book;
DROP TABLE IF EXISTS t_author;

create table t_author
(
    id          bigserial not null
        constraint t_author_pk
            primary key,
    first_name  varchar   not null,
    middle_name varchar,
    last_name   varchar   not null,
    birthday    date      not null
);

create unique index t_author_id_uindex
    on t_author (id);

create table t_book
(
    id           bigserial not null
        constraint t_book_pk
            primary key,
    name         varchar   not null,
    pages_count  integer   not null,
    publish_year bigint,
    author_id    bigint    not null
        constraint t_book_t_author_id_fk
            references t_author
);

create unique index t_book_id_uindex
    on t_book (id);