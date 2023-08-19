package com.junction.hackathon.domain.program.presentation.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthResponseMessage {
    PROGRAM_START_SUCCESS("프로그램을 시작했습니다");
    private final String message;
}
