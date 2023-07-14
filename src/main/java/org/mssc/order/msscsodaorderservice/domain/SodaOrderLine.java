package org.mssc.order.msscsodaorderservice.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
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
                            String upc, UUID sodaId, SodaOrder sodaOrder,
                         Integer orderQuantity, Integer quantityAllocated) {
        super(id, version, createdDate, lastModifiedDate);
        this.upc = upc;
        this.sodaId = sodaId;
        this.orderQuantity = orderQuantity;
        this.sodaOrder = sodaOrder;
        this.quantityAllocated = quantityAllocated;
    }

    @ManyToOne
    private SodaOrder sodaOrder;

    private String upc;


    @Column(columnDefinition = "varchar(36)")
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID sodaId;

    private Integer orderQuantity = 0;

    private Integer quantityAllocated = 0;
}
