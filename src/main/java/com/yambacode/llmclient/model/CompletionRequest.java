package com.yambacode.llmclient.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompletionRequest {

    @Schema(description = "The model to use for generating the response", example = "llama-3.2-3b-instruct", defaultValue = "llama-3.2-3b-instruct")
    private String model = "llama-3.2-3b-instruct";

    @Schema(description = "The prompt text to generate a response for", required = true, example = "What is the theory of relativity?")
    private String prompt;

    @Schema(description = "The maximum number of tokens to generate", example = "100", defaultValue = "100")
    private int maxTokens = 100;

}

