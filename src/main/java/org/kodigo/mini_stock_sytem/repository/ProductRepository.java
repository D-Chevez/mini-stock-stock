package org.kodigo.mini_stock_sytem.repository;

import org.kodigo.mini_stock_sytem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
