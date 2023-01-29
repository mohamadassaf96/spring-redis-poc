package com.example.boilerplate.controller;

import com.example.boilerplate.meta.annotations.ControllerTest;
import com.example.boilerplate.model.dtos.data.PersonDetailsRequest;
import com.example.boilerplate.model.dtos.data.PersonDetailsResponse;
import com.example.boilerplate.service.DataService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.boilerplate.util.Commons.buildHeader;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ControllerTest
@RequiredArgsConstructor
class DataControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DataService dataService;

    @Test
    void testShouldCallDataApiCorrectlyAndReturnData() throws Exception {
        when(dataService.getData(new PersonDetailsRequest().setId("1")))
                .thenReturn(new PersonDetailsResponse().setFirstName("Bob"));

        mockMvc.perform(get("/person-details")
                .headers(buildHeader())
                .param("id", "1")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Bob"));
    }
}
