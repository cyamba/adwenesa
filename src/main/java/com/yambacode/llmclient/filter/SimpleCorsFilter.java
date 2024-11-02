package com.yambacode.llmclient.filter;

import com.yambacode.llmclient.config.CorsConfigProperties;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SimpleCorsFilter implements Filter {

    private final CorsConfigProperties corsConfigProperties;

    public SimpleCorsFilter(CorsConfigProperties corsConfigProperties) {
        this.corsConfigProperties = corsConfigProperties;
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, corsConfigProperties.getAllowedOrigin());
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, corsConfigProperties.getAllowedMethods());
        response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, corsConfigProperties.getMaxAge().toString());
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, corsConfigProperties.getAllowedHeaders());
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, corsConfigProperties.getAllowCredentials());

        if (HttpMethod.OPTIONS.matches((request.getMethod()))) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}

