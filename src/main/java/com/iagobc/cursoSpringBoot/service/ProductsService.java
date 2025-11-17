package com.iagobc.cursoSpringBoot.service;

import com.iagobc.cursoSpringBoot.domain.Product;

import java.util.List;

public interface ProductsService {

    List<Product> getProducts ();
    int newProduct(Product newProduct);
}
