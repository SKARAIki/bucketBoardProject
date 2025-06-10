package com.example.springboardproject.member.dto.requestDto;

public class MemberCreateRequestDto {
    // 속
    private String memberName;
    private String password;
    private String checkPassword;

    // 생
    public MemberCreateRequestDto(String memberName, String password, String checkPassword) {
        this.memberName = memberName;
        this.password = password;
        this.checkPassword = checkPassword;
    }
    //기

    public String getMemberName() {
        return memberName;
    }

    public String getPassword() {
        return password;
    }

    public String getCheckPassword() {
        return checkPassword;
    }
}
