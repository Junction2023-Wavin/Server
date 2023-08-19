package com.junction.hackathon.domain.member.application.dto.response;

import com.junction.hackathon.global.config.response.TokenInfoResponse;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponse {
    private String accessToken;

    public static LoginResponse from(TokenInfoResponse tokenInfoResponse) {
        return LoginResponse.builder()
                .accessToken(tokenInfoResponse.getAccessToken())
                .build();
    }
}
