package org.mssc.order.msscsodaorderservice.web.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mssc.order.msscsodaorderservice.domain.SodaOrder;
import org.mssc.order.msscsodaorderservice.web.model.SodaOrderDto;

@Mapper(uses={DateMapper.class, SodaOrderLineMapper.class})
public interface SodaOrderMapper {

    @Mapping(target = "customerId", source = "customer.id")
    SodaOrderDto sodaOrderToDto(SodaOrder sodaOrder);

    @Mapping(target = "customer.id", source = "customerId")
    SodaOrder dtoToSodaOrder(SodaOrderDto sodaOrderDto);
}
