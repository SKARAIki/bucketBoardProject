package com.example.springboardproject.member.dto.responseDto;

public class MemberCreateResponseDto {
    // 속
    private int status;
    private String message;

    // 생

    public MemberCreateResponseDto() {
        this.status = 200;
        this.message = "회원가입이 정상적으로 완료 되었습니다.";
    }

    // 기

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
