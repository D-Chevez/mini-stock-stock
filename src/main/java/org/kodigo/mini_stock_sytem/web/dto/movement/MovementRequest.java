package org.kodigo.mini_stock_sytem.web.dto.movement;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.kodigo.mini_stock_sytem.model.MovementType;

/**
 * Payload for create/update movement operations.
 */
public record MovementRequest(
        @NotNull Long productId,
        Long customerId,
        Long supplierId,
        @NotNull MovementType type,
        @NotNull @Positive Integer quantity,
        @PositiveOrZero BigDecimal unitPrice, // optional
        @Size(max = 80) String idempotencyKey // optional but unique if present
) { }
