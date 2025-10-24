package com.iagobc.cursoSpringBoot.controllers;

import com.iagobc.cursoSpringBoot.domain.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CustomerController {

    // Simulate data from database
    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(1, "Laura García", "LauraGF", "1234"),
            new Customer(2, "Iago Blanco", "Iago-A", "4321"),
            new Customer(3, "Marta Vilas", "mV1las", "6789"),
            new Customer(4, "Jesus Largo", "Jeguel", "9876")
    ));

    @GetMapping ("/customers")
    public List<Customer> getCustomers () {

        return customers;
    }

    @GetMapping ("/customers/{username}")
    public Customer getSingleCustomer (@PathVariable String username) {
        for (Customer customer : customers) {
            if (customer.getUserName().equalsIgnoreCase(username)) {
                return customer;
            }
        }

        // Here we should return an exception 404
        return null;
    }
}
