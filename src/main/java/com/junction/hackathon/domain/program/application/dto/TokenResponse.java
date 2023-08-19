package com.junction.hackathon.domain.program.application.dto;

import lombok.Getter;

@Getter
public class TokenResponse {
    private String responseCode;
    private ResponseMessage responseMessage;

    @Getter
    public class ResponseMessage {
        private String access_token;
        private String token_type;
        private String expires_in;
        private String refresh_token;
    }
}