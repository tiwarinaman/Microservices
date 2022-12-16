package com.microservice.rest.webservices.restfulwebservices.filtering;

import com.microservice.rest.webservices.restfulwebservices.filtering.utility.DynamicFilteringImplementation;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue filtering() {
        SomeUser someUser = new SomeUser("value1", "value2", "value3");

        //Implementing Custom/Dynamic Filtering on Bean Using Mapping Jackson Value.
        MappingJacksonValue mappingJacksonValue =
                DynamicFilteringImplementation.applyFilter(someUser, "field1", "field2");

        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList() {
        List<SomeUser> ts = Arrays.asList(new SomeUser("value1", "value2", "value3"),
                new SomeUser("value1", "value2", "value3"));

        //Implementing Custom/Dynamic Filtering on Bean Using Mapping Jackson Value.
        MappingJacksonValue mappingJacksonValue =
                DynamicFilteringImplementation.applyFilter(ts, "field2", "field3");

        return mappingJacksonValue;
    }


}
