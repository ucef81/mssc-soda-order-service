package org.mssc.order.msscsodaorderservice.bootstrap;

import lombok.RequiredArgsConstructor;
import org.mssc.order.msscsodaorderservice.domain.Customer;
import org.mssc.order.msscsodaorderservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class SodaOrderBootstrap implements CommandLineRunner {
    public static final String TASTING_ROOM = "Tasting Room";
    public static final String SODA_1_UPC = "0631234200036";
    public static final String SODA_2_UPC = "0631234300019";
    public static final String SODA_3_UPC = "0083783375213";

    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCustomerData();
    }

    private void loadCustomerData() {
        if (customerRepository.count() == 0) {
            customerRepository.save(Customer.builder()
                    .customerName(TASTING_ROOM)
                    .apiKey(UUID.randomUUID())
                    .build());
        }
    }
}
