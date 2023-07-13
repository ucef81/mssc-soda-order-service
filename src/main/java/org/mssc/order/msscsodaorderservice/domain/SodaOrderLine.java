package org.mssc.order.msscsodaorderservice.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class SodaOrderLine extends BaseEntity{
    @Builder
    public SodaOrderLine(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate,
                            String upc, UUID sodaId, String sodaName, String sodaStyle ,
                         BigDecimal price, Integer orderQuantity, Integer quantityAllocated) {
        super(id, version, createdDate, lastModifiedDate);
        this.upc = upc;
        this.sodaId = sodaId;
        this.sodaName = sodaName;
        this.sodaStyle = sodaStyle;
        this.orderQuantity = orderQuantity;
        this.price = price;
        this.quantityAllocated = quantityAllocated;
    }

    @ManyToOne
    private SodaOrder SodaOrder;

    private String upc;
    private String sodaName;
    private String sodaStyle;
    private UUID sodaId;
    private Integer orderQuantity = 0;
    private BigDecimal price;
    private Integer quantityAllocated = 0;
}
