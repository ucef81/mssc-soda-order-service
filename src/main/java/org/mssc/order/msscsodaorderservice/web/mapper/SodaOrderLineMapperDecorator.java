package org.mssc.order.msscsodaorderservice.web.mapper;

import lombok.extern.slf4j.Slf4j;
import org.mssc.order.msscsodaorderservice.domain.SodaOrderLine;
import org.mssc.order.msscsodaorderservice.service.soda.SodaDto;
import org.mssc.order.msscsodaorderservice.service.soda.SodaService;
import org.mssc.order.msscsodaorderservice.web.model.SodaOrderLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.Optional;


@Slf4j
public abstract class SodaOrderLineMapperDecorator implements  SodaOrderLineMapper {



    private SodaService sodaService;
    private SodaOrderLineMapper delegate;


    @Autowired
    public void setSodaService(SodaService sodaService) {
        this.sodaService = sodaService;
    }

    @Autowired
    @Qualifier("delegate")
    public void setDelegate(SodaOrderLineMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public SodaOrderLineDto sodaOrderLineToDto(SodaOrderLine line) {
        SodaOrderLineDto orderLineDto = delegate.sodaOrderLineToDto(line);
        log.info("sodaOrderLineToDtoDecorator upc : " + line.getUpc());

        Optional<SodaDto> sodaDtoOptional = sodaService.getSodaByUpc(line.getUpc());

        sodaDtoOptional.ifPresent(sodaDto -> {
            orderLineDto.setSodaName(sodaDto.getSodaName());
            orderLineDto.setSodaStyle(sodaDto.getSodaStyle());
            orderLineDto.setPrice(sodaDto.getPrice());
            orderLineDto.setSodaId(sodaDto.getId());
        });
        log.info("sodaOrderLineToDtoDecorator : " + orderLineDto.getUpc() + " : " + orderLineDto.getSodaName() );

        return orderLineDto;
    }

    @Override
    public SodaOrderLine dtoToSodaOrderLine(SodaOrderLineDto dto){

        log.info("dtoDecorator : " + dto.getUpc() + " : " + dto.getSodaName() );

        return delegate.dtoToSodaOrderLine(dto);
     }



}
