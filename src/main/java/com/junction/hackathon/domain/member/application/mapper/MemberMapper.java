package com.junction.hackathon.domain.member.application.mapper;

import com.junction.hackathon.domain.member.application.dto.request.SignupRequest;
import com.junction.hackathon.domain.member.domain.constant.Role;
import com.junction.hackathon.domain.member.domain.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member toEntity(SignupRequest signupRequest) {
        Member newMember = Member.builder()
                .email(signupRequest.getEmail())
                .name(signupRequest.getName())
                .password(signupRequest.getPassword())
                .centerLocation(signupRequest.getCenterLocation())
                .role(Role.USER)
                .build();

        return newMember;
    }
}