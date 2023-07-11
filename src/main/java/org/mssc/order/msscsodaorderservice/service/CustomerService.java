package org.mssc.order.msscsodaorderservice.service;

import org.mssc.order.msscsodaorderservice.model.CustomerPagedList;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerPagedList listCustomers(Pageable pageable);

}
