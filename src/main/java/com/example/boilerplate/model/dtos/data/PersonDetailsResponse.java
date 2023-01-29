package com.example.boilerplate.model.dtos.data;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class PersonDetailsResponse implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
