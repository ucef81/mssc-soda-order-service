package org.mssc.order.msscsodaorderservice.domain;

public enum OrderStatusEnum {
    NEW, VALIDATED, VALIDATION_PENDING, VALIDATION_EXCEPTION,
    ALLOCATION_PENDING, ALLOCATED, ALLOCATION_EXCEPTION, CANCELLED,
    PENDING_INVENTORY, PICKED_UP, DELIVERED, DELIVERY_EXCEPTION
}
