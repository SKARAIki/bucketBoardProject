package com.example.springboardproject.member.dto.responseDto;

import org.springframework.http.HttpStatus;

public class MemberLoginResponseDto {
    private final Long id;
    private final int status;
    private final String message;

    public MemberLoginResponseDto(Long id,HttpStatus status, String message) {
        this.id = id;
        this.status = status.value();
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
