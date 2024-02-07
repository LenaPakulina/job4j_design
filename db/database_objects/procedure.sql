create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    END
$$;

call insert_data('product_1', 'producer_2', 15, 32);
call insert_data('product_2', 'producer_1', 1, 3);
call insert_data('product_3', 'producer_3', 123, 34223);

select * from products;

create
or replace procedure delete_data_with_small_count()
language 'plpgsql'
as $$
    BEGIN
        delete from products where count < 2;
    END;
$$;

call delete_data_with_small_count();

create
or replace function delete_data()
returns void
language 'plpgsql'
as
$$
    begin
        delete from products where count < 2;
    end;
$$;

select * from products;
