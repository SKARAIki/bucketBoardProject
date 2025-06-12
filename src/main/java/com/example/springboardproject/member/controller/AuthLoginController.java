package com.example.springboardproject.member.controller;

import com.example.springboardproject.member.dto.requestDto.LoginRequestDto;
import com.example.springboardproject.member.dto.responseDto.MemberLoginResponseDto;
import com.example.springboardproject.member.service.AuthLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j

@RestController
@RequestMapping("/auth")
public class AuthLoginController {
    // 속
    private final AuthLoginService authLoginService;


    //생
    public AuthLoginController(AuthLoginService authLoginService) {
        this.authLoginService = authLoginService;
    }

    //기

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponseDto> loginAPI(
            @Valid HttpServletRequest servletRequest,
            @RequestBody LoginRequestDto loginRequestDto)

    {MemberLoginResponseDto loginResponseDto
            = authLoginService.loginServiceAPI(loginRequestDto,servletRequest);
        // 로그인 성공(?)
        ResponseEntity<MemberLoginResponseDto> loginServiceResponse
                = new ResponseEntity<>(loginResponseDto, HttpStatus.OK);

        return loginServiceResponse;
    }

    // 로그아웃
    @PostMapping("/logout")
    public String logOutAPI(HttpServletRequest httpServletRequest) {
        // 로그아웃 실행시 session : true -> false(null)
        HttpSession session = httpServletRequest.getSession(false);
        // 세션이 존재하면 -> 로그인이 된 경우
        if(session == null) {
            session.invalidate(); // 해당 세션(데이터)을 삭제한다.
        }
        return "로그아웃 성공";
    }

}
