package org.mssc.order.msscsodaorderservice.repositories;

import org.mssc.order.msscsodaorderservice.domain.SodaOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SodaOrderLineRepository extends JpaRepository<SodaOrderLine, UUID>, JpaSpecificationExecutor<UUID> {
}
