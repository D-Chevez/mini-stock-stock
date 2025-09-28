package org.kodigo.mini_stock_sytem.model;


import jakarta.persistence.*;
import lombok.*;

import org.kodigo.mini_stock_sytem.model.common.BaseAuditable;

@Entity
@Table(name = "suppliers",
        uniqueConstraints = @UniqueConstraint(name = "ux_suppliers_email", columnNames = "email"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Supplier extends BaseAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 160, nullable = false)
    private String name;

    @Column(length = 160)
    private String email;

    @Column(length = 40)
    private String phone;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;
}