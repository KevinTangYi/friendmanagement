package com.test.service;

import com.test.model.User;
import com.test.model.dto.FriendsCreationDTO;
import com.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Bootstrap {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    @PostConstruct
    public void init() {

        userRepository.save(userService.toUser(null,"Harry","harry@gmail.com","123456",null));
        userRepository.save(userService.toUser(null,"Peter","peter@gmail.com","123456",null));
        userRepository.save(userService.toUser(null,"Sara","sara@gmail.com","123456",null));
        userRepository.save(userService.toUser(null,"Andy","andy@example.com","123456",null));
        userRepository.save(userService.toUser(null,"John","john@example.com","123456",null));
        FriendsCreationDTO newCreation= new FriendsCreationDTO();
        newCreation.setFriends(new String[]{"harry@gmail.com","john@example.com"});
        friendService.connectFriends(newCreation);
    }
}
