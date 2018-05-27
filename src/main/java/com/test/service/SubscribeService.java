package com.test.service;

import com.test.model.User;
import com.test.model.dto.FriendsDTO;
import com.test.model.dto.RecipientsRequestDTO;
import com.test.model.dto.SubscribeDTO;
import com.test.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class SubscribeService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public boolean connectSubscribe(SubscribeDTO newCreation){

        String requestorEmail = newCreation.getRequestor();
        String targetEmail = newCreation.getTarget();

        Optional<User> requestor = userRepository.findUserByEmail(requestorEmail);
        Optional<User> target = userRepository.findUserByEmail(targetEmail);

        if(requestor.isPresent() && target.isPresent()){
            requestor.get().getSubscribes().add(targetEmail);
            target.get().getFollowers().add(requestorEmail);
            userRepository.save(requestor.get());
            userRepository.save(target.get());

            return true;
        }

        return false;
    }

    @Transactional
    public boolean blockUpdate(SubscribeDTO newCreation){

        String requestorEmail = newCreation.getRequestor();
        String targetEmail = newCreation.getTarget();

        Optional<User> requestor = userRepository.findUserByEmail(requestorEmail);
        Optional<User> target = userRepository.findUserByEmail(targetEmail);

        if(requestor.isPresent() && target.isPresent()){
            requestor.get().getBlock().add(targetEmail);
            userRepository.save(requestor.get());
            return true;
        }

        return false;
    }

    @Transactional
    public List<String> emailsList(RecipientsRequestDTO requestDTO){
        String text = requestDTO.getText();
        String senderEmail = requestDTO.getSender();

        List<String> RecipientEmails = new ArrayList<>();
        Pattern pattern = Pattern.compile("@([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})");

        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String mentionedEmail = matcher.group().substring(1);
            Optional<User> user = userRepository.findUserByEmail(mentionedEmail);
            if (user.isPresent())
                RecipientEmails.add(mentionedEmail);
        }

        Optional<User> user = userRepository.findUserByEmail(requestDTO.getSender());
        if(user.isPresent()){
            List<String> friendsList = user.get().getFriends();
            friendsList.forEach(friend ->{
                List<String> blockList = userRepository.findUserByEmail(friend).get().getBlock();
                if (!blockList.contains(senderEmail) && !RecipientEmails.contains(friend))
                    RecipientEmails.add(friend);
            });

            List<String> followersList = user.get().getFollowers();
            followersList.forEach(follower->{
                List<String> blockList = userRepository.findUserByEmail(follower).get().getBlock();
                if(!blockList.contains(senderEmail) && !RecipientEmails.contains(follower))
                    RecipientEmails.add(follower);
            });
        }

        return RecipientEmails;
    }

}
