package org.mssc.order.msscsodaorderservice.sm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mssc.order.msscsodaorderservice.domain.OrderEventEnum;
import org.mssc.order.msscsodaorderservice.domain.OrderStatusEnum;
import org.mssc.order.msscsodaorderservice.repositories.SodaOrderRepository;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BeerOrderStateChangeInterceptor extends StateMachineInterceptorAdapter<OrderStatusEnum, OrderEventEnum> {

    private final SodaOrderRepository sodaOrderRepository;

    /*

    @Override
    public void preStateChange(State<OrderStatusEnum, OrderEventEnum> state,
                               Message<OrderEventEnum> message,
                               Transition<OrderStatusEnum, OrderEventEnum> transition,
                               StateMachine<OrderStatusEnum, OrderEventEnum> stateMachine) {
        log.debug("Pre-State Change");
        Optional.ofNullable(message)
                .flatMap(msg -> Optional.ofNullable((String) msg.getHeaders().getOrDefault(SodaOrderManagerImpl.ORDER_ID_HEADER, " ")))
                .ifPresent(orderId -> {
                    log.debug("Saving state for order id: " + orderId + " Status: " + state.getId());

                    SodaOrder beerOrder = sodaOrderRepository.getOne(UUID.fromString(orderId));
                    beerOrder.setOrderStatus(state.getId());
                    sodaOrderRepository.saveAndFlush(beerOrder);
                });

    }*/
}
