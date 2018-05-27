package com.test.service;

import com.test.model.User;
import com.test.model.dto.FriendsDTO;
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

    public boolean connectFriends(FriendsDTO newCreation){

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

    public List<String> commonFriendsList(FriendsDTO emails){
        List<String> commonFriendsList = new ArrayList<>();
        Optional<User> user1 = userRepository.findUserByEmail(emails.getFriends()[0]);
        Optional<User> user2 = userRepository.findUserByEmail(emails.getFriends()[1]);

        if(user1.isPresent() && user2.isPresent()){
            List<String> friendsList1 = user1.get().getFriends();
            List<String> friendsList2 = user2.get().getFriends();

            if(!friendsList1.isEmpty() && !friendsList2.isEmpty()) {
                friendsList1.forEach(friend -> {
                    if (friendsList2.contains(friend))
                        commonFriendsList.add(friend);
                });

                return commonFriendsList;
            }
        }

        return null;
    }
}
