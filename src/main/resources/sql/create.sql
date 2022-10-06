-- ----------------------------------------------
-- add few addresses
INSERT INTO address (id, street_name, street_number, door_number, postal_code, city, country) VALUES (1, 'Koszycka', '6A', '25', '01-446', 'Warszawa', 'POLAND');
INSERT INTO address (id, street_name, street_number, door_number, postal_code, city, country) VALUES (2, 'Tyszkiewicza', '28', '3', '01-182', 'Warszawa', 'POLAND');
INSERT INTO address (id, street_name, street_number, door_number, postal_code, city, country) VALUES (3, 'Syta', '50', null, '01-446', 'Warszawa', 'POLAND');

-- ----------------------------------------------
-- add few accounts
INSERT INTO account (id, email, password, created_at, updated_at, role) VALUES (1, 'a1@gmail.com', '111', STR_TO_DATE('21-03-2007 05:31:55', '%d-%m-%Y %H:%i:%s'), null, 'ADMIN');
INSERT INTO account (id, email, password, created_at, updated_at, role) VALUES (101, 'b2@gmail.com', '222', STR_TO_DATE('14-11-2008 20:22:31', '%d-%m-%Y %H:%i:%s'), null, 'MODERATOR');
INSERT INTO account (id, email, password, created_at, updated_at, role) VALUES (1001, 'c3@gmail.com', '333', STR_TO_DATE('05-06-2009 11:44:07', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('06-06-2009 12:44:07', '%d-%m-%Y %H:%i:%s'), 'USER');
INSERT INTO account (id, email, password, created_at, updated_at, role) VALUES (1002, 'd4@gmail.com', '444', STR_TO_DATE('05-06-2009 11:44:07', '%d-%m-%Y %H:%i:%s'), null, 'USER');

-- add few customers
INSERT INTO customer (id, first_name, last_name, birth_date, phone_number, account_id, billing_address_id) VALUES (1, 'Michal', 'Abacki', STR_TO_DATE('21-03-1994', '%d-%m-%Y'), '111222333', 1001, 1);
INSERT INTO customer (id, first_name, last_name, birth_date, phone_number, account_id, billing_address_id) VALUES (2, 'Weronika', 'Babacka', STR_TO_DATE('11-06-1997', '%d-%m-%Y'), '222333444', 1002, 2);

-- ----------------------------------------------
-- add few categories
INSERT INTO category (id, name, description) VALUES (1, 'Smartphone', 'Smartphone simple description');
INSERT INTO category (id, name, description) VALUES (2, 'Tablet', 'Tablet simple description');
INSERT INTO category (id, name, description) VALUES (3, 'Notebook', 'Notebook simple description');

-- add few logos
INSERT INTO upload_file (id, file_name, data) VALUES (1, 'apple.jpg', null);
INSERT INTO upload_file (id, file_name, data) VALUES (2, 'samsung.jpg', null);
INSERT INTO upload_file (id, file_name, data) VALUES (3, 'huawei.jpg', null);

-- add few companies
INSERT INTO company (id, name, website, phone_number, logo_id) VALUES (1, 'Apple', 'www.apple.com', '800123456', 1);
INSERT INTO company (id, name, website, phone_number, logo_id) VALUES (2, 'Samsung', 'www.samsung.com', '800111222', 2);
INSERT INTO company (id, name, website, phone_number, logo_id) VALUES (3, 'Huawei', 'www.huawei.com', '800112233', 3);

-- ----------------------------------------------
-- SMARTPHONE
-- ----------------------------------------------
-- add few pictures
INSERT INTO upload_file (id, file_name, data) VALUES (1001, 'product1001.jpg', null);
INSERT INTO upload_file (id, file_name, data) VALUES (1002, 'product1002.jpg', null);
INSERT INTO upload_file (id, file_name, data) VALUES (1003, 'product1003.jpg', null);
INSERT INTO upload_file (id, file_name, data) VALUES (1004, 'product1004.jpg', null);

-- add few product specs
INSERT INTO product_specs (id, name) VALUES (1, 'spec 1');
INSERT INTO product_specs (id, name) VALUES (2, 'spec 2');
INSERT INTO product_specs (id, name) VALUES (3, 'spec 3');
INSERT INTO product_specs (id, name) VALUES (4, 'spec 4');

-- add few product details
INSERT INTO product_details (id, created_at, updated_at, account_id) VALUES (1, STR_TO_DATE('21-03-2007 05:31:55', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('21-03-2007 06:31:55', '%d-%m-%Y %H:%i:%s'), 1);
INSERT INTO product_details (id, created_at, updated_at, account_id) VALUES (2, STR_TO_DATE('22-03-2007 05:31:55', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('22-03-2007 06:31:55', '%d-%m-%Y %H:%i:%s'), 1);
INSERT INTO product_details (id, created_at, updated_at, account_id) VALUES (3, STR_TO_DATE('23-03-2007 05:31:55', '%d-%m-%Y %H:%i:%s'), null, 1);
INSERT INTO product_details (id, created_at, updated_at, account_id) VALUES (4, STR_TO_DATE('24-03-2007 05:31:55', '%d-%m-%Y %H:%i:%s'), null, 1);

-- add few products
INSERT INTO product (id, name, description, unit_price, category_id, company_id, picture_id, product_specs_id, product_details_id) VALUES (1, 'iPhone 13 Plus', 'iPhone 13 Plus simple description', 4479.00, 1, 1, 1001, 1, 1);
INSERT INTO product (id, name, description, unit_price, category_id, company_id, picture_id, product_specs_id, product_details_id) VALUES (2, 'iPhone 13', 'iPhone 13 simple description', 3879.00, 1, 1, 1002, 2, 2);
INSERT INTO product (id, name, description, unit_price, category_id, company_id, picture_id, product_specs_id, product_details_id) VALUES (3, 'iPhone 12 SE', 'iPhone 12 SE simple description', 2999.00, 1, 1, 1003, 3, 3);
INSERT INTO product (id, name, description, unit_price, category_id, company_id, picture_id, product_specs_id, product_details_id) VALUES (4, 'Galaxy 10', 'Galaxy 10 simple description', 3349.00, 1, 2, 1004, 4, 4);

-- ----------------------------------------------
-- TABLET
-- ----------------------------------------------
-- add few pictures
INSERT INTO upload_file (id, file_name, data) VALUES (1005, 'product1005.jpg', null);

-- add few product details
INSERT INTO product_details (id, created_at, updated_at, account_id) VALUES (5, STR_TO_DATE('25-03-2007 05:31:55', '%d-%m-%Y %H:%i:%s'), null, 1);

-- add few products
INSERT INTO product (id, name, description, unit_price, category_id, company_id, picture_id, product_specs_id, product_details_id) VALUES (5, 'iPad 5', 'iPad 5 simple description', 2399.00, 2, 1, 1005, null, 5);

-- ----------------------------------------------
-- add few comments
INSERT INTO comment (id, rating, created_at, updated_at, content, number_of_likes, number_of_dislikes, product_id, account_id) VALUES (1,'FOUR', STR_TO_DATE('05-06-2009 11:44:07', '%d-%m-%Y %H:%i:%s'), STR_TO_DATE('06-06-2009 12:44:07', '%d-%m-%Y %H:%i:%s'), 'Comment 1 to Apple iPhone 13 Plus', 5, 2, 1, 1001);
INSERT INTO comment (id, rating, created_at, updated_at, content, number_of_likes, number_of_dislikes, product_id, account_id) VALUES (2,'FIVE', STR_TO_DATE('06-06-2009 11:44:07', '%d-%m-%Y %H:%i:%s'), null, 'Comment 2 to Apple iPhone 13 Plus', 2, 1, 1, 1002);
INSERT INTO comment (id, rating, created_at, updated_at, content, number_of_likes, number_of_dislikes, product_id, account_id) VALUES (3,'TWO', STR_TO_DATE('07-06-2009 11:44:07', '%d-%m-%Y %H:%i:%s'), null, 'Comment 1 to Apple iPhone 13', 0, 0, 2, 1001);
INSERT INTO comment (id, rating, created_at, updated_at, content, number_of_likes, number_of_dislikes, product_id, account_id) VALUES (4,'THREE', STR_TO_DATE('08-06-2009 11:44:07', '%d-%m-%Y %H:%i:%s'), null, 'Comment 2 to Apple iPhone 13', 1, 5, 2, 1002);

-- ----------------------------------------------
-- add few tags
INSERT INTO tag (id, name) VALUES (1, 'Best offer');
INSERT INTO tag (id, name) VALUES (2, 'Hurry up');

-- add few tutorials tags
INSERT INTO product_tag (product_id, tag_id) VALUES (1, 1);
INSERT INTO product_tag (product_id, tag_id) VALUES (1, 2);
INSERT INTO product_tag (product_id, tag_id) VALUES (2, 1);
INSERT INTO product_tag (product_id, tag_id) VALUES (3, 2);

-- ----------------------------------------------
-- add few stocks
INSERT INTO stock (id, quantity, product_id) VALUES (1, 10, 1);
INSERT INTO stock (id, quantity, product_id) VALUES (2, 20, 2);
INSERT INTO stock (id, quantity, product_id) VALUES (3, 30, 3);
INSERT INTO stock (id, quantity, product_id) VALUES (4, 1, 4);
INSERT INTO stock (id, quantity, product_id) VALUES (5, 0, 5);

-- ----------------------------------------------
-- add few orders
INSERT INTO orders (id, created_at, amount, customer_id) VALUES (1, STR_TO_DATE('19-03-2016 18:22:33', '%d-%m-%Y %H:%i:%s'), 25993.00, 1);
INSERT INTO orders (id, created_at, amount, customer_id) VALUES (2, STR_TO_DATE('01-04-2016 17:20:03', '%d-%m-%Y %H:%i:%s'), 57027.00, 1);
INSERT INTO orders (id, created_at, amount, customer_id) VALUES (3, STR_TO_DATE('11-05-2016 08:47:22', '%d-%m-%Y %H:%i:%s'), 4479.00, 2);
INSERT INTO orders (id, created_at, amount, customer_id) VALUES (4, STR_TO_DATE('21-05-2016 08:47:22', '%d-%m-%Y %H:%i:%s'), 4798.00, 2);

-- add few items
INSERT INTO item (id, quantity, unit_price, amount, product_id, order_id) VALUES (1, 1, 4479.00, 4479.00, 1, 1);
INSERT INTO item (id, quantity, unit_price, amount, product_id, order_id) VALUES (2, 4, 3879.00, 15516.00, 2, 1);
INSERT INTO item (id, quantity, unit_price, amount, product_id, order_id) VALUES (3, 2, 2999.00, 5998.00, 3, 1);
INSERT INTO item (id, quantity, unit_price, amount, product_id, order_id) VALUES (4, 11, 4479.00, 49269.00, 1, 2);
INSERT INTO item (id, quantity, unit_price, amount, product_id, order_id) VALUES (5, 1, 3879.00, 7758.00, 2, 2);
INSERT INTO item (id, quantity, unit_price, amount, product_id, order_id) VALUES (6, 1, 4479.00, 4479.00, 1, 3);
INSERT INTO item (id, quantity, unit_price, amount, product_id, order_id) VALUES (7, 2, 2399.00, 4798.00, 5, 4);

-- add few shipments
INSERT INTO shipment (id, shipping_date, tracking_number, delivery_date, shipment_status, order_id, shipping_address_id) VALUES (1, STR_TO_DATE('21-03-2016 18:22:33', '%d-%m-%Y %H:%i:%s'), 'PARCELno1', STR_TO_DATE('21-03-2016 18:22:33', '%d-%m-%Y %H:%i:%s'), 'DELIVERED', 1, 1);
INSERT INTO shipment (id, shipping_date, tracking_number, delivery_date, shipment_status, order_id, shipping_address_id) VALUES (2, STR_TO_DATE('22-03-2016 19:22:33', '%d-%m-%Y %H:%i:%s'), 'PARCELno2', null, 'IN_TRANSIT', 2, 1);
INSERT INTO shipment (id, shipping_date, tracking_number, delivery_date, shipment_status, order_id, shipping_address_id) VALUES (3, null, null, null, 'PROCESSING', 3, null);


