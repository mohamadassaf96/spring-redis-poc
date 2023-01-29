package com.example.boilerplate.service;

import com.example.boilerplate.model.dtos.data.PersonDetailsRequest;
import com.example.boilerplate.model.dtos.data.PersonDetailsResponse;

public interface DataService {
    PersonDetailsResponse getData(PersonDetailsRequest request);
}
