package com.devstack.pos.api;

import com.devstack.pos.dto.request.RequestCustomerDto;
import com.devstack.pos.service.CustomerService;
import com.devstack.pos.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping(value = "/api/v1/customers")
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<StandardResponse> createCustomer(@RequestBody RequestCustomerDto customerDto) {

        return new ResponseEntity<>(
                new StandardResponse(200,"CustomerSaver",customerService.createCustomer(customerDto)), HttpStatus.CREATED
        );


    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StandardResponse> findCustomer(@PathVariable Long id) {
        return  new ResponseEntity<>(
                new StandardResponse( 201,"customer Data",customerService.findCustomer(id)),HttpStatus.OK
        );
    }

    @PutMapping(params = "id")
    public ResponseEntity<StandardResponse> updateCustomer( @RequestParam int id, @RequestBody RequestCustomerDto dto ) throws ClassNotFoundException {

        return  new ResponseEntity<>(
                new StandardResponse( 201,"customer Data",customerService.updateCustomer(dto,id)),HttpStatus.OK
        );
    }

    @DeleteMapping (params = "id") //204
    public ResponseEntity<StandardResponse> deleteCustomer( @RequestParam long id) throws ClassNotFoundException {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(
                new StandardResponse(201,"Delete Customer",null),HttpStatus.NO_CONTENT
        );
    }

    @GetMapping(path = "list",params = {"page","size","searchText"})

    public ResponseEntity<StandardResponse> getAllCustomer(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String searchText ) {

        return new ResponseEntity<>(
                new StandardResponse(200,"Customer list",customerService.searchAllCustomer(page,size,searchText)), HttpStatus.OK
        );
    }


}
