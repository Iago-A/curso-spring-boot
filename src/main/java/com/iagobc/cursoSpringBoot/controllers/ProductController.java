package com.iagobc.cursoSpringBoot.controllers;

import com.iagobc.cursoSpringBoot.configurations.ExternalizedConfigurations;
import com.iagobc.cursoSpringBoot.domain.Discount;
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

    @GetMapping ("/filter")
    public ResponseEntity<?> getFilteredProducts (@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        try {
            List<Product> filteredProducts = productsService.getFilteredProducts(minPrice, maxPrice);

            // Response 200
            if (filteredProducts.isEmpty()) {
                return  ResponseEntity.status(HttpStatus.OK).body("There aren't products in the price range specified");
            }

            // Response 200
            return ResponseEntity.ok(filteredProducts);
        } catch (IllegalArgumentException e) {
            // Response 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping ("/{id}/stock")
    public ResponseEntity<String> patchProductsStockById (@PathVariable Integer id, @RequestBody Product product) {
        boolean updated;

        try{
            updated = productsService.updateStock(id, product);

            // Response 200
            if (updated) {
                return  ResponseEntity.status(HttpStatus.OK).body("Stock for product id = " + id + " updated to " +
                        product.getStock());
            }
            // Response 404
            else {
                return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("The product with id = " + id +
                        " wasn't found");
            }
        } catch (IllegalArgumentException e) {
            // Response 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping ("/{id}/discount")
    public ResponseEntity<String> showProductWithDiscount (@PathVariable Integer id, @RequestBody Discount discount) {
        Double newPrice;

        try {
            newPrice = productsService.productWithDiscount(id, discount);

            // Response 200
            if (newPrice != -1.0) {
                return ResponseEntity.status(HttpStatus.OK).body("Product id = " + id + " discounted by " +
                        discount.getPercentage() + "%. New price = " + newPrice);
            }
            //Response 404
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The product with id = " + id + " wasn't found");
            }
        } catch (IllegalArgumentException e) {
            // Response 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}