package com.example.springboardproject.member.controller;

import com.example.springboardproject.member.dto.requestDto.LoginRequestDto;
import com.example.springboardproject.member.dto.responseDto.MemberLoginResponseDto;
import com.example.springboardproject.member.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    // 속
    private final LoginService loginService;

    //생
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    //기
    @PostMapping
    public ResponseEntity<MemberLoginResponseDto> loginAPI(@RequestBody LoginRequestDto loginRequestDto)
    {
        MemberLoginResponseDto loginResponseDto
                = loginService.loginServiceAPI(loginRequestDto);

        ResponseEntity<MemberLoginResponseDto> loginServiceResponse
                = new ResponseEntity<>(loginResponseDto, HttpStatus.OK);

        return loginServiceResponse;
    }
}
