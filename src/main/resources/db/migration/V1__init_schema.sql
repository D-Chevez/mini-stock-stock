-- V1__init_schema.sql (MySQL/MariaDB 5.5+ friendly)

-- === categories ===
DROP TABLE IF EXISTS categories;
CREATE TABLE categories (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  name        VARCHAR(120)   NOT NULL,
  description VARCHAR(255),
  is_active   TINYINT(1)     NOT NULL DEFAULT 1,
  created_at  TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  -- In old MySQL/MariaDB only one TIMESTAMP can auto-init/update.
  -- Keep updated_at nullable and update it from the application/service layer.
  updated_at  TIMESTAMP      NULL DEFAULT NULL
) ENGINE=InnoDB;

CREATE UNIQUE INDEX ux_categories_name ON categories(name);

-- === products ===
DROP TABLE IF EXISTS products;
CREATE TABLE products (
  id          BIGINT AUTO_INCREMENT PRIMARY KEY,
  sku         VARCHAR(64)    NOT NULL,
  name        VARCHAR(160)   NOT NULL,
  category_id BIGINT         NULL,
  price       DECIMAL(12,2)  NOT NULL,
  min_stock   INT            NOT NULL DEFAULT 0,
  stock       INT            NOT NULL DEFAULT 0,
  is_active   TINYINT(1)     NOT NULL DEFAULT 1,
  created_at  TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at  TIMESTAMP      NULL DEFAULT NULL,
  CONSTRAINT fk_products_category
    FOREIGN KEY (category_id) REFERENCES categories(id)
) ENGINE=InnoDB;

CREATE UNIQUE INDEX ux_products_sku   ON products(sku);
CREATE INDEX        ix_products_category ON products(category_id);

-- === customers ===
DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
  id         BIGINT AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(160)   NOT NULL,
  email      VARCHAR(160),
  phone      VARCHAR(40),
  is_active  TINYINT(1)     NOT NULL DEFAULT 1,
  created_at TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP      NULL DEFAULT NULL
) ENGINE=InnoDB;

CREATE UNIQUE INDEX ux_customers_email ON customers(email);

-- === suppliers ===
DROP TABLE IF EXISTS suppliers;
CREATE TABLE suppliers (
  id         BIGINT AUTO_INCREMENT PRIMARY KEY,
  name       VARCHAR(160)   NOT NULL,
  email      VARCHAR(160),
  phone      VARCHAR(40),
  is_active  TINYINT(1)     NOT NULL DEFAULT 1,
  created_at TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP      NULL DEFAULT NULL
) ENGINE=InnoDB;

CREATE UNIQUE INDEX ux_suppliers_email ON suppliers(email);

-- === movements ===
DROP TABLE IF EXISTS movements;
CREATE TABLE movements (
  id               BIGINT AUTO_INCREMENT PRIMARY KEY,
  product_id       BIGINT        NOT NULL,
  type             VARCHAR(16)   NOT NULL,   -- IN | OUT | ADJUST
  qty              INT           NOT NULL,
  reason           VARCHAR(255),
  customer_id      BIGINT        NULL,
  supplier_id      BIGINT        NULL,
  meta             LONGTEXT,                 -- use JSON in MySQL 8+ if available
  idempotency_key  VARCHAR(80),
  created_by       VARCHAR(80),
  created_at       TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_mov_product   FOREIGN KEY (product_id)  REFERENCES products(id),
  CONSTRAINT fk_mov_customer  FOREIGN KEY (customer_id) REFERENCES customers(id),
  CONSTRAINT fk_mov_supplier  FOREIGN KEY (supplier_id) REFERENCES suppliers(id)
  -- No CHECK here (not supported in MySQL 5.5/MariaDB old)
) ENGINE=InnoDB;

CREATE UNIQUE INDEX ux_movements_idempotency ON movements(idempotency_key);
CREATE INDEX        ix_movements_product     ON movements(product_id);
CREATE INDEX        ix_movements_customer    ON movements(customer_id);
CREATE INDEX        ix_movements_supplier    ON movements(supplier_id);
CREATE INDEX        ix_movements_type        ON movements(type);
