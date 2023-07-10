package org.mssc.order.msscsodaorderservice.mapper;


import org.mapstruct.Mapper;
import org.mssc.order.msscsodaorderservice.domain.SodaOrder;
import org.mssc.order.msscsodaorderservice.domain.SodaOrderLine;
import org.mssc.order.msscsodaorderservice.model.SodaOrderDto;
import org.mssc.order.msscsodaorderservice.model.SodaOrderLineDto;

@Mapper(uses = {DateMapper.class})
public interface SodaOrderLineMapper {
    SodaOrderDto SodaOrderLineToDto(SodaOrderLine sodaOrderLine);
    SodaOrder dtoToSodaOrderLine(SodaOrderLineDto sodaOrderLineDto);
}
