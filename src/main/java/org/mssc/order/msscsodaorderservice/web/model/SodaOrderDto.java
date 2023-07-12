package org.mssc.order.msscsodaorderservice.web.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.mssc.order.msscsodaorderservice.domain.OrderStatusEnum;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode (callSuper = true)
public class SodaOrderDto extends BaseItem {

    @Builder
    public SodaOrderDto(UUID id, Integer version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, UUID customerId,
                        List<SodaOrderLineDto> SodaOrderLines,
                        OrderStatusEnum orderStatus, String orderStatusCallbackUrl, String customerRef) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerId = customerId;
        this.SodaOrderLines = SodaOrderLines;
        this.orderStatus = orderStatus;
        this.orderStatusCallbackUrl = orderStatusCallbackUrl;
        this.customerRef = customerRef;
    }

    private UUID customerId;
    private String customerRef;
    private List<SodaOrderLineDto> SodaOrderLines;
    private OrderStatusEnum orderStatus;
    private String orderStatusCallbackUrl;
}
