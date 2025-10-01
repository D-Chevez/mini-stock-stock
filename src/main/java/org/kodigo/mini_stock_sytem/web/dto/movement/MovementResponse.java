package org.kodigo.mini_stock_sytem.web.dto.movement;

import java.math.BigDecimal;
import java.time.Instant;

import org.kodigo.mini_stock_sytem.model.Movement;
import org.kodigo.mini_stock_sytem.model.MovementType;

/** API-friendly view of Movement. */
public record MovementResponse(
        Long id,
        Long productId,
        String productSku,
        String productName,
        Long customerId,
        String customerName,
        Long supplierId,
        String supplierName,
        MovementType type,
        Integer quantity,
        BigDecimal unitPrice,
        String idempotencyKey,
        Instant createdAt,
        String createdBy
) {
    public static MovementResponse fromEntity(Movement entity) {
        return new MovementResponse(
                entity.getId(),
                entity.getProduct() != null ? entity.getProduct().getId() : null,
                entity.getProduct() != null ? entity.getProduct().getSku() : null,
                entity.getProduct() != null ? entity.getProduct().getName() : null,
                entity.getCustomer() != null ? entity.getCustomer().getId() : null,
                entity.getCustomer() != null ? entity.getCustomer().getName() : null,
                entity.getSupplier() != null ? entity.getSupplier().getId() : null,
                entity.getSupplier() != null ? entity.getSupplier().getName() : null,
                entity.getType(),
                entity.getQty(),
                entity.getProduct() != null ? entity.getProduct().getPrice(): null,
                entity.getIdempotencyKey(),
                entity.getCreatedAt(),
                entity.getCreatedBy()
        );
    }
}