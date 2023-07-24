package org.mssc.order.msscsodaorderservice.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mssc.order.msscsodaorderservice.domain.Customer;
import org.mssc.order.msscsodaorderservice.domain.OrderStatusEnum;
import org.mssc.order.msscsodaorderservice.domain.SodaOrder;
import org.mssc.order.msscsodaorderservice.domain.SodaOrderLine;
import org.mssc.order.msscsodaorderservice.repositories.CustomerRepository;
import org.mssc.order.msscsodaorderservice.repositories.SodaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SodaOrderManagerImplTest {

    @Autowired
    SodaOrderRepository sodaOrderRepository;

    @Autowired
    SodaOrderManager sodaOrderManager;

    @Autowired
    CustomerRepository customerRepository;

    Customer testCustomer;

    UUID sodaId = UUID.randomUUID();

    @BeforeEach
    void  setUp(){
        testCustomer = customerRepository.save(Customer.builder().customerName("Test Customer").build());
    }

    @Test
    void testNewAllocated(){

        SodaOrder sodaOrder = createSodaOrder();

        SodaOrder savedSodaOrder = sodaOrderManager.newSodaOrder(sodaOrder);

        assertNotNull(savedSodaOrder);
        assertEquals(OrderStatusEnum.ALLOCATED, savedSodaOrder.getOrderStatus());
    }
    public SodaOrder createSodaOrder(){
        SodaOrder sodaOrder = SodaOrder.builder().customer(testCustomer).build();

        Set<SodaOrderLine> lines = new HashSet<>();
        lines.add(SodaOrderLine.builder().sodaId(sodaId).orderQuantity(1).sodaOrder(sodaOrder).build());
        sodaOrder.setSodaOrderLines(lines);
        return sodaOrder;
    }
}
