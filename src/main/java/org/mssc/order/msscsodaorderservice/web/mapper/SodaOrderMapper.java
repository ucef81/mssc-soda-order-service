package org.mssc.order.msscsodaorderservice.web.mapper;


import org.mapstruct.Mapper;
import org.mssc.order.msscsodaorderservice.domain.SodaOrder;
import org.mssc.order.msscsodaorderservice.web.model.SodaOrderDto;

@Mapper(uses={DateMapper.class})
public interface SodaOrderMapper {

    SodaOrderDto sodaOrderToDto(SodaOrder sodaOrder);
    SodaOrder dtoToSodaOrder(SodaOrderDto sodaOrderDto);
}
