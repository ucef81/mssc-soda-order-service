package org.mssc.order.msscsodaorderservice.service;

import org.mssc.order.msscsodaorderservice.web.model.SodaOrderDto;
import org.mssc.order.msscsodaorderservice.web.model.SodaOrderPagedList;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SodaOrderService {

    SodaOrderPagedList listOrders(UUID customerId, Pageable pageable);
    SodaOrderDto PlaceOrder(UUID customerId, SodaOrderDto sodaOrderDto);
    SodaOrderDto getOrderById(UUID customerId,UUID orderId);
    void pickUpOrder(UUID customerId, UUID orderId);

}
