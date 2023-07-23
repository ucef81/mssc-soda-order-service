package org.mssc.order.msscsodaorderservice.service;

import lombok.RequiredArgsConstructor;
import org.mssc.order.msscsodaorderservice.domain.OrderEventEnum;
import org.mssc.order.msscsodaorderservice.domain.OrderStatusEnum;
import org.mssc.order.msscsodaorderservice.domain.SodaOrder;
import org.mssc.order.msscsodaorderservice.repositories.SodaOrderRepository;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class SodaOrderManagerImpl implements SodaOrderManager {

    private final StateMachineFactory<OrderStatusEnum, OrderEventEnum> stateMachineFactory;
    private final SodaOrderRepository sodaOrderRepository;
    public static final String ORDER_ID_HEADER = "soda_order_id";

    @Override
    public SodaOrder newSodaOrder(SodaOrder sodaOrder) {
        sodaOrder.setId(null);
        sodaOrder.setOrderStatus(OrderStatusEnum.NEW);
        sendEvent(sodaOrder,OrderEventEnum.VALIDATE_ORDER);
        return sodaOrderRepository.save(sodaOrder);
    }

    @Override
    public void processValidationResult(UUID sodaOrderId, Boolean isValid) {
        SodaOrder sodaOrder = sodaOrderRepository.findById(sodaOrderId).get();

        if(isValid){
            sendEvent(sodaOrder, OrderEventEnum.VALIDATION_PASSED);
        }else{
            sendEvent(sodaOrder, OrderEventEnum.ALLOCATION_FAILED);
        }
    }

    private void sendEvent(SodaOrder sodaOrder,  OrderEventEnum event){
        StateMachine<OrderStatusEnum, OrderEventEnum> sm = build(sodaOrder);
        Message msg = MessageBuilder.withPayload(event)
                .setHeader(ORDER_ID_HEADER, sodaOrder.getId().toString())
                .build();

        sm.sendEvent(msg);
    }
    private StateMachine<OrderStatusEnum, OrderEventEnum> build(SodaOrder sodaOrder){

        StateMachine<OrderStatusEnum, OrderEventEnum> sm = stateMachineFactory.getStateMachine(sodaOrder.getId());
        sm.stop();
        sm.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.resetStateMachine(new DefaultStateMachineContext<>(sodaOrder.getOrderStatus(),null,null,null));
                });
        sm.start();
        return sm;
    }
}
