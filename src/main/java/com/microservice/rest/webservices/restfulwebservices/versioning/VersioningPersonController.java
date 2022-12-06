package com.microservice.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
*Types of Versioning
* 1. URI Versioning (Twitter)
*   V1: http://localhost:8080/v1/person
*   V2: http://localhost:8080/v2/person
* 2. Request Param Versioning (Amazon)
*   V1: http://localhost:8080/person?version=1
*   V2: http://localhost:8080/person?version=2
* 3. Header Versioning (Microsoft)
*   V1: http://localhost:8080/person/header
*   V2: http://localhost:8080/person/header
* 4. Content Negotiation Versioning (GitHub)
*   V1: http://localhost:8080/person/accept
*   V2: http://localhost:8080/person/accept
*/

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersionV1 getFirstVersionOfPerson(){
        return new PersionV1("Prabhakar Mishra");
    }

    @GetMapping("/v2/person")
    public PersionV2 getSecondVersionOfPerson(){
        return new PersionV2(new Name("Prabhakar", "Mishra"));
    }

    @GetMapping(path = "/person", params = "version=1")
    public PersionV1 getFirstVersionOfPersonRequestParameter(){
        return new PersionV1("Prabhakar Mishra");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersionV2 getSecondVersionOfPersonRequestParameter(){
        return new PersionV2(new Name("Prabhakar","Mishra"));
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersionV1 getFirstVersionOfPersonRequestHeader(){
        return new PersionV1("Prabhakar Mishra");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersionV2 getSecondVersionOfPersonRequestHeader(){
        return new PersionV2(new Name("Prabhakar","Mishra"));
    }

    @GetMapping(path = "/person/accept", produces =  "application/vnd.company.app-v1+json")
    public PersionV1 getFirstVersionOfPersonAcceptHeader(){
        return new PersionV1("Prabhakar Mishra");
    }

    @GetMapping(path = "/person/accept", produces =  "application/vnd.company.app-v2+json")
    public PersionV2 getSecondVersionOfPersonAcceptHeader(){
        return new PersionV2(new Name("Prabhakar","Mishra"));
    }



}
