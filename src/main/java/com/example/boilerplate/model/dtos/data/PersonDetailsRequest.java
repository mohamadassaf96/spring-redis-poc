package com.example.boilerplate.model.dtos.data;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class PersonDetailsRequest {
    @NotBlank(message = "it cannot be blank")
    private String id;
}
