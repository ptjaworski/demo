package com.ptjaworski.demo.dto;

public record UserRequestDto (
        Long id,
        String firstName,
        String lastName,
        String username,
        String email,
        String password
) {}