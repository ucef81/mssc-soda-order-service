package org.mssc.order.msscbeerorderservice.domain;


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
@NoArgsConstructor
@Entity
public class SodaOrderLine extends BaseEntity{
    @Builder
    public SodaOrderLine(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
                         SodaOrder sodaOrder, UUID SodaId, Integer orderQuantity,
                         Integer quantityAllocated) {
        super(id, version, createdDate, lastModifiedDate);
        this.SodaOrder = sodaOrder;
        this.SodaId = SodaId;
        this.orderQuantity = orderQuantity;
        this.quantityAllocated = quantityAllocated;
    }

    @ManyToOne
    private SodaOrder SodaOrder;

    private UUID SodaId;
    private Integer orderQuantity = 0;
    private Integer quantityAllocated = 0;
}
