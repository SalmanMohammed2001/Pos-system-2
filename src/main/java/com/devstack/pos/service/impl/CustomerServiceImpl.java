package com.devstack.pos.service.impl;

import com.devstack.pos.dto.core.CustomerDto;
import com.devstack.pos.dto.request.RequestCustomerDto;
import com.devstack.pos.dto.response.ResponseCustomerDto;
import com.devstack.pos.dto.response.paginated.model.CustomerPaginatedDto;
import com.devstack.pos.entity.Customer;

import com.devstack.pos.exception.EntryNotFoundException;
import com.devstack.pos.repo.CustomerRepo;
import com.devstack.pos.service.CustomerService;
import com.devstack.pos.util.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;


    private final CustomerMapper customerMapper;


    public CustomerServiceImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public ResponseCustomerDto createCustomer(RequestCustomerDto dto) {

        CustomerDto customerDto = new CustomerDto(
                0,
                new Random().nextInt(10001),
                dto.getName(),
                dto.getAddress(),
                dto.getSalary(),
                true,
                null,
                null,
                null,
                null
        );


   /*     Customer customer = new Customer(
                0,
                new Random().nextInt(1000),
                customerDto.getName(),
                customerDto.getAddress(),
                customerDto.getSalary(),
                customerDto.isActiveState(),
                null);
        */



        customerRepo.save(customerMapper.toCustomer(customerDto));
        return new ResponseCustomerDto(
                customerDto.getPublicId(),
                customerDto.getName(),
                customerDto.getAddress(),
                customerDto.getSalary(),
                customerDto.isActiveState()
        );
    }

    @Override
    public ResponseCustomerDto findCustomer(Long id)  {
        Optional<Customer> selectedCustomer= customerRepo.findByPublicId(id);
        if(selectedCustomer.isPresent()){
            return customerMapper.toResponseCustomerDto(selectedCustomer.get());
            /*return new ResponseCustomerDto(
                  *//*  selectedCustomer.get().getPublicId(),
                    selectedCustomer.get().getName(),
                    selectedCustomer.get().getAddress(),
                    selectedCustomer.get().getSalary(),
                    selectedCustomer.get().isActiveState()*//*

            );*/
        }
       throw new EntryNotFoundException("Not Found");
    }

    @Override
    public ResponseCustomerDto updateCustomer(RequestCustomerDto dto,long id) throws ClassNotFoundException {
        Optional<Customer> selectedCustomer = customerRepo.findByPublicId(id);
        if(selectedCustomer.isEmpty()) throw new ClassNotFoundException();

        selectedCustomer.get().setName(dto.getName());
        selectedCustomer.get().setAddress(dto.getAddress());
        selectedCustomer.get().setSalary(dto.getSalary());

        customerRepo.save(selectedCustomer.get());

            return customerMapper.toResponseCustomerDto(selectedCustomer.get());
      /*  return new ResponseCustomerDto(
                selectedCustomer.get().getPublicId(),
                selectedCustomer.get().getName(),
                selectedCustomer.get().getAddress(),
                selectedCustomer.get().getSalary(),
                selectedCustomer.get().isActiveState()
        );*/


    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepo.deleteByPublicId(id);

    }

    @Override
    public CustomerPaginatedDto searchAllCustomer(int page, int size, String searchText) {
     /*   Page<Customer> customers = customerRepo.findAll(PageRequest.of(page,size));*/
        Page<Customer> customers = customerRepo.searchAllByAddressOrName(searchText,PageRequest.of(page,size));
     //   ArrayList<ResponseCustomerDto> list = new ArrayList<>();
        List<ResponseCustomerDto> list = customerMapper.toResponseCustomerDtoList(customers);


//        long recordCount=customerRepo.count();
        long recordCount=customerRepo.countDataWithSearchText(searchText);
       /* for(Customer c:customers){
            list.add(new ResponseCustomerDto(
                    c.getPublicId(),
                    c.getName(),
                    c.getAddress(),
                    c.getSalary(),
                    c.isActiveState()
            ));
        }*/
        return new CustomerPaginatedDto(recordCount,list);

    }
}
