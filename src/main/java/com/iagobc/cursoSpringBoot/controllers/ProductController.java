package com.iagobc.cursoSpringBoot.controllers;

import com.iagobc.cursoSpringBoot.configurations.ExternalizedConfigurations;
import com.iagobc.cursoSpringBoot.domain.Product;
import com.iagobc.cursoSpringBoot.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<?> newProduct (@RequestBody Product newProduct) {
        // codeNumber represents the status of the new product in the list:
        // codeNumber = 0, new product didn't exist in the list, response code 200
        // codeNumber = 1, new product already exist in the list, and matched by ID, response code 409
        // codeNumber = 2, new product already exist in the list, and matched by name, response code 409
        int codeNumber;

        codeNumber = productsService.newProduct(newProduct);

        switch (codeNumber){
            case 0:
                // Response 200
                return ResponseEntity.ok("Product " + newProduct.getName() + " was created.");
            case 1:
                // Response 409 (ID match)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("The product with ID " + newProduct.getId() +
                        " already exists.");
            case 2:
                // Response 409 (name match)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("The product with name " + newProduct.getName() +
                        " already exists.");
            default:
                throw new IllegalStateException("Unexpected codeNumber: " + codeNumber);
        }
    }
}