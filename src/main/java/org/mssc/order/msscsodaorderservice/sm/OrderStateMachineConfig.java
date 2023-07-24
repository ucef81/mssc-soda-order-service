package org.mssc.order.msscsodaorderservice.sm;


import lombok.RequiredArgsConstructor;
import org.mssc.order.msscsodaorderservice.domain.OrderEventEnum;
import org.mssc.order.msscsodaorderservice.domain.OrderStatusEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
@RequiredArgsConstructor
public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderStatusEnum, OrderEventEnum> {

    private final Action<OrderStatusEnum, OrderEventEnum> validateOrderAction;
    private final Action<OrderStatusEnum, OrderEventEnum> allocateOrderAction;

    @Override
    public void configure(StateMachineStateConfigurer<OrderStatusEnum, OrderEventEnum> states) throws Exception {
        states.withStates()
                .initial(OrderStatusEnum.NEW)
                .states(EnumSet.allOf(OrderStatusEnum.class))
                .end(OrderStatusEnum.PICKED_UP)
                .end(OrderStatusEnum.DELIVERED)
                .end(OrderStatusEnum.VALIDATION_EXCEPTION)
                .end(OrderStatusEnum.DELIVERY_EXCEPTION)
                .end(OrderStatusEnum.ALLOCATION_EXCEPTION);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatusEnum, OrderEventEnum> transitions) throws Exception {
        transitions.withExternal()
                .source(OrderStatusEnum.NEW).target(OrderStatusEnum.VALIDATION_PENDING)
                .event(OrderEventEnum.VALIDATE_ORDER)
                .action(validateOrderAction)
                .and().withExternal()
                .source(OrderStatusEnum.VALIDATION_PENDING).target(OrderStatusEnum.VALIDATED)
                .event(OrderEventEnum.VALIDATION_PASSED)
                .and().withExternal()
                .source(OrderStatusEnum.VALIDATION_PENDING).target(OrderStatusEnum.VALIDATION_EXCEPTION)
                .event(OrderEventEnum.VALIDATION_FAILED)
                 .and().withExternal()
                .source(OrderStatusEnum.VALIDATED).target(OrderStatusEnum.ALLOCATION_PENDING)
                .event(OrderEventEnum.VALIDATE_ORDER)
                .action(allocateOrderAction);

    }
}
