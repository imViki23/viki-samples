## Joins

### INNER JOIN

Returns only the rows that have matching values in both tables.

```sql
SELECT b.name as brand_name, p.name as product_name, pv.sku, pv.attributes
    FROM catalog.product_variants as pv
    JOIN catalog.products p ON p.product_id = pv.product_id
    JOIN catalog.brands b ON p.brand_id = b.brand_id;
```

|brand_name|product_name    |sku            |
|----------|----------------|---------------|
|Apple     |iPhone 17       |IP17LVD256     |
|Apple     |iPhone 17       |IP17LVD512     |
|Samsung   |Samsung S25     |SM25ULBLK256   |

### LEFT JOIN

Returns all rows from the left table, and the matched rows from the right table (with NULL if no match).

```sql
SELECT b.name as brand_name, p.name as product_name
    FROM catalog.brands b
    LEFT JOIN catalog.products p ON p.brand_id = b.brand_id;
```

|brand_name|product_name    |
|----------|----------------|
|Apple     |iPhone 17       |
|Apple     |iPhone 16       |
|Samsung   |Samsung S25     |
|Sony      |NULL            |

### RIGHT JOIN

Returns all rows from the right table, and the matched rows from the left table (with NULL if no match).

```sql
SELECT b.name as brand_name, p.name as product_name
    FROM catalog.products p
    RIGHT JOIN catalog.brands b ON p.brand_id = b.brand_id;
```

|brand_name|product_name    |
|----------|----------------|
|Apple     |iPhone 17       |
|Apple     |iPhone 16       |
|Samsung   |Samsung S25     |
|Sony      |NULL            |

### FULL OUTER JOIN

Returns all rows when there is a match in either the left or right table, filling missing sides with NULL.

```sql
SELECT b.name AS brand_name, p.name AS product_name
    FROM catalog.brands b
    FULL OUTER JOIN catalog.products p ON b.brand_id = p.brand_id;
```

## ON DELETE - Handling SQL delete

```sql
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
    CONSTRAINT fk_product_variants_products FOREIGN KEY (product_id) REFERENCES catalog.products(product_id) ON DELETE CASCADE -- ON DELETE
);

DELETE FROM catalog.products where product_id = '7d2465ce-bc73-4876-bb29-1609143a3164'; -- DELETES entry in product table too
```

- *ON DELETE RESTRICT (or NO ACTION)*: Prevents you from deleting the parent until all child records are manually deleted or reassigned. This acts as a safety guard against accidental data loss.
- *ON DELETE SET NULL*: Deletes the parent, but keeps the child records by setting the foreign key column to NULL (useful optional relationships).

## What is ACID ?

- *Atomicity* - A transaction is treated as a single, indivisible unit of work. Either all operations within the transaction succeed, or none of them are applied. Transferring money from Account A to Account B. If the money is debited from Account A but a system crash prevents it from being credited to Account B, atomicity ensures the entire transaction is rolled back so Account A doesn't lose its money.
- *Consistency* - If any operation violates a database constraint, the entire transaction fails and the database rolls back to its prior state. If a table rule dictates that an account balance cannot fall below zero, a transaction attempting to withdraw more than the available balance will be rejected to maintain consistency.
- *Isloation* - When multiple transactions occur simultaneously, the execution of one transaction must be isolated from the others. The intermediate state of a concurrent transaction must be invisible to other transactions until it is fully committed. This prevents race conditions and data corruption. Two people trying to book the last seat on a flight at the exact same millisecond. Isolation ensures that one transaction completes and commits first, while the second transaction sees the updated seat availability and handles the conflict appropriately rather than both succeeding.
- *Durability* - Once a transaction has been successfully committed, its changes are permanent. Even if the server crashes, loses power, or suffers a catastrophic hardware failure (typically achieved using Write-Ahead Logging / WAL).