package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;


    public User createUser(User user) {
        return userService.createUser(user);
    }
    @GetMapping("/api/users/{userId}/products")
    public ResponseEntity<List<Product>> getAllProductsForUser(@PathVariable Long userId) {
        // Récupérer l'utilisateur par ID
        User user = userService.getUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Récupérer tous les produits associés à l'utilisateur
        List<Product> products = productService.getAllProductsForUser(user);

        // Retourner la liste des produits associés à l'utilisateur
        return ResponseEntity.ok(products);
    }

    // UserController
    @DeleteMapping("/api/users/{userId}/products/{productId}") // <-- URL pour supprimer un produit d'un utilisateur
    public ResponseEntity<Void> deleteProductForUser(@PathVariable Long userId, @PathVariable Long productId) {
        // Vérifier si l'utilisateur existe
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            // Récupérer l'utilisateur
            User user = userOptional.get();

            // Vérifier si le produit appartient à l'utilisateur
            Optional<Product> productOptional = productService.getProductById(productId);
            if (productOptional.isPresent() && productOptional.get().getUser().getId().equals(userId)) {
                // Supprimer le produit
                productService.deleteProduct(productId);
                return ResponseEntity.ok().build();
            } else {
                // Le produit n'appartient pas à l'utilisateur
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            // L'utilisateur n'existe pas
            return ResponseEntity.notFound().build();
        }
    }



    // UserController
    @PutMapping("/api/users/{userId}/products/{productId}") // <-- URL pour mettre à jour un produit d'un utilisateur
    public ResponseEntity<Product> updateProductForUser(@PathVariable Long userId, @PathVariable Long productId, @RequestBody Product productDetails) {
        // Vérifier si l'utilisateur existe
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            // Récupérer l'utilisateur
            User user = userOptional.get();

            // Vérifier si le produit appartient à l'utilisateur
            Optional<Product> productOptional = productService.getProductById(productId);
            if (productOptional.isPresent() && productOptional.get().getUser().getId().equals(userId)) {
                // Mettre à jour les détails du produit
                Product updatedProduct = productService.updateProduct(productId, productDetails);
                return ResponseEntity.ok(updatedProduct);
            } else {
                // Le produit n'appartient pas à l'utilisateur
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } else {
            // L'utilisateur n'existe pas
            return ResponseEntity.notFound().build();
        }
    }


    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    public ResponseEntity<User> updateUser(Long id, User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            userService.deleteUser(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
