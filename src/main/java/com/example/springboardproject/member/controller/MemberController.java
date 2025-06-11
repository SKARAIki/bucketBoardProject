package com.example.springboardproject.member.controller;

import com.example.springboardproject.member.dto.requestDto.MemberCreateRequestDto;
import com.example.springboardproject.member.dto.responseDto.MemberCreateResponseDto;
import com.example.springboardproject.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

    // 속
    private final MemberService memberService;

    // 생
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 기

    @PostMapping
    public ResponseEntity<MemberCreateResponseDto> createMemberAPI(
            @Valid @RequestBody MemberCreateRequestDto memberCreateRequestDto)
    {

        ResponseEntity<MemberCreateResponseDto> createMemberProcess
                = memberService.createMemberService(memberCreateRequestDto);
        return createMemberProcess;

    }
}


// 공통 api 사용한
//ResponseEntity<APIResponseDto> memberService1
//        = memberService.createMemberService(memberCreateRequestDto);
//        return memberService1;