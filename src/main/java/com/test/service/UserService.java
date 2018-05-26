package com.test.service;

import com.test.model.User;
import com.test.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User toUser(Long id, String name, String email, String passWord, ArrayList<String> friends){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passWord);
        user.setFriends(friends);

        return user;
    }

}
