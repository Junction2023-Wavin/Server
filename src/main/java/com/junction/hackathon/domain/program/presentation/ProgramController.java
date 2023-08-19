package com.junction.hackathon.domain.program.presentation;

import com.junction.hackathon.domain.program.application.ProgramUserCase;
import com.junction.hackathon.global.config.response.SuccessResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.junction.hackathon.domain.program.presentation.constant.AuthResponseMessage.PROGRAM_START_SUCCESS;

@RestController
@AllArgsConstructor
@RequestMapping("/program")
public class ProgramController {
    private final ProgramUserCase programUserCase;

    @PostMapping("/start/{index}")
    public ResponseEntity<SuccessResponse> startProgram(@PathVariable Integer index) {
        this.programUserCase.startProgram(index);
        return ResponseEntity.ok(SuccessResponse.create(PROGRAM_START_SUCCESS.getMessage()));
    }

}
