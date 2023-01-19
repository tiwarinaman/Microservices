package com.microservice.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static Integer userCount = 0;

//    static {
//        users.add(new User(++userCount, "Adam", LocalDate.now().minusYears(30)));
//        users.add(new User(++userCount, "Eve", LocalDate.now().minusYears(25)));
//        users.add(new User(++userCount, "Jim", LocalDate.now().minusYears(20)));
//    }

    public List<User> findAll(){
        return users;
    }

    public User findUserById(Integer id){
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);

    }

    public User saveUser(User user){
        user.setId(++userCount);
        boolean add = users.add(user);
        if (add){
            System.out.println("User saved successfully");
            return user;
        }else{
            System.out.println("Failed to save the user");
            return null;
        }

    }

    public void deleteUser(Integer id){
        users.removeIf(user -> user.getId().equals(id));
    }

}
