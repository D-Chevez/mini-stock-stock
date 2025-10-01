package org.kodigo.mini_stock_sytem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "movements",
        indexes = {
                @Index(name = "ix_movements_product", columnList = "product_id"),
                @Index(name = "ix_movements_customer", columnList = "customer_id"),
                @Index(name = "ix_movements_supplier", columnList = "supplier_id"),
                @Index(name = "ix_movements_type", columnList = "type")
        },
        uniqueConstraints = @UniqueConstraint(name = "ux_movements_idempotency", columnNames = "idempotency_key"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_mov_product"))
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(length = 16, nullable = false)
    private MovementType type;

    @Column(nullable = false)
    private Integer qty; // positive for IN, negative for OUT/ADJUST if you prefer; or enforce sign at service layer.

    @Column(length = 255)
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id",
            foreignKey = @ForeignKey(name = "fk_mov_customer"))
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id",
            foreignKey = @ForeignKey(name = "fk_mov_supplier"))
    private Supplier supplier;

    /** JSON payload serialized as text (portable for H2). */
    @Lob
    @Column(name = "meta", columnDefinition = "LONGTEXT")
    private String meta;

    @Column(name = "idempotency_key", length = 80)
    private String idempotencyKey;

    @Column(name = "created_by", length = 80)
    private String createdBy;

    @Column(name = "created_at", nullable = false)
    private java.time.Instant createdAt;

    @PrePersist
    void onCreate() {
        if (createdAt == null) createdAt = java.time.Instant.now();
    }
}