package com.iagobc.cursoSpringBoot.service;

import com.iagobc.cursoSpringBoot.domain.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Classes with @Service are a bean for Spring boot
@Service //("listProducts")
@ConditionalOnProperty (name = "service.products", havingValue = "listProducts")
public class ProductsServiceImpl implements ProductsService {
    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "Portatil",799.99 ,10),
            new Product(2, "Smartphone", 449.99, 25),
            new Product(3, "Tablet", 299.99, 15),
            new Product(4, "Smartwatch", 199.99, 30)
    ));


    @Override
    public List<Product> getProducts () {
        return products;
    }
}
