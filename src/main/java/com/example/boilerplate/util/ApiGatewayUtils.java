package com.example.boilerplate.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiGatewayUtils {
    public static <T> HttpEntity<T> buildRequestHttpEntity(T req) {
        return new HttpEntity<>(req, buildHeaders());
    }

    public static HttpEntity<String> buildRequestHttpEntity() {
        return new HttpEntity<>(buildHeaders());
    }

    public static MultiValueMap<String, String> transformToMultiValueMap(Map<String, String> paramsKeyValue) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        paramsKeyValue.forEach(params::add);
        return params;
    }

    public static HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.ACCEPT, "*/*");

        return headers;
    }
}
