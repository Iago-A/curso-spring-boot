package com.iagobc.cursoSpringBoot.service;

import com.iagobc.cursoSpringBoot.domain.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductsServiceImpl {
    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "Portatil",799.99 ,10),
            new Product(2, "Smartphone", 449.99, 25),
            new Product(3, "Tablet", 299.99, 15),
            new Product(4, "Smartwatch", 199.99, 30)
    ));


    public List<Product> getProducts () {
        return products;
    }
}
