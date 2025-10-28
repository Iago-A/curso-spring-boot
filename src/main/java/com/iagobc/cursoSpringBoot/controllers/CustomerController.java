package com.iagobc.cursoSpringBoot.controllers;

import com.iagobc.cursoSpringBoot.domain.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping ("/customers")
public class CustomerController {

    // Simulate data from database
    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(1, "Laura García", "LauraGF", "1234"),
            new Customer(2, "Iago Blanco", "Iago-A", "4321"),
            new Customer(3, "Marta Vilas", "mV1las", "6789"),
            new Customer(4, "Jesus Largo", "Jeguel", "9876")
    ));


    @RequestMapping (method = RequestMethod.GET)
//    @GetMapping
    public List<Customer> getCustomers () {

        return customers;
    }


    @RequestMapping (value = "/{username}", method = RequestMethod.GET)
//    @GetMapping ("/{username}")
    public Customer getSingleCustomer (@PathVariable String username) {
        for (Customer customer : customers) {
            if (customer.getUserName().equalsIgnoreCase(username)) {
                return customer;
            }
        }

        // Here we should return an exception 404
        return null;
    }


    @PostMapping
    public Customer newCustomer (@RequestBody Customer customer) {
        customers.add(customer);
        return customer;
    }


    @PutMapping
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


    @DeleteMapping ("/{id}")
    public List<Customer> deleteCustomer (@PathVariable int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customers.remove(customer);
                break;
            }
        }

        return customers;
    }


    @PatchMapping
    public Customer patchCustomer (@RequestBody Customer customer) {
        for (Customer client : customers) {
            if (client.getId() == customer.getId()) {

                if (customer.getName() != null) {
                    client.setName(customer.getName());
                }

                if (customer.getUserName() != null) {
                    client.setUserName(customer.getUserName());
                }

                if (customer.getPassword() != null) {
                    client.setPassword(customer.getPassword());
                }

                return client;
            }
        }

        // It should be a exception, not a null
        return null;
    }
}
