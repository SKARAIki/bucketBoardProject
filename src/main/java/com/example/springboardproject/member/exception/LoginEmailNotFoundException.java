package com.example.springboardproject.member.exception;

public class LoginEmailNotFoundException extends RuntimeException{
    public LoginEmailNotFoundException(){
        super("존재하지 않는 회원 입니다. 이메일을 확인 해 주세요");
    }
}
