package com.example.springboardproject.member.dto.responseDto;

public class MemberCreateResponseDto {
    // 속
    private final Long id;
    private final int status;
    private final String message;

    // 생

    public MemberCreateResponseDto(Long id) {
        this.id = id;
        this.status = 200;
        this.message = "회원가입이 정상적으로 완료 되었습니다.";
    }

    // 기

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
