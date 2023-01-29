package com.example.boilerplate.service.impl;

import com.example.boilerplate.model.dtos.data.PersonDetailsRequest;
import com.example.boilerplate.model.dtos.data.PersonDetailsResponse;
import com.example.boilerplate.service.ApiGatewayService;
import com.example.boilerplate.service.DataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataServiceImpl implements DataService {
    private final ApiGatewayService apiGatewayService;

    @Override
    public PersonDetailsResponse getData(PersonDetailsRequest request) {
        return apiGatewayService.getPersonDetails(request);
    }

}
