package com.example.boilerplate.controller;

import com.example.boilerplate.model.dtos.data.PersonDetailsRequest;
import com.example.boilerplate.model.dtos.data.PersonDetailsResponse;
import com.example.boilerplate.service.DataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DataController {
    private final DataService dataService;

    @GetMapping("/person-details")
    public ResponseEntity<PersonDetailsResponse> getPersonDetails(@Valid PersonDetailsRequest request) {
        PersonDetailsResponse response = dataService.getData(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
