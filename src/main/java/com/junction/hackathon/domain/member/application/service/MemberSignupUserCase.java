package com.junction.hackathon.domain.member.application.service;

import com.junction.hackathon.domain.member.application.dto.request.LoginRequest;
import com.junction.hackathon.domain.member.application.dto.request.SignupRequest;
import com.junction.hackathon.domain.member.application.dto.response.LoginResponse;
import com.junction.hackathon.domain.member.application.mapper.MemberMapper;
import com.junction.hackathon.domain.member.domain.entity.Member;
import com.junction.hackathon.domain.member.domain.service.MemberQueryService;
import com.junction.hackathon.domain.member.domain.service.MemberSaveService;
import com.junction.hackathon.domain.member.exception.InvalidPasswordException;
import com.junction.hackathon.global.config.response.TokenInfoResponse;
import com.junction.hackathon.global.config.security.jwt.TokenUtil;
import com.junction.hackathon.global.config.security.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberSignupUserCase {

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final MemberSaveService memberSaveService;
    private final MemberQueryService memberQueryService;
    private final TokenUtil tokenUtil;

    public void signup(SignupRequest signupRequest) {
        Member member = memberMapper.toEntity(signupRequest);
        member.encryptPassword(passwordEncoder);
        this.memberSaveService.saveMember(member);
    }

    public LoginResponse logIn(LoginRequest loginRequest) {
        Member member = memberQueryService.getMemberByEmail(loginRequest.getEmail());

        if (!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())) {
            throw new InvalidPasswordException();
        }
        AuthenticationUtil.makeAuthentication(member);
        TokenInfoResponse tokenResponse = tokenUtil.createToken(member);
        return LoginResponse.from(tokenResponse);
    }

}
