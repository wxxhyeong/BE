package com.be.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /**
     * 400
     */
    PASSWORD_MATCH_INVALID(BAD_REQUEST, "비밀번호와 비밀번호 재입력이 동일하지 않습니다."),


    /**
     * 409 Conflict
     */
    EXISTING_USER_ID(CONFLICT, "이미 사용중인 ID입니다."),
    EXISTING_EMAIL(CONFLICT, "이미 사용중인 이메일입니다.");


    private final HttpStatus httpStatus;
    private final String message;
}
