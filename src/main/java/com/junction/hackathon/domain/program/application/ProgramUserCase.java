package com.junction.hackathon.domain.program.application;

import com.junction.hackathon.domain.program.application.dto.TokenRequest;
import com.junction.hackathon.domain.program.application.dto.TokenResponse;
import com.junction.hackathon.global.config.exception.InternalServerErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProgramUserCase {

    private final WebClient webClient;
    @Value("${solum.id}")
    private String id;

    @Value("${solum.pw}")
    private String pw;

    public void startProgram(Integer index) {
        String accessToken = getToken();
        if (accessToken == null) {
            throw new RuntimeException("Failed to get access token");
        }

        List<String> labelCodes = List.of("085C1A6BE1D5", "085C1A72E1DD", "085C1A75E1DA", "085C1B43E1DE", "085C1B49E1D4");

        Flux.fromIterable(labelCodes)
                .flatMap(labelCode -> updateLabel(accessToken, labelCode, index))
                .doOnNext(response -> {
                })
                .subscribe();
    }

    private String getToken() {
        TokenResponse.ResponseMessage responseMessage = webClient.post()
                .uri("/common/api/v2/token")
                .header("Content-Type", "application/json")  // <-- 추가한 부분
                .bodyValue(new TokenRequest(id, pw))
                .retrieve()
                .onStatus(HttpStatus::is5xxServerError, response ->
                        Mono.error(new InternalServerErrorException("User Internal Server Error")))
                .bodyToMono(new ParameterizedTypeReference<TokenResponse>() {})
                .map(TokenResponse::getResponseMessage)
                .block();

        return responseMessage.getAccess_token();
    }


    private Mono<String> updateLabel(String accessToken, String labelCode, Integer index) {
        return webClient.post()
                .uri("/common/api/v2/common/labels/page?company=JC06&store=1111")
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .body(BodyInserters.fromValue(Map.of("pageChangeList", List.of(Map.of("labelCode", labelCode, "page", index)))))
                .retrieve()
                .bodyToMono(String.class);
    }


}
