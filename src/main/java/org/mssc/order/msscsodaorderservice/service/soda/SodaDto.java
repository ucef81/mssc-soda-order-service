package org.mssc.order.msscsodaorderservice.service.soda;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Version;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SodaDto implements Serializable {

    static final long serialVersionUID = -5815566940065181210L;
    @Null
    private UUID id;

    @Version
    private Long version;

    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)

    private OffsetDateTime createdDate;

    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime lastModifiedDate;

    @NotBlank(message = "This field Doesn't have  to be Blank  ")
    @Size(min = 3,max = 10, message = "Size name have to be between 3 and 10 caracters")
    private String sodaName;

    @NotNull
    private String sodaStyle;

    @Positive
    private String upc;


    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Positive
    private BigDecimal price;



    @PositiveOrZero
    private Integer quantityOnHand;


}
