-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- CREATE SCHEMA IF NOT EXISTS ${SCHEMA_NAME}

-- CREATE TABLE ${TABLE_NAME} (
--     ${COLUMN_NAME} ${DATA_TYPE} [NOT NULL,UNIQUE] [CHECK (${COLUMN_NAME} condition)] [DEFAULT ${DEFAULT_VALUE}],
--     ${COLUMN_NAME} TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
--     CONSTRAINT ${CONSTRAINT_NAME:pk_table} PRIMARY KEY (${COLUMN_NAMES....}),
--     CONSTRAINT ${CONSTRAINT_NAME:fk_table1_table2} FOREIGN KEY (${COLUMN_NAME}) REFERENCES ${SCHEMA_NAME}.${TABLE_NAME}(${COLUMN_NAME})
-- )

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP SCHEMA catalog CASCADE;

CREATE SCHEMA IF NOT EXISTS catalog;

CREATE TABLE catalog.brands(
    brand_id UUID DEFAULT uuid_generate_v4(),
    name TEXT NOT NULL,
    CONSTRAINT pk_brands PRIMARY KEY (brand_id)
);
CREATE TABLE catalog.products(
    product_id UUID DEFAULT uuid_generate_v4(),
    name TEXT NOT NULL,
    slug TEXT NOT NULL,
    brand_id UUID NOT NULL,
    CONSTRAINT pk_products PRIMARY KEY (product_id),
    CONSTRAINT fk_products_brands FOREIGN KEY (brand_id) REFERENCES catalog.brands(brand_id)
);
CREATE TABLE catalog.product_variants(
    variant_id UUID DEFAULT uuid_generate_v4(),
    sku TEXT NOT NULL UNIQUE,
    attributes JSONB NOT NULL,
    product_id UUID NOT NULL,
    CONSTRAINT pk_product_variants PRIMARY KEY (variant_id),
    CONSTRAINT fk_product_variants_products FOREIGN KEY (product_id) REFERENCES catalog.products(product_id)
);

-- Brands
INSERT INTO catalog.brands (brand_id, name) VALUES
('77f3f0cc-97d3-487d-aae4-c0cafd469d1f', 'Apple'  ),
('439e8d63-c99c-49b4-a51c-74c74193370d', 'Samsung'),
('091e94ec-7768-4e79-896d-bcff5a324731', 'Sony');

-- Products
INSERT INTO catalog.products (product_id, brand_id, name, slug) VALUES
('7d2465ce-bc73-4876-bb29-1609143a3164', '77f3f0cc-97d3-487d-aae4-c0cafd469d1f', 'iPhone 17',   'iphone-17'),
('f79b926c-695a-40fb-9eab-ccb41952e237', '77f3f0cc-97d3-487d-aae4-c0cafd469d1f', 'iPhone 16',   'iphone-16'),
('6385a7d4-55a9-4940-9b10-3fe78fd30279', '439e8d63-c99c-49b4-a51c-74c74193370d', 'Samsung S25', 'samsung-s25');

-- Variants
INSERT INTO catalog.product_variants (variant_id, product_id, sku, attributes) VALUES
('1a9cafa3-912f-4478-a28c-47350fb0ebc0', '7d2465ce-bc73-4876-bb29-1609143a3164', 'IP17LVD256',    '{"color": "Lavendar", "memory": "256GB"}'::jsonb ),  -- IPhone 17 Lavendar 256 GB
('9b7e6718-1e96-4a93-9f6a-cf3014127331', '7d2465ce-bc73-4876-bb29-1609143a3164', 'IP17LVD512',    '{"color": "Lavendar", "memory": "512GB"}'::jsonb ),  -- IPhone 17 Lavendar 512 GB
('d93dcbfc-714c-4439-a3d5-c86a8ae3825f', 'f79b926c-695a-40fb-9eab-ccb41952e237', 'IP16WHT128',    '{"color": "White", "memory": "128GB"}'::jsonb    ),  -- IPhone 16 White 128 GB
('08eaad11-1d6c-4425-8336-4cfc98b968cf', '6385a7d4-55a9-4940-9b10-3fe78fd30279', 'SM25ULBLK256',  '{"color": "Black", "memory": "256GB"}'::jsonb    );  -- Samsung S25 Black 256 GB

SELECT p.name as product_name FROM catalog.products p;

-- INNER JOIN (INTERSECTION) - Sony wont be shown
SELECT b.name as brand_name, p.name as product_name, pv.sku, pv.attributes
    FROM catalog.product_variants as pv
    JOIN catalog.products p ON p.product_id = pv.product_id
    JOIN catalog.brands b ON p.brand_id = b.brand_id;

-- LEFT JOIN - Sony wont be shown even though it is orphaned
SELECT b.name as brand_name, p.name as product_name
    FROM catalog.brands b
    LEFT JOIN catalog.products p ON p.brand_id = b.brand_id;

-- RIGHT JOIN - Sony wont be shown even though it is orphaned
SELECT b.name as brand_name, p.name as product_name
    FROM catalog.products p
    RIGHT JOIN catalog.brands b ON p.brand_id = b.brand_id;

-- FULL OUTER JOIN
SELECT b.name AS brand_name, p.name AS product_name
    FROM catalog.brands b
    FULL OUTER JOIN catalog.products p ON b.brand_id = p.brand_id;