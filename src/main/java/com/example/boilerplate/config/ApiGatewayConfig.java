package com.example.boilerplate.config;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Setter
@Configuration
@NoArgsConstructor
@ConfigurationProperties(prefix = "api-gateway")
public class ApiGatewayConfig {
    private String url;
    private String personDetailsEndpoint;

    public String getPersonDetailsUriWithParams(MultiValueMap<String, String> params) {
        return getURIAsString(personDetailsEndpoint, params);
    }

    private URI getUri(String endpoint) {
        return UriComponentsBuilder.fromUriString(url + endpoint).build().toUri();
    }

    private String getURIAsString(String endpoint, MultiValueMap<String, String> params) {
        return UriComponentsBuilder.fromHttpUrl(this.url + endpoint).queryParams(params).build().toString();
    }
}
