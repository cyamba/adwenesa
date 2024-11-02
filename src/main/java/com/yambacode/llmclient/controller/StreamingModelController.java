package com.yambacode.llmclient.controller;

import com.yambacode.llmclient.config.LlmApiProperties;
import com.yambacode.llmclient.model.CompletionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:63343")
@RestController
@RequestMapping("/v1")
@Slf4j
public class StreamingModelController {

    private final WebClient webClient;

    public StreamingModelController(WebClient.Builder webClientBuilder, LlmApiProperties llmApiProperties) {
        this.webClient = webClientBuilder.baseUrl(llmApiProperties.getBaseUrl()).build();
    }

    @PostMapping(value = "/stream-completions", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getStreamedAiResponse(@RequestBody CompletionRequest request) {
        return webClient.post()
                .uri("/v1/completions")
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(Map.class)
                .map(response -> {
                    log.info("Response: {}", response);
                    List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
                    if (choices != null && !choices.isEmpty()) {
                        Map<String, Object> firstChoice = choices.getFirst();
                        Object text = firstChoice.get("text");
                        return text != null ? text.toString() : "[No text available]";
                    } else {
                        return "[No choices available]";
                    }
                })
                .delayElements(Duration.ofMillis(500)); // Simulate delay for streaming effect
    }

    @GetMapping(value = "/v1/stream-completions", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamCompletions(@RequestParam String prompt) {
        // Simulate streaming data chunks over time
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Streaming data chunk for prompt: " + prompt + " [chunk #" + (sequence + 1) + "]\n")
                .take(10); // Simulate 10 chunks of data
    }

}
