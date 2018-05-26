package com.test.controller;

import com.test.model.User;
import com.test.model.dto.BooleanResponseDTO;
import com.test.model.dto.EmailDTO;
import com.test.model.dto.FriendsCreationDTO;
import com.test.model.dto.FriendsListDTO;
import com.test.repository.UserRepository;
import com.test.service.FriendService;
import com.test.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/friend")
public class ManageFriendController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FriendService friendService;

    @ApiOperation(nickname = "createFriends", value = "Add a friend connection", response = BooleanResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Friends Connection successfully created"),
            @ApiResponse(code = 400, message = "Connection already exists")
    })
    @RequestMapping(value = "/create",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    ResponseEntity<BooleanResponseDTO> create(@RequestBody FriendsCreationDTO friends) {
        BooleanResponseDTO resp = new BooleanResponseDTO();
        Boolean success = friendService.connectFriends(friends);
        resp.setSuccess(success);

        return new ResponseEntity<>(resp,HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get all friends someone has", response = FriendsListDTO.class, responseContainer = "list")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Friends list retrieved"),
    })

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    ResponseEntity<FriendsListDTO> list(@RequestBody EmailDTO email) {
        FriendsListDTO friendListDTO = new FriendsListDTO();
        List<String> friendsList = friendService.friendsList(email.getEmail());

        if(friendsList != null){
            friendListDTO.setSuccess(true);
            friendListDTO.setFriends(friendsList);
            friendListDTO.setCount(friendsList.size());
        }else{
            friendListDTO.setSuccess(false);
        }
        //log.info("found " + productList.size() + " products");
        return new ResponseEntity<FriendsListDTO>(friendListDTO, HttpStatus.OK);
    }
}
