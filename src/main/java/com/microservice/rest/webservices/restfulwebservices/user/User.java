package com.microservice.rest.webservices.restfulwebservices.user;

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
    private String name;

    @Past(message = "DOB should be in the past")
    private LocalDate dateOfBirth;



}
