package com.example.springboardproject.member.controller;

import com.example.springboardproject.common.Const;
import com.example.springboardproject.member.dto.requestDto.LoginRequestDto;
import com.example.springboardproject.member.dto.responseDto.MemberLoginResponseDto;
import com.example.springboardproject.member.entity.Member;
import com.example.springboardproject.member.service.AuthLoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
            @Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletRequest servletRequest) {
        // 데이터 준비
        MemberLoginResponseDto loginResponseDto
                = authLoginService.loginServiceAPI(loginRequestDto);
        // 데이터 조회
        Long memberId = loginResponseDto.getId();

        // 실패시 예외처리
        if (memberId == null) {
            ResponseEntity<MemberLoginResponseDto> loginFailResponse
                    = new ResponseEntity<>(loginResponseDto, HttpStatus.BAD_REQUEST);
            return loginFailResponse;
        }
        // 세션 발급
        HttpSession session = servletRequest.getSession();

        // 회원 정보 조회
        Optional<Member> loginServiceById = authLoginService.findById(memberId);
        if (loginServiceById.isPresent()){
            // key(Const.LOGIN_USER) : value(로그인 유저)
            session.setAttribute(Const.LOGIN_USER, memberId);
        }

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
