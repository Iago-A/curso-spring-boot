package com.iagobc.cursoSpringBoot.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iagobc.cursoSpringBoot.domain.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

// Classes with @Service are a bean for Spring boot
//@Primary
@Service //("jsonProducts")
@ConditionalOnProperty (name = "service.products", havingValue = "jsonProducts")
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
}
