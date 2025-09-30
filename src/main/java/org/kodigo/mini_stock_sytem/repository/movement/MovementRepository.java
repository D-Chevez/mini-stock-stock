package org.kodigo.mini_stock_sytem.repository.movement;

import java.util.Optional;
import org.kodigo.mini_stock_sytem.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementRepository extends JpaRepository<Movement, Long>, JpaSpecificationExecutor<Movement> {

    boolean existsByIdempotencyKey(String idempotencyKey);

    Optional<Movement> findByIdempotencyKey(String idempotencyKey);
}