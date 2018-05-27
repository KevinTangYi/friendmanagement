package com.test.controller;

import com.test.model.dto.*;
import com.test.repository.UserRepository;
import com.test.service.SubscribeService;
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

@RestController
@RequestMapping(value = "/api/subscribe")
public class ManageSubscribeController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SubscribeService subsscribeService;

    @ApiOperation(nickname = "createSubscribe", value = "Create a subscribe", response = BooleanResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Subscribe Connection successfully created"),
            @ApiResponse(code = 400, message = "Connection already exists")
    })
    @RequestMapping(value = "/create",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    ResponseEntity<BooleanResponseDTO> create(@RequestBody SubscribeDTO newCreation) {
        BooleanResponseDTO resp = new BooleanResponseDTO();
        Boolean success = subsscribeService.connectSubscribe(newCreation);
        resp.setSuccess(success);

        return new ResponseEntity<>(resp,HttpStatus.CREATED);
    }

    @ApiOperation(nickname = "Block update", value = "Block update from a email address", response = BooleanResponseDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Block update successfully"),
            @ApiResponse(code = 400, message = "Blocking already exists")
    })
    @RequestMapping(value = "/block",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    ResponseEntity<BooleanResponseDTO> block(@RequestBody SubscribeDTO newCreation) {
        BooleanResponseDTO resp = new BooleanResponseDTO();
        Boolean success = subsscribeService.blockUpdate(newCreation);
        resp.setSuccess(success);

        return new ResponseEntity<>(resp,HttpStatus.OK);
    }

    @ApiOperation(value = "Get all email address can receive update", response = RecipientsListDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Recipients list retrieved"),
    })

    @RequestMapping(
            value = "/get",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json")
    ResponseEntity<RecipientsListDTO> list(@RequestBody RecipientsRequestDTO recipientsRequest) {
        RecipientsListDTO recipientsListDTO = new RecipientsListDTO();
        List<String> recipientsList = subsscribeService.emailsList(recipientsRequest);


        recipientsListDTO.setSuccess(true);
        recipientsListDTO.setRecipients(recipientsList);



        return new ResponseEntity<RecipientsListDTO>(recipientsListDTO, HttpStatus.OK);
    }

}
