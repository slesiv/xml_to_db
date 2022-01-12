CREATE TABLE orgs(
    id serial primary key,
    name varchar(250),
    inn bigint UNIQUE NOT NULL,
    ogrn bigint,
    address varchar(250),
    postcode int,
    open_date Date,
    created_date Date default now()
);

INSERT INTO orgs(name, inn, ogrn, address, postcode, open_date)
VALUES ('Первая', 123456789, 123456789012, 'г. Москва, ул. Ячнева д.6 кв.15', 123456, '20.04.2012');