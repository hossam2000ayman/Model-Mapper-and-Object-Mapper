package com.example.testmapper.controller;

import com.example.testmapper.dto.UserDto;
import com.example.testmapper.model.Device;
import com.example.testmapper.model.User;
import com.example.testmapper.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("api/v1")
public class MainRestController {


    @Autowired
    private UserService userService;

    @GetMapping("get/user")
    public ResponseEntity<UserDto> getUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUser());
    }


    @GetMapping("save/user")
    public ResponseEntity<User> saveUser() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.saveUser());
    }


    @PostMapping("convertJsonToEntity")
    public ResponseEntity<Device> convertJSONBackToEntity() throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.convertJSONBackToEntity());
    }


    @PostMapping("convertJsonArrayBackToArrayOfEntities")
    public ResponseEntity<Device[]> convertJsonArrayBackToArrayOfEntities() throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.convertJsonArrayBackToArrayOfEntities());
    }

    @PostMapping("convertJsonArrayBackToListOfEntity")
    public ResponseEntity<List<Device>> convertJsonArrayBackToListOfEntity() throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.convertJsonArrayBackToListOfEntity());
    }


    @PostMapping("convertJsonBackToMapOfEntity")
    public ResponseEntity<Map<String, String>> convertJsonBackToMapOfEntity() throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.convertJsonArrayBackToMapOfEntity());
    }


    @PostMapping("convertJavaObjectToJsonString")
    public ResponseEntity<String> convertJavaObjectToJsonString() throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.convertJavaObjectToJsonString());
    }

    @PostMapping("convertObjectToJsonUsingMixins")
    public ResponseEntity<String> convertObjectToJsonUsingMixins() throws JsonProcessingException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.convertObjectToJsonUsingMixins());
    }

}
