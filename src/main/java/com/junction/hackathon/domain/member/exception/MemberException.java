package com.junction.hackathon.domain.member.exception;

import com.junction.hackathon.global.config.exception.ApplicationException;
import com.junction.hackathon.global.config.response.ErrorCode;
import org.springframework.http.HttpStatus;

public abstract class MemberException extends ApplicationException {
    protected MemberException(ErrorCode errorCode, HttpStatus httpStatus) {
        super(errorCode, httpStatus);
    }
}
