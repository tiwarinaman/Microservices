package com.microservice.rest.webservices.restfulwebservices.controller;

import com.microservice.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.microservice.rest.webservices.restfulwebservices.user.User;
import com.microservice.rest.webservices.restfulwebservices.user.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;

    @Autowired
    public UserResource(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> users(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User user(@PathVariable Integer id){


        User user = service.findUserById(id);

        if (null != user){
            return user;
        }else {
            throw new UserNotFoundException("User dose not exists!!!");
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = service.saveUser(user);
        if (null != savedUser){
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId())
                    .toUri();
            return ResponseEntity.created(location).build();
        }else{
            return null;
        }

    }

    @DeleteMapping("/users/{id}")
    public void removeUser(@PathVariable Integer id){
        service.deleteUser(id);
    }


}
