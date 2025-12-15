package com.iagobc.cursoSpringBoot.service;

import com.iagobc.cursoSpringBoot.domain.Discount;
import com.iagobc.cursoSpringBoot.domain.Product;

import java.util.List;

public interface ProductsService {

    List<Product> getProducts ();
    int newProduct(Product newProduct);
    List<Product> getFilteredProducts (Double minPrice, Double maxPrice);
    boolean updateStock (Integer id, Product product);
    Double productWithDiscount (Integer id, Discount discount);
}
