package org.mssc.order.msscsodaorderservice.service;


import lombok.extern.slf4j.Slf4j;
import org.mssc.order.msscsodaorderservice.bootstrap.SodaOrderBootstrap;
import org.mssc.order.msscsodaorderservice.domain.Customer;
import org.mssc.order.msscsodaorderservice.repositories.CustomerRepository;
import org.mssc.order.msscsodaorderservice.repositories.SodaOrderRepository;
import org.mssc.order.msscsodaorderservice.web.model.SodaOrderDto;
import org.mssc.order.msscsodaorderservice.web.model.SodaOrderLineDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
@Slf4j
public class placeTastingRoomOrder {

    private final CustomerRepository customerRepository;
    private final SodaOrderService sodaOrderService;
    private final SodaOrderRepository sodaOrderRepository;
    private final List<String> sodaUpcs = new ArrayList<>(3);


    public placeTastingRoomOrder(CustomerRepository customerRepository, SodaOrderService sodaOrderService, SodaOrderRepository sodaOrderRepository) {
        this.customerRepository = customerRepository;
        this.sodaOrderService = sodaOrderService;
        this.sodaOrderRepository = sodaOrderRepository;


        sodaUpcs.add(SodaOrderBootstrap.SODA_1_UPC);
        sodaUpcs.add(SodaOrderBootstrap.SODA_2_UPC);
        sodaUpcs.add(SodaOrderBootstrap.SODA_3_UPC);
    }


    @Transactional
    @Scheduled(fixedRate = 60000) //run every 2 seconds
    public void placeTastingRoomOrder(){

        List<Customer> customerList = customerRepository.findAllByCustomerNameLike(SodaOrderBootstrap.TASTING_ROOM);

        if (customerList.size() == 1){ //should be just one
            doPlaceOrder(customerList.get(0));
        } else {
            log.error("Too many or too few tasting room customers found");
        }
    }

    private void doPlaceOrder(Customer customer) {
        String sodaToOrder = getRandomBeerUpc();

        SodaOrderLineDto sodaOrderLine = SodaOrderLineDto.builder()
                .upc(sodaToOrder)
                .orderQuantity(new Random().nextInt(6)) //todo externalize value to property
                .build();
        List<SodaOrderLineDto> sodaOrderLineSet = new ArrayList<>();
        sodaOrderLineSet.add(sodaOrderLine);

        SodaOrderDto sodaOrder = SodaOrderDto.builder()
                .customerId(customer.getId())
                .customerRef(UUID.randomUUID().toString())
                .sodaOrderLines(sodaOrderLineSet)
                .build();

        SodaOrderDto savedOrder = sodaOrderService.placeOrder(customer.getId(), sodaOrder);

    }

    private String getRandomBeerUpc() {
        return sodaUpcs.get(new Random().nextInt(sodaUpcs.size() -0));
    }

}
