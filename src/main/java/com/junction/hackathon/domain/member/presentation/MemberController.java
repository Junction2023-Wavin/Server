package com.junction.hackathon.domain.member.presentation;

import com.junction.hackathon.domain.member.application.dto.request.LoginRequest;
import com.junction.hackathon.domain.member.application.dto.request.SignupRequest;
import com.junction.hackathon.domain.member.application.dto.response.LoginResponse;
import com.junction.hackathon.domain.member.application.service.MemberSignupUserCase;
import com.junction.hackathon.global.config.response.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.junction.hackathon.domain.member.presentation.constant.AuthResponseMessage.LOG_IN_SUCCESS;
import static com.junction.hackathon.domain.member.presentation.constant.AuthResponseMessage.SIGN_UP_SUCCESS;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class MemberController {

    private final MemberSignupUserCase memberSignupService;


    @PostMapping("/signUp")
    public ResponseEntity<SuccessResponse> signIn(@Valid @RequestBody SignupRequest signupRequest) {
        this.memberSignupService.signup(signupRequest);
        return ResponseEntity.ok(SuccessResponse.create(SIGN_UP_SUCCESS.getMessage()));
    }

    @PostMapping("/logIn")
    public ResponseEntity<SuccessResponse<LoginResponse>> logIn(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(SuccessResponse.create(LOG_IN_SUCCESS.getMessage(), this.memberSignupService.logIn(loginRequest)));
    }
}