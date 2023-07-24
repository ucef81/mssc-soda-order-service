package org.mssc.order.msscsodaorderservice.sm.actions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mssc.order.msscsodaorderservice.config.JmsConfig;
import org.mssc.order.msscsodaorderservice.domain.OrderEventEnum;
import org.mssc.order.msscsodaorderservice.domain.OrderStatusEnum;
import org.mssc.order.msscsodaorderservice.domain.SodaOrder;
import org.mssc.order.msscsodaorderservice.events.AllocateOrderRequest;
import org.mssc.order.msscsodaorderservice.repositories.SodaOrderRepository;
import org.mssc.order.msscsodaorderservice.service.SodaOrderManagerImpl;
import org.mssc.order.msscsodaorderservice.web.mapper.SodaOrderMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;


@Slf4j
@Component
@RequiredArgsConstructor
public class AllocateOrderAction implements Action<OrderStatusEnum, OrderEventEnum> {

    private final JmsTemplate jmsTemplate;
    private final SodaOrderRepository sodaOrderRepository;
    private final SodaOrderMapper sodaOrderMapper;

    @Override
    public void execute(StateContext<OrderStatusEnum, OrderEventEnum> context) {
        String beerOrderId = (String) context.getMessage().getHeaders().get(SodaOrderManagerImpl.ORDER_ID_HEADER);
        Optional<SodaOrder> beerOrderOptional = sodaOrderRepository.findById(UUID.fromString(beerOrderId));

        beerOrderOptional.ifPresentOrElse(beerOrder -> {
            jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_QUEUE,
                    AllocateOrderRequest.builder()
                            .sodaOrderDto(sodaOrderMapper.sodaOrderToDto(beerOrder))
                            .build());
            log.debug("Sent Allocation Request for order id: " + beerOrderId);
        }, () -> log.error("Beer Order Not Found!"));
    }
}
