package org.mssc.order.msscsodaorderservice.mapper;


import org.mapstruct.Mapper;
import org.mssc.order.msscsodaorderservice.domain.Customer;
import org.mssc.order.msscsodaorderservice.model.CustomerDto;

@Mapper(uses= {DateMapper.class})
public interface CustomerMapper{

    public Customer dtoToCustomer(CustomerDto customerDto);
    public CustomerDto customerToDto(Customer customer);
}
