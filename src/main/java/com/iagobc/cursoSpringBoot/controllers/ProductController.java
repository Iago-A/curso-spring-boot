package com.iagobc.cursoSpringBoot.controllers;

import com.iagobc.cursoSpringBoot.configurations.ExternalizedConfigurations;
import com.iagobc.cursoSpringBoot.domain.Product;
import com.iagobc.cursoSpringBoot.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/products")
public class ProductController {

//    Class instance
//    ProductService productsService = new ProductsServiceImpl();

    // Dependency injection, it will use the bean with the interface provided to the injection
    @Autowired
    @Lazy
//    @Qualifier("jsonProducts")
    private ProductsService productsService;

    @Autowired
    private ExternalizedConfigurations externalizedConfigurations;

    @GetMapping
    public ResponseEntity<?> getProducts () {

        System.out.println(externalizedConfigurations.toString());

        List<Product> products = productsService.getProducts();

        return ResponseEntity.ok(products);
    }
}