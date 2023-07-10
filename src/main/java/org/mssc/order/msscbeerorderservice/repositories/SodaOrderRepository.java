package org.mssc.order.msscbeerorderservice.repositories;

import org.mssc.order.msscbeerorderservice.domain.SodaOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SodaOrderRepository extends JpaRepository<SodaOrder, UUID> , JpaSpecificationExecutor<SodaOrder> {
}
