package com.microservice.rest.webservices.restfulwebservices.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class TestController {

    private MessageSource messageSource;

    public TestController (MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @GetMapping("/hello-world")
    public String helloWorld(){
        return "Hello World";
    }

    /*
    *Internationalization Implemented
    */
    @GetMapping("/hello-world-internationalization")
    public String helloWorldInternationalization(){

        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",
                null, "Default Message", locale);

    }

}
