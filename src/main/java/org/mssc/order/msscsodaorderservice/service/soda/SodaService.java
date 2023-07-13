package org.mssc.order.msscsodaorderservice.service.soda;

import java.util.Optional;
import java.util.UUID;

public interface SodaService {



        Optional<SodaDto> getSodaById(UUID  uuid);

        Optional<SodaDto> getSodaByUpc(String upc);

}
