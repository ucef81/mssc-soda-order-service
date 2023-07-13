package org.mssc.order.msscsodaorderservice.web.mapper;

import org.mssc.order.msscsodaorderservice.domain.SodaOrderLine;
import org.mssc.order.msscsodaorderservice.service.soda.SodaDto;
import org.mssc.order.msscsodaorderservice.service.soda.SodaService;
import org.mssc.order.msscsodaorderservice.web.model.SodaOrderLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;

public abstract class SodaOrderLineMapperDecorator implements  SodaOrderLineMapper {


    private SodaService sodaService;
    private SodaOrderLineMapper sodaOrderLineMapper;

    @Autowired
    public void setSodaService(SodaService sodaService) {
        this.sodaService = sodaService;
    }

    @Autowired
    @Qualifier("delegate")
    public void setSodaOrderLineMapper(SodaOrderLineMapper sodaOrderLineMapper) {
        this.sodaOrderLineMapper = sodaOrderLineMapper;
    }

    @Override
    public SodaOrderLineDto sodaOrderLineToDto(SodaOrderLine line) {
        SodaOrderLineDto orderLineDto = sodaOrderLineMapper.sodaOrderLineToDto(line);
        Optional<SodaDto> SodaDtoOptional = sodaService.getSodaByUpc(line.getUpc());

        SodaDtoOptional.ifPresent(SodaDto -> {
            orderLineDto.setSodaName(SodaDto.getSodaName());
            orderLineDto.setSodaStyle(SodaDto.getSodaStyle());
            orderLineDto.setPrice(SodaDto.getPrice());
            orderLineDto.setSodaId(SodaDto.getId());
        });

        return orderLineDto;
    }



}
