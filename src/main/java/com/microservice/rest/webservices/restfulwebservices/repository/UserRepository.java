package com.microservice.rest.webservices.restfulwebservices.repository;

import com.microservice.rest.webservices.restfulwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Integer> {

}
