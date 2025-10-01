-- Seed data for categories
INSERT INTO categories (name, description) VALUES
  ('Electronics', 'Electronic devices and accessories'),
  ('Groceries', 'Everyday grocery items'),
  ('Sports', 'Sports equipment and clothing'),
  ('Furniture', 'Home and office furniture');

-- Seed data for products
INSERT INTO products (sku, name, category_id, price, min_stock, stock) VALUES
  ('ELEC-001', 'Smartphone X1', 1, 350.00, 5, 50),
  ('ELEC-002', 'Wireless Headphones', 1, 75.00, 10, 100),
  ('GROC-001', 'Organic Rice 1kg', 2, 2.50, 20, 200),
  ('GROC-002', 'Olive Oil 500ml', 2, 5.00, 10, 80),
  ('SPRT-001', 'Basketball Ball', 3, 25.00, 5, 30),
  ('SPRT-002', 'Yoga Mat', 3, 15.00, 5, 40),
  ('FURN-001', 'Office Chair', 4, 120.00, 2, 15),
  ('FURN-002', 'Study Desk', 4, 180.00, 1, 10);

-- Seed data for customers
INSERT INTO customers (name, email, phone) VALUES
  ('Alice Johnson', 'alice.johnson@example.com', '555-0101'),
  ('Bob Smith', 'bob.smith@example.com', '555-0102'),
  ('Carol White', 'carol.white@example.com', '555-0103');

-- Seed data for suppliers
INSERT INTO suppliers (name, email, phone) VALUES
  ('Tech Supplies Ltd', 'contact@techsupplies.com', '555-0201'),
  ('Fresh Foods Co.', 'sales@freshfoods.com', '555-0202'),
  ('Fit & Fun Sports', 'support@fitfun.com', '555-0203'),
  ('Office World', 'info@officeworld.com', '555-0204');

-- Seed data for movements
-- Positive qty = stock in, Negative qty = stock out
INSERT INTO movements (product_id, type, qty, reason, supplier_id, created_by) VALUES
  (1, 'IN',  20, 'Initial stock from supplier', 1, 'system'),
  (2, 'IN',  30, 'Initial stock from supplier', 1, 'system'),
  (3, 'IN', 100, 'Initial stock from supplier', 2, 'system'),
  (5, 'IN',  15, 'Initial stock from supplier', 3, 'system'),
  (7, 'IN',  10, 'Initial stock from supplier', 4, 'system'),
  (1, 'OUT', -2, 'Customer purchase', NULL, 'system'),
  (3, 'OUT', -5, 'Customer purchase', NULL, 'system'),
  (5, 'OUT', -1, 'Customer purchase', NULL, 'system');
