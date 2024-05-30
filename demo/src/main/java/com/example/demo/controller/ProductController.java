package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    public Product createProduct(Product product) {
        return productService.createProduct(product);
    }

    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    public ResponseEntity<Product> getProductById(Long id) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<Product> updateProduct(Long id, Product productDetails) {
        Product updatedProduct = productService.updateProduct(id, productDetails);
        return ResponseEntity.ok(updatedProduct);
    }

    @PostMapping("/api/users/{userId}/products")
    public Product createProductForUser(@PathVariable Long userId, @RequestBody Product product) {
        // Récupérer l'utilisateur par ID
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        // Associer le produit à l'utilisateur
        product.setUser(user);
        // Enregistrer le produit
        return productService.createProduct(product);
    }

    public ResponseEntity<Void> deleteProduct(Long id) {
        Optional<Product> productOptional = productService.getProductById(id);
        if (productOptional.isPresent()) {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
