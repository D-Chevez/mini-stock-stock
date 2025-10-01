package org.kodigo.mini_stock_sytem.model;

import jakarta.persistence.*;
import lombok.*;

import org.kodigo.mini_stock_sytem.model.common.BaseAuditable;

import java.math.BigDecimal;

@Entity
@Table(name = "products",
        indexes = {
                @Index(name = "ix_products_category", columnList = "category_id")
        },
        uniqueConstraints = @UniqueConstraint(name = "ux_products_sku", columnNames = "sku"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product extends BaseAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 64, nullable = false)
    private String sku;

    @Column(length = 160, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_products_category"))
    private Category category;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "min_stock", nullable = false)
    private Integer minStock = 0;

    @Column(nullable = false)
    private Integer stock = 0;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;
}