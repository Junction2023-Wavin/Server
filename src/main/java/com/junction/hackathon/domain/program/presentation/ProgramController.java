package com.junction.hackathon.domain.program.presentation;

import com.junction.hackathon.domain.program.application.ProgramUserCase;
import com.junction.hackathon.global.config.response.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.junction.hackathon.domain.program.presentation.constant.AuthResponseMessage.PROGRAM_START_SUCCESS;

@RestController
@AllArgsConstructor
@RequestMapping("/program")
public class ProgramController {
    private final ProgramUserCase programUserCase;

    @PostMapping("/start")
    public ResponseEntity<SuccessResponse> startProgram() {
        this.programUserCase.startProgram();
        return ResponseEntity.ok(SuccessResponse.create(PROGRAM_START_SUCCESS.getMessage()));
    }

}
