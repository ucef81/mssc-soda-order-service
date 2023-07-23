package org.mssc.order.msscsodaorderservice.service.soda;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;



@Slf4j
@ConfigurationProperties(prefix = "org.grosserie", ignoreUnknownFields = false)
@Component
public class SodaServiceImpl implements SodaService {


    private final  String SODABYID_PATH = "/api/v1/soda/";
    private final  String SODABYUPC_PATH = "/api/v1/sodaUpc/";


    @Autowired
    private  RestTemplate restTemplate;

    private String sodaServiceHost;

    public void setSodaServiceHost(String sodaServiceHost) {
        this.sodaServiceHost = sodaServiceHost;
    }

    @Override
    public Optional<SodaDto> getSodaById(UUID uuid) {
        log.debug("Calling Soda By Id Service");
        return Optional.of(restTemplate.getForObject(sodaServiceHost + SODABYID_PATH + uuid.toString(),SodaDto.class));

    }

    @Override
    public Optional<SodaDto> getSodaByUpc(String upc) {
        log.debug("Calling Soda By Upc Service");
        return Optional.of(restTemplate.getForObject(sodaServiceHost + SODABYUPC_PATH + upc,SodaDto.class));
    }
}
