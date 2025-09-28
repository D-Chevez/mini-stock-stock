package org.kodigo.mini_stock_sytem.model;

import jakarta.persistence.*;
import lombok.*;

import org.kodigo.mini_stock_sytem.model.common.BaseAuditable;

@Entity
@Table(name = "categories",
        uniqueConstraints = @UniqueConstraint(name = "ux_categories_name", columnNames = "name"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Category extends BaseAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 120, nullable = false)
    private String name;

    @Column(length = 255)
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;
}