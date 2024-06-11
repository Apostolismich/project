package gr.aueb.cf.project.controller;


import gr.aueb.cf.project.common.ApiResponse;
import gr.aueb.cf.project.model.Customer;
import gr.aueb.cf.project.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> body = customerService.customersList();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCustomer(@Valid @RequestBody Customer customer) {
      if (Objects.nonNull(customerService.readCustomer(customer.getCustomerName()))) {
            return new ResponseEntity<>(new ApiResponse(false, "customer already exists"), HttpStatus.CONFLICT);
        }

      customerService.createCustomer(customer);
      return new ResponseEntity<>(new ApiResponse(true, "created the customer"),HttpStatus.CREATED);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<ApiResponse> updateCustomer(@PathVariable("customerId") Integer customerId, @Valid @RequestBody Customer customer) {
        // check if customer exists.
        if (Objects.nonNull(customerService.readCustomer(customerId))) {
            // if the customer exists then update it.
            customerService.updateCustomer(customerId, customer);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true,"updated the customers"), HttpStatus.OK);
        }
        // if the customers doesn't exist then return a response
        return new ResponseEntity<>(new ApiResponse(false,"customers does not exist"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable("customerId") Integer customerId ) {
        if (Objects.nonNull(customerService.readCustomer(customerId))) {
            customerService.deleteById(customerId);
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "customer is deleted"),HttpStatus.OK);
        }

        return new ResponseEntity<>(new ApiResponse(false,"customer not found"), HttpStatus.NOT_FOUND);
    }

}
