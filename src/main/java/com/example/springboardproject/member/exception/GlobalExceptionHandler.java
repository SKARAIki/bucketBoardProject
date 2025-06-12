package com.example.springboardproject.member.exception;

import com.example.springboardproject.member.dto.responseDto.APIErrorResponse;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // memberName, password 가 빈칸일 때
    @ExceptionHandler(InvalidUserInputException.class)
    public ResponseEntity<APIErrorResponse> handleInvalidUserInputException() {
        APIErrorResponse invalidUserInputResponse
                = APIErrorResponse.errorResponse(HttpStatus.BAD_REQUEST, "이름과 비밀번호를 입력 해 주세요.");
        ResponseEntity<APIErrorResponse> InvalidUserInputExceptionResponse
                = new ResponseEntity<>(invalidUserInputResponse, HttpStatus.BAD_REQUEST);
        return InvalidUserInputExceptionResponse;
    }

    // password = "비밀번호는 최소 8자리, 최대 20자리이며, 숫자, 특수문자를 포함하지 않을 때
    @ExceptionHandler(IsNotValidPasswordException.class)
    public ResponseEntity<APIErrorResponse> handleIsNotValidPasswordException() {

        String IsNotValidPasswordMessage = "비밀번호는 최소 8자리, 최대 20자리이며, 숫자, 특수문자를 포함해야 합니다.";

        APIErrorResponse invalidUserInputResponse
                = APIErrorResponse.errorResponse(HttpStatus.BAD_REQUEST, IsNotValidPasswordMessage);
        ResponseEntity<APIErrorResponse> IsNotValidPasswordExceptionResponse
                = new ResponseEntity<>(invalidUserInputResponse, HttpStatus.BAD_REQUEST);
        return IsNotValidPasswordExceptionResponse;
    }

    // password != checkPassword
    @ExceptionHandler(InvalidPasswordConfirmationException.class)
    public ResponseEntity<APIErrorResponse> handleInvalidPasswordConfirmationException() {

        String InvalidPasswordConfirmationMessage = "입력한 비밀번호가 서로 다릅니다";

        APIErrorResponse invalidUserInputResponse
                = APIErrorResponse.errorResponse(HttpStatus.BAD_REQUEST, InvalidPasswordConfirmationMessage);
        ResponseEntity<APIErrorResponse> InvalidPasswordConfirmationExceptionResponse
                = new ResponseEntity<>(invalidUserInputResponse, HttpStatus.BAD_REQUEST);
        return InvalidPasswordConfirmationExceptionResponse;
    }
    // 올바르지 못한 이메일 형식 예외처리
    //

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIErrorResponse> handleEmailConstraintViolationException()
    {
        String EmailConstraintViolationMessage = "이메일 형식을 지켜주십시오 올바른 이메일 형식은 abcd@edfgc.com 입니다";

        APIErrorResponse EmailConstraintViolationResponse
                = APIErrorResponse.errorResponse(HttpStatus.BAD_REQUEST, EmailConstraintViolationMessage);
        ResponseEntity<APIErrorResponse> EmailConstraintViolationExceptionResponse
                = new ResponseEntity<>(EmailConstraintViolationResponse, HttpStatus.BAD_REQUEST);
        return EmailConstraintViolationExceptionResponse;
    }

    // 로그인 시 잘못된 이메일을 입력 했을 때 예외처리
    @ExceptionHandler(LoginEmailNotFoundException.class)
    public ResponseEntity<APIErrorResponse> handleLoginMemberNotFoundException(LoginEmailNotFoundException e)
    {

        APIErrorResponse LoginMemberNotFoundResponse
                = APIErrorResponse.errorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        ResponseEntity<APIErrorResponse> LoginMemberNotFoundExceptionResponse
                = new ResponseEntity<>(LoginMemberNotFoundResponse, HttpStatus.BAD_REQUEST);
        return LoginMemberNotFoundExceptionResponse;
    }

    // 로그인 시 비밀번호가 다를 때 예외처리
    @ExceptionHandler(LoginInvalidPasswordException.class)
    public ResponseEntity<APIErrorResponse> handleLoginInvalidPasswordException(LoginInvalidPasswordException e)
    {
        APIErrorResponse LoginInvalidPasswordResponse
                = APIErrorResponse.errorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        ResponseEntity<APIErrorResponse> LoginInvalidPasswordExceptionResponse
                = new ResponseEntity<>(LoginInvalidPasswordResponse, HttpStatus.BAD_REQUEST);
        return LoginInvalidPasswordExceptionResponse;
    }
    // 회원가입시 중복 이메일 검사
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<APIErrorResponse> handleDuplicateEmailException(DuplicateEmailException e)
    {
        APIErrorResponse DuplicateEmailResponse
                = APIErrorResponse.errorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        ResponseEntity<APIErrorResponse> DuplicateEmailExceptionResponse
                = new ResponseEntity<>(DuplicateEmailResponse, HttpStatus.BAD_REQUEST);
        return DuplicateEmailExceptionResponse;
    }
    // 로그인 안할 때 에러메세지
    @ExceptionHandler(NotFoundSessionKeyException.class)
    public ResponseEntity<APIErrorResponse> handleNotFoundSessionKeyException(NotFoundSessionKeyException e)
    {
        APIErrorResponse NotFoundSessionKeyResponse
                = APIErrorResponse.errorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
        ResponseEntity<APIErrorResponse> NotFoundSessionKeyExceptionResponse
                = new ResponseEntity<>(NotFoundSessionKeyResponse, HttpStatus.UNAUTHORIZED);
        return NotFoundSessionKeyExceptionResponse;
    }
}
