package org.mssc.order.msscsodaorderservice.service;

import org.mssc.order.msscsodaorderservice.domain.SodaOrder;

import java.util.UUID;

public interface SodaOrderManager {

    SodaOrder newSodaOrder(SodaOrder sodaOrder);
    void processValidationResult(UUID beerOrderId, Boolean isValid);

}
