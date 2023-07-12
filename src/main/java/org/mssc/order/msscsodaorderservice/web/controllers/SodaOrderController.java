package org.mssc.order.msscsodaorderservice.web.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mssc.order.msscsodaorderservice.service.SodaOrderService;
import org.mssc.order.msscsodaorderservice.web.model.SodaOrderDto;
import org.mssc.order.msscsodaorderservice.web.model.SodaOrderPagedList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Slf4j
@RequestMapping("/api/v1/customers/{customerId}/")
@RestController
@RequiredArgsConstructor
public class SodaOrderController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final SodaOrderService  sodaOrderService;

    @GetMapping("orders")
    public SodaOrderPagedList listOrders(@PathVariable("customerId") UUID customerId,
                                         @RequestParam(value = "numberPage" , required = false) Integer pageNumber,
                                         @RequestParam(value = "pageSize", required = false) Integer pageSize){

        if (pageNumber == null || pageNumber  < 0)
        {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize == null || pageSize < 0){
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return sodaOrderService.listOrders(customerId, PageRequest.of(pageNumber, pageSize));
    }

    @PostMapping("orders")
    @ResponseStatus(HttpStatus.CREATED)
    public SodaOrderDto placeOrder(@PathVariable("customerId") UUID customerId, @RequestBody SodaOrderDto sodaOrderDto){

        return sodaOrderService.PlaceOrder(customerId, sodaOrderDto);
    }

    @GetMapping("orders/{orderId}")
    public SodaOrderDto getOrder(@PathVariable("customerId") UUID customerId,@PathVariable("orderId") UUID orderId){

        return sodaOrderService.getOrderById(customerId, orderId);

    }

    @PutMapping("orders/{orderId}/backup")
    public void pickup(@PathVariable("customerId") UUID customerId,@PathVariable("orderId") UUID orderId)
    {
        sodaOrderService.pickUpOrder(customerId, orderId);
    }
}
