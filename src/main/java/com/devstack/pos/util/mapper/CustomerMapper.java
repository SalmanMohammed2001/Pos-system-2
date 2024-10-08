package com.devstack.pos.util.mapper;


import com.devstack.pos.dto.core.CustomerDto;
import com.devstack.pos.dto.response.ResponseCustomerDto;
import com.devstack.pos.entity.Customer;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer toCustomer(CustomerDto customerDto);
    ResponseCustomerDto toResponseCustomerDto(Customer customer);
    List<ResponseCustomerDto> toResponseCustomerDtoList(Page<Customer> page);
}
