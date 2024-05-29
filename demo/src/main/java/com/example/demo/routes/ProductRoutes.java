package com.example.demo.routes;

import com.example.demo.controller.ProductController;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductRoutes {

    @Autowired
    private ProductController productController;

    // Supprimez cette méthode
    // @PostMapping("/add")
    // public Product addProduct(@RequestBody Product product) {
    //     return productController.addProduct(product);
    // }
}
