package org.mssc.order.msscsodaorderservice.web.mapper;


import org.mapstruct.Mapper;
import org.mssc.order.msscsodaorderservice.domain.SodaOrder;
import org.mssc.order.msscsodaorderservice.domain.SodaOrderLine;
import org.mssc.order.msscsodaorderservice.web.model.SodaOrderDto;
import org.mssc.order.msscsodaorderservice.web.model.SodaOrderLineDto;

@Mapper(uses = {DateMapper.class})
public interface SodaOrderLineMapper {
    SodaOrderDto SodaOrderLineToDto(SodaOrderLine sodaOrderLine);
    SodaOrder dtoToSodaOrderLine(SodaOrderLineDto sodaOrderLineDto);
}
