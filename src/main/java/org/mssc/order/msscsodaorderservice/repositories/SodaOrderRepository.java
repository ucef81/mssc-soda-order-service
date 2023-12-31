package org.mssc.order.msscsodaorderservice.repositories;

import org.mssc.order.msscsodaorderservice.domain.Customer;
import org.mssc.order.msscsodaorderservice.domain.SodaOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SodaOrderRepository extends JpaRepository<SodaOrder, UUID> , JpaSpecificationExecutor<SodaOrder> {
    Page<SodaOrder> findAllByCustomer(Customer customer, Pageable pageable);



}

