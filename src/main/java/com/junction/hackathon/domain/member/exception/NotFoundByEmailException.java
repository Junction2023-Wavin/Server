package com.junction.hackathon.domain.member.exception;


import com.junction.hackathon.global.config.response.ErrorCode;
import org.springframework.http.HttpStatus;

public class NotFoundByEmailException extends MemberException {
    public NotFoundByEmailException() {
        super(ErrorCode.NOT_FOUND_BY_EMAIL_ERROR,
                HttpStatus.NOT_FOUND);
    }
}
