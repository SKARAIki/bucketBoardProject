package com.example.springboardproject.member.exception;

public class LoginInvalidPasswordException extends RuntimeException{
    public LoginInvalidPasswordException(){
        super("비밀번호가 맞지 않습니다");
    }
}
