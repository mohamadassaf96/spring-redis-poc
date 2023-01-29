package com.example.boilerplate.service;

import com.example.boilerplate.model.dtos.data.PersonDetailsRequest;
import com.example.boilerplate.model.dtos.data.PersonDetailsResponse;

public interface ApiGatewayService {
    PersonDetailsResponse getPersonDetails(PersonDetailsRequest request);
}
