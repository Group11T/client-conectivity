package io.t11.clientConnectivity.controller;

import io.t11.clientConnectivity.User;
import io.t11.clientConnectivity.dto.UserDto;
import io.t11.clientConnectivity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @PostMapping("/register")
    public User registerNewUser(UserDto userDto){

        return userService.createNewUser(userDto);  // so You Know something must be done here
    }

    public String loginUser(){
        return ""; // so You Know something must be done here
    }

}
