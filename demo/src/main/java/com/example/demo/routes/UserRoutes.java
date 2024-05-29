package com.example.demo.routes;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserRoutes {

    @Autowired
    private UserController userController;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userController.createUser(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userController.deleteUser(id);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userController.getUser(id);
    }
}
