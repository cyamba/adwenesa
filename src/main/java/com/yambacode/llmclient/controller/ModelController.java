package com.yambacode.llmclient.controller;

import com.yambacode.llmclient.config.LlmApiProperties;
import com.yambacode.llmclient.model.CompletionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/v1")
public class ModelController {

    private final WebClient webClient;

    @Autowired
    public ModelController(WebClient.Builder webClientBuilder, LlmApiProperties llmApiProperties) {
        this.webClient = webClientBuilder.baseUrl(llmApiProperties.getBaseUrl()).build();
    }

    @GetMapping("/models")
    public Mono<Map> getModels() {
        return webClient.get()
                .uri("/v1/models")
                .retrieve()
                .bodyToMono(Map.class);
    }

    @PostMapping("/completions")
    public Mono<Map> getAiResponse(@RequestBody CompletionRequest request) {
        return webClient.post()
                .uri("/v1/completions")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Map.class);
    }
}

