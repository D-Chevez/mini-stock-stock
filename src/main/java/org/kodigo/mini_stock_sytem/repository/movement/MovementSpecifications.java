package org.kodigo.mini_stock_sytem.repository.movement;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;
import org.kodigo.mini_stock_sytem.model.Movement;
import org.kodigo.mini_stock_sytem.model.MovementType;
import org.springframework.data.jpa.domain.Specification;

public final class MovementSpecifications {
    private MovementSpecifications() {}

    public static Specification<Movement> productId(Long productId) {
        return (root, q, cb) ->
                productId == null ? null : cb.equal(root.get("product").get("id"), productId);
    }

    public static Specification<Movement> customerId(Long customerId) {
        return (root, q, cb) ->
                customerId == null ? null : cb.equal(root.get("customer").get("id"), customerId);
    }

    public static Specification<Movement> supplierId(Long supplierId) {
        return (root, q, cb) ->
                supplierId == null ? null : cb.equal(root.get("supplier").get("id"), supplierId);
    }

    public static Specification<Movement> type(MovementType type) {
        return (root, q, cb) -> type == null ? null : cb.equal(root.get("type"), type);
    }

    public static Specification<Movement> createdFrom(Instant from) {
        return (root, q, cb) -> from == null ? null : cb.greaterThanOrEqualTo(root.get("createdAt"), from);
    }

    public static Specification<Movement> createdTo(Instant to) {
        return (root, q, cb) -> to == null ? null : cb.lessThan(root.get("createdAt"), to);
    }

    public static Specification<Movement> withFilters(
            Long productId,
            Long customerId,
            Long supplierId,
            MovementType type,
            LocalDate dateFrom,
            LocalDate dateTo
    ) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (productId != null) {
                predicates.add(cb.equal(root.get("product").get("id"), productId));
            }
            if (customerId != null) {
                predicates.add(cb.equal(root.get("customer").get("id"), customerId));
            }
            if (supplierId != null) {
                predicates.add(cb.equal(root.get("supplier").get("id"), supplierId));
            }
            if (type != null) {
                predicates.add(cb.equal(root.get("type"), type));
            }
            if (dateFrom != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), dateFrom));
            }
            if (dateTo != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), dateTo));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
