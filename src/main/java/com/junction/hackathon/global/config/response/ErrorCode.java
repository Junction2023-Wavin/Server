package com.junction.hackathon.global.config.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    BAD_REQUEST("400", "올바른 패턴을 입력하세요."),
    METHOD_NOT_ALLOWED("405", "클라이언트가 사용한 HTTP 메서드가 리소스에서 허용되지 않습니다."),
    INTERNAL_SERVER_ERROR("500", "서버에서 요청을 처리하는 동안 오류가 발생했습니다."),

    //유저 관련 에러 코드
    NOT_FOUND_BY_EMAIL_ERROR( "U0001",  "유효하지 않는 유저입니다."),
    TOKEN_INVALID_ERROR("AU0002", "입력 토큰이 유효하지 않습니다."),
    INVALID_PASSWORD_ERROR( "U0001",  "유효하지 않는 비밀번호입니다.");
    private String errorCode;
    private String message;

}