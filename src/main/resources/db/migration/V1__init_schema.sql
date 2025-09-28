CREATE TABLE categories (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  name          VARCHAR(120)    NOT NULL,
  description   VARCHAR(255),
  is_active     BOOLEAN         NOT NULL DEFAULT TRUE,
  created_at    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX ux_categories_name ON categories(name);

CREATE TABLE products (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  sku           VARCHAR(64)     NOT NULL,
  name          VARCHAR(160)    NOT NULL,
  category_id   BIGINT,
  price_cents   INT             NOT NULL,
  min_stock     INT             NOT NULL DEFAULT 0,
  stock         INT             NOT NULL DEFAULT 0,
  is_active     BOOLEAN         NOT NULL DEFAULT TRUE,
  created_at    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT fk_products_category
    FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE UNIQUE INDEX ux_products_sku ON products(sku);
CREATE INDEX ix_products_category ON products(category_id);

CREATE TABLE customers (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  name          VARCHAR(160)    NOT NULL,
  email         VARCHAR(160),
  phone         VARCHAR(40),
  is_active     BOOLEAN         NOT NULL DEFAULT TRUE,
  created_at    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX ux_customers_email ON customers(email);

CREATE TABLE suppliers (
  id            BIGINT AUTO_INCREMENT PRIMARY KEY,
  name          VARCHAR(160)    NOT NULL,
  email         VARCHAR(160),
  phone         VARCHAR(40),
  is_active     BOOLEAN         NOT NULL DEFAULT TRUE,
  created_at    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX ux_suppliers_email ON suppliers(email);

-- Enum simulation with VARCHAR for portability. Values: IN, OUT, ADJUST
CREATE TABLE movements (
  id                BIGINT AUTO_INCREMENT PRIMARY KEY,
  product_id        BIGINT          NOT NULL,
  type              VARCHAR(16)     NOT NULL,
  qty               INT             NOT NULL,
  reason            VARCHAR(255),
  customer_id       BIGINT,
  supplier_id       BIGINT,
  meta              CLOB,                 -- JSON as text for H2; in MySQL you can change to JSON
  idempotency_key   VARCHAR(80),
  created_by        VARCHAR(80),
  created_at        TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT fk_mov_product   FOREIGN KEY (product_id)  REFERENCES products(id),
  CONSTRAINT fk_mov_customer  FOREIGN KEY (customer_id) REFERENCES customers(id),
  CONSTRAINT fk_mov_supplier  FOREIGN KEY (supplier_id) REFERENCES suppliers(id),
  CONSTRAINT chk_mov_qty      CHECK (qty <> 0)
);

CREATE UNIQUE INDEX ux_movements_idempotency ON movements(idempotency_key);
CREATE INDEX ix_movements_product ON movements(product_id);
CREATE INDEX ix_movements_customer ON movements(customer_id);
CREATE INDEX ix_movements_supplier ON movements(supplier_id);
CREATE INDEX ix_movements_type ON movements(type);
