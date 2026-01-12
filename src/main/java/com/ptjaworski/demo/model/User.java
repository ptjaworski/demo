package com.ptjaworski.demo.model;

import java.util.Optional;

// рекорд - хз что это, видимо что-то чтобы иметь каст с обьекта как в TS
public record User (
        Long id,
        String firstName,
        String lastName,
        String username,
        String email,
        String password
) {}