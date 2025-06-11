package com.example.springboardproject.member.service;

import com.example.springboardproject.Config.PasswordEncoder;
import com.example.springboardproject.member.dto.requestDto.LoginRequestDto;
import com.example.springboardproject.member.dto.responseDto.MemberLoginResponseDto;
import com.example.springboardproject.member.entity.Member;
import com.example.springboardproject.member.exception.LoginEmailNotFoundException;
import com.example.springboardproject.member.exception.LoginInvalidPasswordException;
import com.example.springboardproject.member.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthLoginService {
    // 속
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //생

    public AuthLoginService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //기
    public MemberLoginResponseDto loginServiceAPI(LoginRequestDto requestDto) {
        //데이터 조회

        /**
         * Optional<Member> findByEmail(String email); 의 작성된 코드에서 조회하기 때문에
         * Optional<Member> memberRepositoryByEmail 로 되어야 하지만 Optional 의 제공 메서드인
         * .orElseThrow 는 null이 있다면 throw를, 없다면 반드시 null이 아닌 값을 반환하기 때문에
         * null을 포함하는 Optional<Member> -> Member 데이터 변경이 가능
         */
        Member memberRepositoryByEmail
                = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new LoginEmailNotFoundException());

        // 비밀번호 일치 확인. 평문 비밀번호를 인코드하여, db에 인코드화 되어 저장된 비밀번호와 매치
        boolean passwordMatches
                = passwordEncoder.matches(requestDto.getPassword(),
                memberRepositoryByEmail.getPassword());
        if (!passwordMatches) {
            throw new LoginInvalidPasswordException();
        }
        Long id = memberRepositoryByEmail.getId();
        // 로그인 응답 DTO

        MemberLoginResponseDto loginResponseDto
                = new MemberLoginResponseDto(id,HttpStatus.OK, "로그인 되었습니다. 환영합니다");
        // 반환
        return loginResponseDto;

    }
    public Optional<Member> findById(Long id){
        Optional<Member> byId = memberRepository.findById(id);
        return byId;
    }
}



