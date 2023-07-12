package org.mssc.order.msscsodaorderservice.service;

import org.mssc.order.msscsodaorderservice.web.model.CustomerPagedList;
import org.springframework.data.domain.Pageable;

public interface CustomerService {
    CustomerPagedList listCustomers(Pageable pageable);

}
