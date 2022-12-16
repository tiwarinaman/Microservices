package com.microservice.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private Integer id;
    @Size(min = 2, message = "Name should at least 2 letter")
    @JsonProperty("user_name")
    private String name;

    @Past(message = "DOB should be in the past")
    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;



}
