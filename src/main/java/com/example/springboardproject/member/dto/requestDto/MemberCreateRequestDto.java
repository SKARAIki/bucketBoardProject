package com.example.springboardproject.member.dto.requestDto;

public class MemberCreateRequestDto {
    // 속
    private final String memberName;
    private final String email;
    private final String password;
    private final String checkPassword;

    // 생
    /**
     * Request에선 DB에 저장된 Entity의 데이터정보를 가져올수가 없는데 아무생각없이 Entity entity.getxxxx 사용해서
     * 500에러 및 HttpMessageConversionException 발생
     */
    public MemberCreateRequestDto(String memberName, String email, String password, String checkPassword) {
        this.memberName = memberName;
        this.email = email;
        this.password = password;
        this.checkPassword = checkPassword;
    }
    //기

    public String getMemberName() {
        return memberName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCheckPassword() {
        return checkPassword;
    }
}
