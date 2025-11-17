package com.iagobc.cursoSpringBoot.service;

import com.iagobc.cursoSpringBoot.domain.Customer;
import com.iagobc.cursoSpringBoot.domain.Product;
import com.iagobc.cursoSpringBoot.domain.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    @Lazy
    private CustomersService customersService;

    @Autowired
    @Lazy
    private ProductsService productsService;

    @Override
    public Stat getStats () {
        List<Customer> customers = customersService.getCustomers();
        List<Product> products = productsService.getProducts();

        int totalCustomers = customers.size();
        int totalProducts = 0;
        double sumOfPrices = 0;
        double averagePrice = 0;

        for (Product product : products) {
            totalProducts++;
            sumOfPrices += product.getPrice();
        }

        averagePrice = sumOfPrices / totalProducts;

        Stat stat = new Stat(totalCustomers, totalProducts, averagePrice);

        return stat;
    }
}
