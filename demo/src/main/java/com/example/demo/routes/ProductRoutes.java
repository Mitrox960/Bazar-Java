package com.example.demo.routes;

import com.example.demo.controller.ProductController;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductRoutes {

    @Autowired
    private ProductController productController;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productController.createProduct(product);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productController.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productController.getProductById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productController.updateProduct(id, productDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        return productController.deleteProduct(id);
    }
}
