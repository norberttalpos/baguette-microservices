/*
    Measurement Unit
 */

insert into measurement_unit (name) values ('kg');
insert into measurement_unit (name) values ('dkg');
insert into measurement_unit (name) values ('g');
insert into measurement_unit (name) values ('l');
insert into measurement_unit (name) values ('dl');

/*
    Product Category
 */

--- root categories

insert into product_category (name, parent) values ('grocery', null);

insert into product_category (name, parent)
select 'meat', pc.id from product_category pc where pc.name = 'grocery';
insert into product_category (name, parent)
select 'fruits and vegetables', pc.id from product_category pc where pc.name = 'grocery';
insert into product_category (name, parent)
select 'baked goods', pc.id from product_category pc where pc.name = 'grocery';
insert into product_category (name, parent)
select 'dairy', pc.id from product_category pc where pc.name = 'grocery';
insert into product_category (name, parent)
select 'drink', pc.id from product_category pc where pc.name = 'grocery';

--- meat

insert into product_category (name, parent)
select 'poultry', pc.id from product_category pc where pc.name = 'meat';
insert into product_category (name, parent)
select 'pork', pc.id from product_category pc where pc.name = 'meat';
insert into product_category (name, parent)
select 'beef', pc.id from product_category pc where pc.name = 'meat';
insert into product_category (name, parent)
select 'fish', pc.id from product_category pc where pc.name = 'meat';
insert into product_category (name, parent)
select 'cold cut', pc.id from product_category pc where pc.name = 'meat';

--- fruits and vegetables

insert into product_category (name, parent)
select 'fruit', pc.id from product_category pc where pc.name = 'fruits and vegetables';
insert into product_category (name, parent)
select 'vegetable', pc.id from product_category pc where pc.name = 'fruits and vegetables';
insert into product_category (name, parent)
select 'nuts', pc.id from product_category pc where pc.name = 'fruits and vegetables';

--- baked goods

insert into product_category (name, parent)
select 'bread', pc.id from product_category pc where pc.name = 'baked goods';
insert into product_category (name, parent)
select 'bakery products', pc.id from product_category pc where pc.name = 'baked goods';
insert into product_category (name, parent)
select 'salted pastries', pc.id from product_category pc where pc.name = 'baked goods';

--- dairy

insert into product_category (name, parent)
select 'cheese', pc.id from product_category pc where pc.name = 'dairy';
insert into product_category (name, parent)
select 'milk', pc.id from product_category pc where pc.name = 'dairy';
insert into product_category (name, parent)
select 'yoghurt', pc.id from product_category pc where pc.name = 'dairy';
insert into product_category (name, parent)
select 'egg', pc.id from product_category pc where pc.name = 'dairy';

--- drink

insert into product_category (name, parent)
select 'water', pc.id from product_category pc where pc.name = 'drink';
insert into product_category (name, parent)
select 'soft drink', pc.id from product_category pc where pc.name = 'drink';
insert into product_category (name, parent)
select 'beer', pc.id from product_category pc where pc.name = 'drink';
insert into product_category (name, parent)
select 'wine', pc.id from product_category pc where pc.name = 'drink';
insert into product_category (name, parent)
select 'hard liquor', pc.id from product_category pc where pc.name = 'drink';

insert into product_category (name, parent)
select 'lager', pc.id from product_category pc where pc.name = 'beer';
insert into product_category (name, parent)
select 'ipa', pc.id from product_category pc where pc.name = 'beer';

/*
    Product Brand
 */

insert into product_brand (country, name) values ('Hungary', 'Norbi Grocery');

/*
    Product
 */

/*
    Meat
 */

--- poultry

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.5, 'Chicken Breast', 100, 10.0, 2000, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'poultry' and mu.name = 'kg';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.3, 'Bio Chicken Breast', 100, 10.0, 2500, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'poultry' and mu.name = 'kg';

--- pork

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 1.06, 'Pork Chop', 100, 10.0, 2000, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'pork' and mu.name = 'kg';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.3, 'Pork Bacon', 100, 10.0, 3000, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'pork' and mu.name = 'kg';

--- beef

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.3, 'Black Angus Filet', 100, 10.0, 3000, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'beef' and mu.name = 'kg';

--- fish

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.45, 'Salmon', 100, 10.0, 6000, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'fish' and mu.name = 'kg';

--- cold cut

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.5, 'Salami', 100, 10.0, 5000, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'cold cut' and mu.name = 'kg';

/*
    Fruits and vegetables
 */

--- fruit

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.18, 'Apple', 100, 10.0, 500, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'fruit' and mu.name = 'kg';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.2, 'Lemon', 100, 10.0, 750, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'fruit' and mu.name = 'kg';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.5, 'Grapes', 100, 10.0, 2000, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'fruit' and mu.name = 'kg';

--- vegetables

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.5, 'Broccoli', 100, 10.0, 1100, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'vegetable' and mu.name = 'kg';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.22, 'Carrot', 100, 10.0, 350, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'vegetable' and mu.name = 'kg';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.27, 'Cucumber', 100, 10.0, 1600, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'vegetable' and mu.name = 'kg';

--- nuts

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.32, 'Peanuts', 100, 10.0, 1600, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'nuts' and mu.name = 'kg';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.3, 'Cashew Nuts', 100, 10.0, 5300, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'nuts' and mu.name = 'kg';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.2, 'Walnuts', 100, 10.0, 7000, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'nuts' and mu.name = 'kg';

/*
    Baked goods
 */

--- bread

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.4, 'White Bread', 100, 10.0, 1500, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'bread' and mu.name = 'kg';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.4, 'Whole Grain Bread', 100, 10.0, 1500, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'bread' and mu.name = 'kg';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.25, 'Gluten-Free Bread', 100, 10.0, 3200, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'bread' and mu.name = 'kg';

--- bakery products

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.055, 'Crosissant', 100, 10.0, 5400, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'bakery products' and mu.name = 'kg';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.07, 'Donut', 100, 10.0, 3500, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'bakery products' and mu.name = 'kg';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.1, 'Muffin', 100, 10.0, 4500, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'bakery products' and mu.name = 'kg';

--- salted pastries

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.13, 'Baguette', 100, 10.0, 1500, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'salted pastries' and mu.name = 'kg';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.1, 'Pretzel', 100, 10.0, 1900, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'salted pastries' and mu.name = 'kg';

/*
    Dairy
 */

--- cheese

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.7, 'Trappista', 100, 10.0, 4000, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'cheese' and mu.name = 'kg';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.15, 'Parmagiano', 100, 10.0, 10200, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'cheese' and mu.name = 'kg';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.15, 'Emmentaler', 100, 10.0, 3500, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'cheese' and mu.name = 'kg';

--- milk

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 1, 'Low-Fat Milk', 100, 10.0, 380, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'milk' and mu.name = 'l';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 1, 'Lactose-Free Milk', 100, 10.0, 470, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'milk' and mu.name = 'l';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 1, 'Coconut Milk', 100, 10.0, 900, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'milk' and mu.name = 'l';

--- yoghurt

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.15, 'Unflavoured Yoghurt', 100, 10.0, 770, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'yoghurt' and mu.name = 'kg';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.12, 'Vegan Yoghurt', 100, 10.0, 3300, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'yoghurt' and mu.name = 'kg';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.15, 'Riso', 100, 10.0, 2400, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'yoghurt' and mu.name = 'kg';

--- egg

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 10, 'Regular eggs', 100, 10.0, 75, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'egg' and mu.name = 'db';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 10, 'Bio eggs', 100, 10.0, 120, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'egg' and mu.name = 'db';

/*
    Drinks
 */

--- water

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 6, 'Still Water', 100, 10.0, 100, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'water' and mu.name = 'l';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 6, 'Sparkling Water', 100, 10.0, 100, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'water' and mu.name = 'l';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 1, 'Lime-Flavoured Water', 100, 10.0, 120, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'water' and mu.name = 'l';

--- soft drink

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 1.5, 'Iced Tea', 100, 10.0, 260, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'soft drink' and mu.name = 'l';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 1.5, 'Coca-Cola', 100, 10.0, 250, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'soft drink' and mu.name = 'l';

--- beer

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.5, 'Cherry Beer', 100, 10.0, 700, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'beer' and mu.name = 'l';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.5, 'Lager Beer', 100, 10.0, 700, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'beer' and mu.name = 'l';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.5, 'Ipa Beer', 100, 10.0, 700, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'beer' and mu.name = 'l';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.5, 'Brown Beer', 100, 10.0, 700, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'beer' and mu.name = 'l';

--- wine

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.7, 'Dry White Wine', 100, 10.0, 1100, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'wine' and mu.name = 'l';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.7, 'Red Wine', 100, 10.0, 1300, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'wine' and mu.name = 'l';

--- hard liquor

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.7, 'Tequila', 100, 10.0, 10000, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'hard liquor' and mu.name = 'l';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.7, 'Vodka', 100, 10.0, 7900, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'hard liquor' and mu.name = 'l';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.7, 'Jäger', 100, 10.0, 7900, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'hard liquor' and mu.name = 'l';

insert into product (amount, name, quantity, rating, unit_price, product_brand_id, product_category_id, measurement_unit_id)
select 0.7, 'Tatratea', 100, 10.0, 12000, pb.id, pc.id, mu.id
from product_brand pb, product_category pc, measurement_unit mu
where pb.name = 'Norbi Grocery' and pc.name = 'hard liquor' and mu.name = 'l';