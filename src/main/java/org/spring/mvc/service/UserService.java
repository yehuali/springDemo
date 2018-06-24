package org.spring.mvc.service;

import org.spring.mvc.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

@Service
public class UserService {


    public User createUser(User user){
        System.out.println(user.getPassword() +","+user.getRealName() +","+user.getUserName());
        return user;
    }

    public User getUserById(String userId){
        System.out.println("userId:"+userId);
        return new User();
    }

}
