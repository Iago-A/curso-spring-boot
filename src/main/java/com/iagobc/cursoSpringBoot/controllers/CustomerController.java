package com.iagobc.cursoSpringBoot.controllers;

import com.iagobc.cursoSpringBoot.domain.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


//    @RequestMapping (method = RequestMethod.GET)
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers () {

        return ResponseEntity.ok(customers);
    }


//    @RequestMapping (value = "/{username}", method = RequestMethod.GET)
    @GetMapping ("/{username}")
    public ResponseEntity<?> getSingleCustomer (@PathVariable String username) {
        for (Customer customer : customers) {
            if (customer.getUserName().equalsIgnoreCase(username)) {
                return ResponseEntity.ok(customer);
            }
        }

        // Response 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The customer with username " + username + " doesn't exist.");
    }


    @PostMapping
    public ResponseEntity<?> newCustomer (@RequestBody Customer customer) {
        customers.add(customer);

        // Response 201
        return ResponseEntity.status(HttpStatus.CREATED).body("Customer " + customer.getName() + " was created.");
    }


    @PutMapping
    public ResponseEntity<?> updateCustomer (@RequestBody Customer customer) {
        for (Customer client : customers) {
            if (customer.getId() == client.getId()) {
                client.setName(customer.getName());
                client.setUserName(customer.getUserName());
                client.setPassword(customer.getPassword());

                return ResponseEntity.ok("The customer with ID " + customer.getId() + " was updated.");
            }
        }

        // Response 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The customer with ID " + customer.getId() + " doesn't exist.");
    }


    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deleteCustomer (@PathVariable int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customers.remove(customer);

                // Response 200
                return ResponseEntity.ok("The customer with ID  " + id + " was removed.");
            }
        }

        // Response 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The customer with ID " + id + " doesn't exist.");
    }


    @PatchMapping
    public ResponseEntity<?> patchCustomer (@RequestBody Customer customer) {
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

                // Response 200
                return ResponseEntity.ok("The customer with ID " + customer.getId() + " was updated.");
            }
        }

        // Response 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The customer with ID " + customer.getId() + " doesn't exist");
    }
}
