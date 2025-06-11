package com.example.springboardproject.member.dto.responseDto;

import org.springframework.http.HttpStatus;

public class MemberLoginResponseDto {
    private int status;
    private String message;

    public MemberLoginResponseDto(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
