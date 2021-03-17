package io.t11.clientConnectivity.controller;

import io.t11.clientConnectivity.model.User;
import io.t11.clientConnectivity.dto.UserDto;
import io.t11.clientConnectivity.service.IUserService;
import io.t11.clientConnectivity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User registerNewUser(UserDto userDto){
        //Annotate @RequestBody UserDto userDto
        return userService.createNewUser(userDto);  // so You Know something must be done here
    }

    public String loginUser(){
        return ""; // so You Know something must be done here
    }

}
