create or replace procedure calc_discount(txtids text, new_discount integer) as $$
    declare
cust RECORD;
        purch RECORD;
        ids integer[] := string_to_array(txtids, ',');
begin
for cust in select * from customer loop
    for purch in select * from purchase
            where customer_id = cust.id
              and product_id = ANY (ids) loop
update customer set discount = new_discount
where id = cust.id;
end loop;
end loop;
end;
    $$
language plpgsql;

CALL calc_discount('2,4,6,8,10,12,14,16', 30);

update customer set discount = 35
where id in (select customer_id from purchase where product_id in (2,4,6,8,10,12,14,16));