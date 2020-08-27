drop table if exists product;
create table product
(
    product_id  bigint auto_increment
        primary key,
    name    varchar(100) not null,
    price double null,
    available_stock  int          null
);

drop table if exists basket;
create table basket
(
    basket_id  bigint auto_increment
        primary key,
    product_id    bigint not null,
    quantity  int          null,
    create_at   datetime null
);