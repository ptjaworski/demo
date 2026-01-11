package com.ptjaworski.demo.controller;

import com.ptjaworski.demo.model.UserEntity;
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
    public String postUser(@RequestBody UserEntity userEntity) {
        return "Post User " + userEntity;
    }

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody UserEntity userEntity) {
        return "Update User ";
    }

    @DeleteMapping
    public String deleteUser() {
        return "Delete User ";
    }


}
