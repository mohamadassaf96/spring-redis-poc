package com.example.boilerplate.service;

import com.example.boilerplate.meta.annotations.TestAnnotation;
import com.example.boilerplate.model.dtos.data.PersonDetailsRequest;
import com.example.boilerplate.model.dtos.data.PersonDetailsResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@TestAnnotation
class DataServiceImplTests {
    @Autowired
    private DataService dataService;
    @MockBean
    private ApiGatewayService apiGatewayService;

    @Test
    void testShouldKnowHowToGetDataFromApiGatewayAndReturnThem() {
        when(apiGatewayService.getPersonDetails(new PersonDetailsRequest().setId("1")))
                .thenReturn(new PersonDetailsResponse().setFirstName("firstName"));
        PersonDetailsResponse personDetailsResponse = dataService.getData(new PersonDetailsRequest().setId("1"));
        assertEquals("firstName", personDetailsResponse.getFirstName());
    }
}
