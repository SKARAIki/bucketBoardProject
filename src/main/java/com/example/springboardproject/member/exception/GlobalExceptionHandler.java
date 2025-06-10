package com.example.springboardproject.member.exception;

import com.example.springboardproject.member.dto.responseDto.APIErrorResponse;
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
}
