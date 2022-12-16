package com.microservice.rest.webservices.restfulwebservices.filtering.utility;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

public class DynamicFilteringImplementation {


    public static MappingJacksonValue applyFilter(Object object, String field1,String field2){

        //Implementing Custom/Dynamic Filtering on Bean Using Mapping Jackson Value.
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(object);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(field1,field2);
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;

    }
}
