package org.mssc.order.msscsodaorderservice.events;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mssc.order.msscsodaorderservice.web.model.SodaOrderDto;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocateOrderResult {
    private SodaOrderDto sodaOrderDto;
    private Boolean allocationError = false;
    private Boolean pendingInventory = false;
}
