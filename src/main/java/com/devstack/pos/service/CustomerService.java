package com.devstack.pos.service;

import com.devstack.pos.dto.core.CustomerDto;
import com.devstack.pos.dto.request.RequestCustomerDto;
import com.devstack.pos.dto.response.ResponseCustomerDto;
import com.devstack.pos.dto.response.paginated.model.CustomerPaginatedDto;

public interface CustomerService {
        public ResponseCustomerDto createCustomer(RequestCustomerDto dto);
        public ResponseCustomerDto findCustomer(Long id);
        public ResponseCustomerDto updateCustomer(RequestCustomerDto dto,long id) throws ClassNotFoundException;
        public void deleteCustomer(Long id);

        public CustomerPaginatedDto searchAllCustomer(int page,int size,String searchText);
}
