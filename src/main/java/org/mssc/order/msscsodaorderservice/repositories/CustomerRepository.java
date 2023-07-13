package org.mssc.order.msscsodaorderservice.repositories;

import org.mssc.order.msscsodaorderservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID>, JpaSpecificationExecutor<Customer> {
    List<Customer> findAllByCustomerNameLike(String customerName);
}
