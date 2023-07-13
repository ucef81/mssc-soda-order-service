package org.mssc.order.msscsodaorderservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mssc.order.msscsodaorderservice.domain.Customer;
import org.mssc.order.msscsodaorderservice.domain.OrderStatusEnum;
import org.mssc.order.msscsodaorderservice.domain.SodaOrder;
import org.mssc.order.msscsodaorderservice.web.mapper.SodaOrderMapper;
import org.mssc.order.msscsodaorderservice.web.model.SodaOrderDto;
import org.mssc.order.msscsodaorderservice.web.model.SodaOrderPagedList;
import org.mssc.order.msscsodaorderservice.repositories.CustomerRepository;
import org.mssc.order.msscsodaorderservice.repositories.SodaOrderRepository;
import org.mssc.order.msscsodaorderservice.specification.SodaOrderSpecification;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SodaOrderServiceImpl implements SodaOrderService {

    private final SodaOrderRepository sodaOrderRepository;
    private final CustomerRepository customerRepository;
    private final SodaOrderMapper sodaOrderMapper;

    @Override
    public SodaOrderPagedList listOrders(UUID customerId, Pageable pageable) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()){

            Page<SodaOrder> sodaOrderPage = sodaOrderRepository.findAll(pageable);
            return new SodaOrderPagedList(sodaOrderPage.stream()
                    .map(sodaOrderMapper::sodaOrderToDto)
                    .collect(Collectors.toList()),
                    PageRequest.of(
                            sodaOrderPage.getPageable().getPageNumber(),
                            sodaOrderPage.getPageable().getPageSize()
                    ),
                    sodaOrderPage.getTotalElements());
        }
        else {
            return null;
        }
    }

    @Transactional
    @Override
    public SodaOrderDto placeOrder(UUID customerId, SodaOrderDto sodaOrderDto) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent())
        {
            SodaOrder sodaOrder = sodaOrderMapper.dtoToSodaOrder(sodaOrderDto);
            sodaOrder.setId(null);
            sodaOrder.setCustomer(customer.get());
            sodaOrder.setOrderStatus(OrderStatusEnum.NEW);
            SodaOrder sodaSaved = sodaOrderRepository.saveAndFlush(sodaOrder);
            log.debug("Saved Beer Order: " + sodaSaved.getId());
            return sodaOrderMapper.sodaOrderToDto(sodaSaved);
        }
        else{
            throw new RuntimeException("Customer Not Found");
        }

    }

    @Override
    public SodaOrderDto getOrderById(UUID customerId, UUID orderId) {

        return sodaOrderMapper.sodaOrderToDto(getOrder(customerId, orderId));
    }

    @Override
    public void pickUpOrder(UUID customerId, UUID orderId) {
        SodaOrder sodaOrder = getOrder(customerId, orderId);
        sodaOrder.setOrderStatus(OrderStatusEnum.PICKED_UP);
        sodaOrderRepository.save(sodaOrder);
    }


    private SodaOrder getOrder(UUID customerId, UUID orderId)
    {
        Specification<SodaOrder> specification =  new SodaOrderSpecification();

        specification = specification.and((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("customerId"), customerId));

        specification = specification.and((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("id"), orderId));
        return  sodaOrderRepository.findOne(specification)
                .orElseThrow(() -> new RuntimeException("Soda Order not found"));
    }
}
