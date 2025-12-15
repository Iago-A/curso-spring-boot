package com.iagobc.cursoSpringBoot.service;

import com.iagobc.cursoSpringBoot.domain.Discount;
import com.iagobc.cursoSpringBoot.domain.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Classes with @Service are a bean for Spring boot
@Primary
@Service ("listProducts")
//@ConditionalOnProperty (name = "service.products", havingValue = "listProducts")
public class ProductsServiceImpl implements ProductsService {
    // Simulate data from database
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


    @Override
    public int newProduct (Product newProduct) {
        // codeNumber represents the status of the new product in the list:
        // codeNumber = 0, new product didn't exist in the list, response code 200
        // codeNumber = 1, new product already exist in the list, and matched by ID, response code 409
        // codeNumber = 2, new product already exist in the list, and matched by name, response code 409
        int codeNumber = 0;

        for (Product product : products) {
            if (product.getId() == newProduct.getId()) {
                codeNumber = 1;
                break;
            }

            if (product.getName().equalsIgnoreCase(newProduct.getName())) {
                codeNumber = 2;
                break;
            }
        }

        if (codeNumber == 0) {
            products.add(newProduct);
        }

        return codeNumber;
    }


    @Override
    public List<Product> getFilteredProducts (Double minPrice, Double maxPrice) {
        // Validate if minPrice and maxPrice are correct
        if (minPrice < 0 || maxPrice < 0) {
            throw new IllegalArgumentException("Invalid price range");
        }

        if (minPrice > maxPrice) {
            throw new IllegalArgumentException("minPrice must be lower than maxPrice");
        }

        List<Product> filteredProducts = new ArrayList<>();

        for (Product p : products) {
            if (p.getPrice() >= minPrice && p.getPrice() <= maxPrice) {
                filteredProducts.add(p);
            }
        }

        return filteredProducts;
    }


    @Override
    public boolean updateStock (Integer id, Product product) {
        if (product.getStock() == null) {
            throw new IllegalArgumentException("New stock is required");
        }

        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stock can't be negative");
        }

        for (Product p : products) {
            if (p.getId().equals(id)) {
                p.setStock(product.getStock());
                return true;
            }
        }

        return false;
    }

    @Override
    public Double productWithDiscount (Integer id, Discount discount) {
        Double newPrice = -1.0;

        if (discount.getPercentage() == null) {
            throw new IllegalArgumentException("Percentage is required");
        }

        if (discount.getPercentage() <= 0 || discount.getPercentage() >= 100) {
            throw new IllegalArgumentException("Percentage must be higher than 0 and lower than 100");
        }

        for (Product p : products) {
            if (p.getId().equals(id)) {
                newPrice = p.getPrice() - (p.getPrice() * (discount.getPercentage()/100.0));
                break;
            }
        }

        return newPrice;
    }
}
