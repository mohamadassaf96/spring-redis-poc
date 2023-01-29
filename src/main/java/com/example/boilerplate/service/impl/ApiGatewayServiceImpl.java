package com.example.boilerplate.service.impl;

import com.example.boilerplate.config.ApiGatewayConfig;
import com.example.boilerplate.model.dtos.data.PersonDetailsRequest;
import com.example.boilerplate.model.dtos.data.PersonDetailsResponse;
import com.example.boilerplate.service.ApiGatewayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static com.example.boilerplate.model.constants.RedisConstants.PERSON_DETAILS_CACHE;
import static com.example.boilerplate.model.constants.RedisConstants.SHORT_TERM_CACHE_TTL_SECONDS;
import static com.example.boilerplate.util.ApiGatewayUtils.buildRequestHttpEntity;
import static com.example.boilerplate.util.ApiGatewayUtils.transformToMultiValueMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiGatewayServiceImpl implements ApiGatewayService {
    private final RestTemplate restTemplate;
    private final RedisService redisService;
    private final ApiGatewayConfig apiGatewayConfig;

    @Override
    public PersonDetailsResponse getPersonDetails(PersonDetailsRequest request) {
        PersonDetailsResponse cachedResponse = redisService.get(PERSON_DETAILS_CACHE, request.getId());
        if (Objects.nonNull(cachedResponse))
            return cachedResponse;
        Supplier<PersonDetailsResponse> emptyResponse = PersonDetailsResponse::new;
        PersonDetailsResponse response;

        Map<String, String> paramsKeyValue = new HashMap<>();
        paramsKeyValue.put("id", request.getId());

        HttpEntity<String> entity = buildRequestHttpEntity();

        MultiValueMap<String, String> params = transformToMultiValueMap(paramsKeyValue);

        try {
            response = restTemplate.exchange(
                    apiGatewayConfig.getPersonDetailsUriWithParams(params),
                    HttpMethod.GET,
                    entity,
                    PersonDetailsResponse.class).getBody();
            if (Objects.nonNull(response)) {
                redisService.put(PERSON_DETAILS_CACHE, request.getId(), response, SHORT_TERM_CACHE_TTL_SECONDS, TimeUnit.SECONDS);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return emptyResponse.get();
        }
        return response;
    }
}
