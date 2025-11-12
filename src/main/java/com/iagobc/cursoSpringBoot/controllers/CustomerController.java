package com.iagobc.cursoSpringBoot.controllers;

import com.iagobc.cursoSpringBoot.domain.Customer;
import com.iagobc.cursoSpringBoot.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping ("/customers")
public class CustomerController {

    @Autowired
    @Lazy
    private CustomersService customersService;


//    @RequestMapping (method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers () {
        List<Customer> customers = customersService.getCustomers();

        return ResponseEntity.ok(customers);
    }


//    @RequestMapping (value = "/{username}", method = RequestMethod.GET)
    @GetMapping ("/{username}")
    public ResponseEntity<?> getSingleCustomer (@PathVariable String username) {
       Customer customer = customersService.getSingleCustomer(username);

        if (customer != null) {
            // Response 200
            return ResponseEntity.ok(customer);
        }
        else{
            // Response 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The customer with username " + username +
                    " doesn't exist.");
        }
    }


    @PostMapping
    public ResponseEntity<?> newCustomer (@RequestBody Customer customer) {
        URI location = customersService.newCustomer(customer);

        // Response 201
        return ResponseEntity.created(location).body("Customer " + customer.getName() + " was created.");
    }


    @PutMapping
    public ResponseEntity<?> updateCustomer (@RequestBody Customer customer) {
        boolean updated = customersService.updateCustomer(customer);

        if (updated) {
            // Response 200
            return ResponseEntity.ok("The customer with ID " + customer.getId() + " was updated.");
        }
        else {
            // Response 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The customer with ID " + customer.getId() +
                    " doesn't exist.");
        }
    }


    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteCustomer (@PathVariable int id) {
        boolean deleted = customersService.deleteCustomer(id);

        if (deleted) {
            // Response 200
            return ResponseEntity.ok("The customer with ID  " + id + " was removed.");
        }
        else {
            // Response 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The customer with ID " + id + " doesn't exist.");
        }
    }


    @PatchMapping
    public ResponseEntity<?> patchCustomer (@RequestBody Customer customer) {
        boolean patched = customersService.patchCustomer(customer);

       if (patched) {
           // Response 200
            return ResponseEntity.ok("The customer with ID " + customer.getId() + " was updated.");
       }
       else {
           // Response 404
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The customer with ID " + customer.getId() +
                   " doesn't exist");
       }
    }
}
