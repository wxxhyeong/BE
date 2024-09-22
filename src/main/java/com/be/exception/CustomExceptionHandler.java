package com.be.exception;

import com.be.common.dto.ExceptionResDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<ExceptionResDto> handlerCustomException(CustomException e) {

        return ExceptionResDto.exceptionResponse(e.getExceptionCode());
    }
}
