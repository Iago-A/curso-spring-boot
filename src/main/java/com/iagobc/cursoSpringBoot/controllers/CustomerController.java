package com.iagobc.cursoSpringBoot.controllers;

import com.iagobc.cursoSpringBoot.domain.Customer;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping ("/customers")
    public Customer newCustomer (@RequestBody Customer customer) {
        customers.add(customer);
        return customer;
    }


    @PutMapping ("/customers")
    public Customer updateCustomer (@RequestBody Customer customer) {
        for (Customer client : customers) {
            if (customer.getId() == client.getId()) {
                client.setName(customer.getName());
                client.setUserName(customer.getUserName());
                client.setPassword(customer.getPassword());

                return client;
            }
        }

        // It should be a exception, not a null
        return null;
    }


    @DeleteMapping ("/customers/{id}")
    public List<Customer> deleteCustomer (@PathVariable int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customers.remove(customer);
                break;
            }
        }

        return customers;
    }
}
