package org.mssc.order.msscsodaorderservice.service;

import org.mssc.order.msscsodaorderservice.model.SodaOrderDto;
import org.mssc.order.msscsodaorderservice.model.SodaOrderPagedList;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface SodaOrderService {

    SodaOrderPagedList listOrders(UUID customerId, Pageable pageable);
    SodaOrderDto PlaceOrder(UUID customerId, SodaOrderDto sodaOrderDto);
    SodaOrderDto getOrderById(UUID customerId,UUID orderId);
    void pickUpeOrder(UUID customerId, UUID orderId);

}
