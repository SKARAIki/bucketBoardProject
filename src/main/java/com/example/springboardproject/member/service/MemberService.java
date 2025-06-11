package com.example.springboardproject.member.service;

import com.example.springboardproject.Config.PasswordEncoder;
import com.example.springboardproject.member.dto.requestDto.MemberCreateRequestDto;
import com.example.springboardproject.member.dto.responseDto.MemberCreateResponseDto;
import com.example.springboardproject.member.entity.Member;
import com.example.springboardproject.member.exception.InvalidPasswordConfirmationException;
import com.example.springboardproject.member.exception.InvalidUserInputException;
import com.example.springboardproject.member.exception.IsNotValidPasswordException;
import com.example.springboardproject.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class MemberService {
    // 속
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 생
    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder)
    {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //기

    public ResponseEntity<MemberCreateResponseDto> createMemberService
            (MemberCreateRequestDto memberCreateRequestDto) {
        // 데이터 준비
        String memberName = memberCreateRequestDto.getMemberName();
        String memberEmail = memberCreateRequestDto.getEmail();
        String memberPassword = memberCreateRequestDto.getPassword().replaceAll("\\s+", "");
        String memberCheckPassword = memberCreateRequestDto.getCheckPassword().replaceAll("\\s+", "");


        /**
         * 데이터 검증
         * 이름과 비밀번호가 공란일 때
         * 비밀번호 형식을 지키지 않았을 때
         * 입력한 비밀번호가 서로 다를 때
         */
        if (memberName.isEmpty() || memberPassword.isEmpty()) {
            throw new InvalidUserInputException();

        } else if (!memberPassword.matches("^(?=.*[a-z])(?=.*\\d)(?=.*[^\\w\\s])[\\S]{8,20}$")) {
            throw new IsNotValidPasswordException();

        }
        else if (!memberPassword.equals(memberCheckPassword)) {
            throw new InvalidPasswordConfirmationException();
        }
        // 비밀번호 암호화
        String passwordEncode = passwordEncoder.encode(memberPassword);

        // Entity 준비
        Member member = Member.createFromMemberCreateRequestDto(memberName, memberEmail, passwordEncode);
        // 데이터 저장
        memberRepository.save(member);
        // 응답 Dto 준비
        MemberCreateResponseDto memberCreateResponseDto = new MemberCreateResponseDto();
        ResponseEntity<MemberCreateResponseDto> successResponse
                = new ResponseEntity<>(memberCreateResponseDto, HttpStatusCode.valueOf(200));

        // 반환
        return successResponse;
    }
}

/**
 * {
 * 		"status": 201,
 * 		"message": "회원가입이 정상적으로 완료 되었습니다."
 * }    "data": null
 *  아래 코드
 */
//        String successMessage = "회원가입이 성공적으로 완료";
//        APIResponseDto succeed = APIResponseDto.success(HttpStatus.CREATED, successMessage);
//        ResponseEntity<APIResponseDto> successResponse
//        = new ResponseEntity<>(succeed , HttpStatus.CREATED);
