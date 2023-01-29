package com.example.boilerplate.service;

import com.example.boilerplate.config.ApiGatewayConfig;
import com.example.boilerplate.meta.annotations.TestAnnotation;
import com.example.boilerplate.model.dtos.data.PersonDetailsRequest;
import com.example.boilerplate.model.dtos.data.PersonDetailsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@TestAnnotation
class ApiGatewayServiceImplTests {
    @Autowired
    private ApiGatewayService apiGatewayService;
    @Autowired
    private ApiGatewayConfig apiGatewayConfig;
    @MockBean
    private RestTemplate restTemplate;
    @Mock
    private RedisTemplate<Object, Object> redisTemplate;
    @MockBean
    private ValueOperations<Object, Object> valueOperations;

    @Nested
    @DisplayName("testShouldKnowHowToCallAndHandlePersonDetailsApi")
    class getPersonDetailsTests {
        PersonDetailsResponse personDetailsResponse;

        @BeforeEach
        void setup() throws IOException {
            InputStream customerDetailsJson =
                    new ClassPathResource("wiremock/__files/person-details-success.json")
                            .getInputStream();

            personDetailsResponse =
                    new ObjectMapper().readValue(customerDetailsJson, PersonDetailsResponse.class);
        }

        @Test
        void testShouldCallTheApiAndReturnDateCorrectly() {
            when(restTemplate.exchange(
                    any(String.class),
                    eq(HttpMethod.GET),
                    any(),
                    eq(PersonDetailsResponse.class)))
                    .thenReturn(ResponseEntity.ok(personDetailsResponse));

            when(redisTemplate.opsForValue())
                    .thenReturn(valueOperations);
            when(redisTemplate.opsForValue().get(anyString()))
                    .thenReturn(null);

            PersonDetailsResponse response = apiGatewayService.getPersonDetails(new PersonDetailsRequest().setId("1"));
            assertEquals("Bob", response.getFirstName());
        }
    }
}
