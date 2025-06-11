package com.example.springboardproject.member.exception;

public class NotFoundSessionKeyException extends RuntimeException{
    public NotFoundSessionKeyException(){
        super("로그인 해주세요");
    }
}
