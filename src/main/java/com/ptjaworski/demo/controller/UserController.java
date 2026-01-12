package com.ptjaworski.demo.controller;

import com.ptjaworski.demo.dto.UserRequestDto;
import com.ptjaworski.demo.dto.UserResponseDto;
import com.ptjaworski.demo.model.User;
import com.ptjaworski.demo.model.UserEntity;
import com.ptjaworski.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("getAllUsers called");
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public String getUserById (@PathVariable Long id) {
        log.info("getUserById called");
        return "User " + id;

    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> postUser(@RequestBody UserRequestDto userToCreate) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("123", "123")
                .body(UserService.registerUser(userToCreate));
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
