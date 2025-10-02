package com.example.board.service;

import com.example.board.dto.GeminiRequestDTO;
import com.example.board.dto.GeminiResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class GeminiService {

    private final WebClient webClient;
    private final String apiKey;
    private final String apiUrl;

    // application.properties에서 설정값을 주입받습니다.
    public GeminiService(WebClient.Builder webClientBuilder,
                         @Value("${gemini.api.key}") String apiKey,
                         @Value("${gemini.api.url}") String apiUrl) {
        this.webClient = webClientBuilder.build();
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
    }

    /**
     * Gemini API에 프롬프트를 보내고 응답 텍스트를 받아옵니다.
     * @param prompt 사용자 질문
     * @return Gemini 응답 텍스트
     */
    public Mono<String> getCompletion(String prompt) {
        // API 요청 본문 생성
        GeminiRequestDTO.Part part = new GeminiRequestDTO.Part(prompt);
        GeminiRequestDTO.Content content = new GeminiRequestDTO.Content(new GeminiRequestDTO.Part[]{part});
        GeminiRequestDTO requestDto = new GeminiRequestDTO(new GeminiRequestDTO.Content[]{content});

        return webClient.post()
                .uri(apiUrl + "?key=" + apiKey) // URI에 API 키를 쿼리 파라미터로 추가
                .bodyValue(requestDto)          // 요청 본문 설정
                .retrieve()                     // 응답 받아오기
                .bodyToMono(GeminiResponseDTO.class) // 응답을 DTO로 변환
                .map(response -> {
                    // 응답 객체에서 실제 텍스트만 추출하여 반환
                    if (response != null && response.candidates() != null && response.candidates().length > 0) {
                        return response.candidates()[0].content().parts()[0].text();
                    }
                    return "죄송합니다, 답변을 생성할 수 없습니다.";
                });
    }
}
