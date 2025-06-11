package com.example.springboardproject.member.dto.responseDto;

import org.springframework.http.HttpStatus;

public class APIErrorResponse {

    // 속성
    private final int status;
    private final String message;



    // 생성자
    private APIErrorResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;


    }
    // 기능

    // 공통 에러 응답
    public static APIErrorResponse errorResponse(HttpStatus status, String message){
        return new APIErrorResponse(status, message);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }


}
