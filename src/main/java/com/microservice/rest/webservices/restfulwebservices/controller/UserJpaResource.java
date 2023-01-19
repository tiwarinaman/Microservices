package com.microservice.rest.webservices.restfulwebservices.controller;

import com.microservice.rest.webservices.restfulwebservices.exception.PostNotFoundException;
import com.microservice.rest.webservices.restfulwebservices.exception.UserNotFoundException;
import com.microservice.rest.webservices.restfulwebservices.repository.PostRepository;
import com.microservice.rest.webservices.restfulwebservices.repository.UserRepository;
import com.microservice.rest.webservices.restfulwebservices.user.Post;
import com.microservice.rest.webservices.restfulwebservices.user.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {

//    private final UserDaoService service;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public UserJpaResource(UserRepository userRepository, PostRepository postRepository){

        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/jpa/users")
    public List<User> users(){
        return userRepository.findAll();
    }


    /**
     * In this method we are using HATEOAS(Hypermedia as the Engine of Application State) concept
     * Website allows to see data and perform actions using links using HATEOAS.
     * Entity Model used to wrap the data without making changes in the bean class.
     * WebMVCLinkBuilder used to create the link.
     * @param id id
     * @return User wrapped with Entity Model.
     */
    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> user(@PathVariable Integer id){


        Optional<User> user = userRepository.findById(id);

        if (!user.isEmpty()){
            EntityModel<User> entityModel = EntityModel.of(user.get());
            WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).users());
            entityModel.add(link.withRel("all-users"));

            return entityModel;
        }else {
            throw new UserNotFoundException("User dose not exists!!!");
        }
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
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

    @DeleteMapping("/jpa/users/{id}")
    public void removeUser(@PathVariable Integer id){
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/post")
    public List<Post> retrievePost(@PathVariable Integer id){
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new UserNotFoundException("User dose not exists!!!");
        }
        List<Post> posts = user.get().getPosts();
        return posts;

    }

    @PostMapping("/jpa/users/{id}/post")
    public ResponseEntity<Post> createPostForUser(@PathVariable Integer id, @Valid @RequestBody Post post){
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()){
            throw new UserNotFoundException("User dose not exists!!!");
        }

        post.setUser(user.get());
        Post savedPost = postRepository.save(post);


        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/jpa/post/{id}")
    public Post getPostById(@PathVariable Integer id){

        Optional<Post> post = postRepository.findById(id);

        if (post.isEmpty()){
            throw new PostNotFoundException("Post dose not exists!!");
        }

        return post.get();

    }

}
