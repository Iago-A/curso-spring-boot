package com.iagobc.cursoSpringBoot.service;

import com.iagobc.cursoSpringBoot.domain.Customer;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomersServiceImpl implements CustomersService {
    // Simulate data from database
    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(1, "Laura García", "LauraGF", "1234"),
            new Customer(2, "Iago Blanco", "Iago-A", "4321"),
            new Customer(3, "Marta Vilas", "mV1las", "6789"),
            new Customer(4, "Jesus Largo", "Jeguel", "9876")
    ));

    @Override
    public List<Customer> getCustomers () {
        return customers;
    }

    @Override
    public Customer getSingleCustomer (String username) {
        for (Customer customer : customers) {
            if (customer.getUserName().equalsIgnoreCase(username)) {
                return customer;
            }
        }

        return null;
    }

    @Override
    public URI newCustomer (Customer customer) {
        customers.add(customer);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(customer.getUserName())
                .toUri();

        return location;
    }

    @Override
    public boolean updateCustomer (Customer customer) {
        boolean customerExists = false;

        for (Customer c : customers) {
            if (c.getId() == customer.getId()) {
                c.setName(customer.getName());
                c.setUserName(customer.getUserName());
                c.setPassword(customer.getPassword());

                customerExists = true;
                break;
            }
        }

        return customerExists;
    }

    @Override
    public boolean deleteCustomer (int id) {
        boolean customerFound = false;

        for (Customer customer : customers) {
            if (customer.getId() == id) {
                customers.remove(customer);
                customerFound = true;
                break;
            }
        }

        return customerFound;
    }

    @Override
    public boolean patchCustomer (Customer customer) {
        boolean customerExists = false;

        for (Customer c : customers) {
            if (c.getId() == customer.getId()) {

                if (customer.getName() != null) {
                    c.setName(customer.getName());
                }

                if (customer.getUserName() != null) {
                    c.setUserName(customer.getUserName());
                }

                if (customer.getPassword() != null) {
                    c.setPassword(customer.getPassword());
                }

                customerExists = true;
                break;
            }
        }

        return customerExists;
    }
}
