package com.ptjaworski.demo.controller;

import com.ptjaworski.demo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{id}")
    public String getUserById (@PathVariable Long id) {
        log.info("getUserById called");
        return "User " + id;

    }

    @PostMapping("/register")
    public String postUser(@RequestBody User user) {
        return "Post User " + user;
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        return "Update User ";
    }

    @DeleteMapping
    public String deleteUser() {
        return "Delete User ";
    }


}
