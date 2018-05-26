package com.test.service;

import com.test.model.User;
import com.test.model.dto.FriendsCreationDTO;
import com.test.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FriendService {
    @Autowired
    private UserRepository userRepository;

    public boolean connectFriends(FriendsCreationDTO newCreation){

        String email1 = newCreation.getFriends()[0];
        String email2 = newCreation.getFriends()[1];

        Optional<User> user1 = userRepository.findUserByEmail(email1);
        Optional<User> user2 = userRepository.findUserByEmail(email2);

        if(user1.isPresent() && user2.isPresent()){
            User userA = user1.get();
            User userB = user2.get();
            userA.getFriends().add(userB.getEmail());
            userB.getFriends().add(userA.getEmail());
            userRepository.save(userA);
            userRepository.save(userB);

            return true;
        }

        return false;
    }

    public List<String> friendsList(String email){
        Optional<User> user = userRepository.findUserByEmail(email);

        if(user.isPresent()){
            return user.get().getFriends();
        }

        return null;
    }
}
