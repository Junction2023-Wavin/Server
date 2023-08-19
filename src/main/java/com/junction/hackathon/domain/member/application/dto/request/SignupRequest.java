package com.junction.hackathon.domain.member.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class SignupRequest {
    private String email;
    private String name;
    private String password;
    private String centerLocation;
}
