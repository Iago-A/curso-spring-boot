package com.iagobc.cursoSpringBoot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iagobc.cursoSpringBoot.domain.Product;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Lazy
// Classes with @Service are a bean for Spring boot
@Service ("jsonProducts")
//@ConditionalOnProperty (name = "service.products", havingValue = "jsonProducts")
public class ProductsServiceJSONImpl implements ProductsService {

    @Override
    public List<Product> getProducts() {
        ObjectMapper mapper = new ObjectMapper();

        try {
            List<Product> products = mapper.readValue(this.getClass().getResourceAsStream("/products.json"),
                    new TypeReference<List<Product>>() {});

            return products;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public int newProduct(Product newProduct) {
        throw new UnsupportedOperationException("JSON-based product service is read-only.");
    }


    @Override
    public List<Product> getFilteredProducts (Double minPrice, Double maxPrice) {
        throw new UnsupportedOperationException("JSON-based product service is read-only.");
    }
}
