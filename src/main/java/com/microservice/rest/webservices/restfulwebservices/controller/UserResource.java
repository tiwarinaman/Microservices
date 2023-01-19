package com.microservice.rest.webservices.restfulwebservices.controller;

import com.microservice.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.microservice.rest.webservices.restfulwebservices.user.Post;
import com.microservice.rest.webservices.restfulwebservices.user.User;
import com.microservice.rest.webservices.restfulwebservices.user.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserResource {

    private final UserDaoService service;

    @Autowired
    public UserResource(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> users(){
        return service.findAll();
    }


    /**
     * In this method we are using HATEOAS(Hypermedia as the Engine of Application State) concept
     * Website allows to see data and perform actions using links using HATEOAS.
     * Entity Model used to wrap the data without making changes in the bean class.
     * WebMVCLinkBuilder used to create the link.
     * @param id id
     * @return User wrapped with Entity Model.
     */
    @GetMapping("/users/{id}")
    public EntityModel<User> user(@PathVariable Integer id){


        User user = service.findUserById(id);

        if (null != user){
            EntityModel<User> entityModel = EntityModel.of(user);
            WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).users());
            entityModel.add(link.withRel("all-users"));

            return entityModel;
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
