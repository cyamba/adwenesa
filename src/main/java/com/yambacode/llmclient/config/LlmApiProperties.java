package com.yambacode.llmclient.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "llm.api")
@Getter
@Setter
public class LlmApiProperties {

    private String host;
    private int port;
    private Paths paths;

    public String getBaseUrl() {
        return String.format("http://%s:%d", host, port);
    }

    @Getter
    @Setter
    public static class Paths {
        private String models;
        private String predictions;
        private String training;
    }
}

