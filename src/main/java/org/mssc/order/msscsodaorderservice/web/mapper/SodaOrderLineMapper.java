package org.mssc.order.msscsodaorderservice.web.mapper;


import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;


import org.mapstruct.Mapping;
import org.mssc.order.msscsodaorderservice.domain.SodaOrderLine;
import org.mssc.order.msscsodaorderservice.web.model.SodaOrderLineDto;

@Mapper(uses = {DateMapper.class})
@DecoratedWith(SodaOrderLineMapperDecorator.class)
public interface SodaOrderLineMapper {


    SodaOrderLineDto sodaOrderLineToDto(SodaOrderLine sodaOrderLine);

    SodaOrderLine dtoToSodaOrderLine(SodaOrderLineDto sodaOrderLineDto);

}
