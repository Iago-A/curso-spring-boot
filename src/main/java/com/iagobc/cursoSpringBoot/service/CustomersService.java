package com.iagobc.cursoSpringBoot.service;

import com.iagobc.cursoSpringBoot.domain.Customer;

import java.net.URI;
import java.util.List;

public interface CustomersService {

    List<Customer> getCustomers ();
    Customer getSingleCustomer (String username);
    URI newCustomer (Customer customer);
    boolean updateCustomer (Customer customer);
    boolean deleteCustomer (int id);
    boolean patchCustomer (Customer customer);
}
