package org.mssc.order.msscsodaorderservice.sm.actions;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mssc.order.msscsodaorderservice.config.JmsConfig;
import org.mssc.order.msscsodaorderservice.domain.OrderEventEnum;
import org.mssc.order.msscsodaorderservice.domain.OrderStatusEnum;
import org.mssc.order.msscsodaorderservice.domain.SodaOrder;
import org.mssc.order.msscsodaorderservice.repositories.SodaOrderRepository;
import org.mssc.order.msscsodaorderservice.service.SodaOrderManager;
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
public class ValidateOrderAction implements Action<OrderStatusEnum, OrderEventEnum> {

    private final SodaOrderRepository sodaOrderRepository;
    private final JmsTemplate jmsTemplate;
    private SodaOrderMapper sodaOrderMapper;
    @Override
    public void execute(StateContext<OrderStatusEnum, OrderEventEnum> stateContext) {
        String orderId  = (String)stateContext.getMessage().getHeaders()
                .get(SodaOrderManagerImpl.ORDER_ID_HEADER);

        Optional<SodaOrder> sodaOrderOptional = sodaOrderRepository.findById(UUID.fromString(orderId));

        sodaOrderOptional.ifPresentOrElse(sodaOrder -> {
            jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_QUEUE,
                    sodaOrderMapper.sodaOrderToDto(sodaOrder));
        },() -> {
            log.error("Order Not Found. Id: " + orderId);
        });
        log.debug("Sent Validation request to queue for order id " + orderId);
    }
}
