package org.mssc.order.msscsodaorderservice.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class SodaOrder extends BaseEntity{

    @Builder
    public SodaOrder(UUID id, Long version, Timestamp createdDate, Timestamp lasModifiedDate, String customerRef, Customer customer
            ,OrderStatusEnum orderStatus, String orderStatusCallbackUrl)
    {
        super(id,version,createdDate,lasModifiedDate);
        this.customerRef = customerRef;
        this.customer = customer;
        this.orderStatus = orderStatus;
        this.orderStatusCallbackUrl = orderStatusCallbackUrl;


    }

    private String customerRef;

    @ManyToOne
    private Customer customer;

    private OrderStatusEnum orderStatus = OrderStatusEnum.NEW;

    private String orderStatusCallbackUrl;

}
