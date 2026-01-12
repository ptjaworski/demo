package com.ptjaworski.demo.dto;

public record UserResponseDto (
        Long id,
        String firstName,
        String lastName,
        String username,
        String email
) {}