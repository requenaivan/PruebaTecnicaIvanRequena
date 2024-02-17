INSERT INTO currency(ID, iso, num, name) VALUES (1, 'EUR', 978, 'Euro');
INSERT INTO currency(ID, iso, num, name) VALUES (2, 'COP', 170, 'Colombian peso');
INSERT INTO currency(ID, iso, num, name) VALUES (3, 'USD', 840, 'United States dollar');

INSERT INTO brand(ID, name) VALUES (1, 'ZARA');
INSERT INTO brand(ID, name) VALUES (2, 'STRADIVARIUS');
INSERT INTO brand(ID, name) VALUES (3, 'BERSHKA');

INSERT  INTO product (id, price_list,brand_id,start_date,end_date,product_id,priority,price,curr)
VALUES (1, 1,1,'2020-06-14 00.00.00','2020-12-31 23.59.59',35455,0,35.50, 1);
INSERT  INTO product (id, price_list,brand_id,start_date,end_date,product_id,priority,price,curr)
VALUES (2, 2,1,'2020-06-14 15.00.00','2020-06-14 18.30.00',35455,1,25.45, 1);
INSERT  INTO product (id, price_list,brand_id,start_date,end_date,product_id,priority,price,curr)
VALUES (3, 3,1,'2020-06-15 00.00.00','2020-06-15 11.00.00',35455,1,30.50, 1);
INSERT  INTO product (id, price_list,brand_id,start_date,end_date,product_id,priority,price,curr)
VALUES (4, 4,1,'2020-06-15 16.00.00','2020-12-31 23.59.59',35455,1,38.95, 1);

commit;
