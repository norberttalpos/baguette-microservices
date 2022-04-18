insert into measurement_unit (name) values ('kg');

insert into product_category (name, parent) values ('Meat', 0);

insert into product_brand (country, name) values ('Hungary', 'Pick');

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.5, 'Téliszalámi', 100, 9.1, 5000, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Pick' and pc.name = 'Meat' and mu.name = 'kg';