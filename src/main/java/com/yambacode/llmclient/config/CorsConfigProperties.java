package com.yambacode.llmclient.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "cors")
public class CorsConfigProperties {
    private String allowedOrigin;
    private String allowedMethods;
    private String allowedHeaders;
    private String allowCredentials;
    private Long maxAge;
}

