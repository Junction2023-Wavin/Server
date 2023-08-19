package com.junction.hackathon.domain.member.exception;

import com.junction.hackathon.global.config.response.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends MemberException{
    public InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD_ERROR,
                HttpStatus.UNAUTHORIZED);
    }
}
