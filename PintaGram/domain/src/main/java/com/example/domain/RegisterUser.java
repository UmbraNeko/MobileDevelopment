package com.example.domain;

import com.example.domain.AuthRepository;

public class RegisterUser {
    private final AuthRepository authRepository;

    public RegisterUser(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void execute(String email, String password, AuthRepository.AuthCallback callback) {
        authRepository.register(email, password, callback);
    }
}
