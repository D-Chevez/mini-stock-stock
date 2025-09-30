package org.kodigo.mini_stock_sytem.repository;

import org.kodigo.mini_stock_sytem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByEmail(String email);
}
