package org.mssc.order.msscsodaorderservice.service.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mssc.order.msscsodaorderservice.config.JmsConfig;
import org.mssc.order.msscsodaorderservice.events.ValidateOrderResult;
import org.mssc.order.msscsodaorderservice.service.SodaOrderManager;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Component
public class ValidationResultListener {
    private final SodaOrderManager sodaOrderManager;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE)
    public void listen(ValidateOrderResult result){
        final UUID beerOrderId = result.getOrderId();

        log.debug("Validation Result for Order Id: " + beerOrderId);

        sodaOrderManager.processValidationResult(beerOrderId, result.getIsValid());
    }
}
