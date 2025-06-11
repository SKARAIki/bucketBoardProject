package com.example.springboardproject.member.dto.requestDto;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDto {
    @NotBlank
    private final String email;
    @NotBlank
    private final String password;

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
