package io.t11.clientConnectivity.controller;

import io.t11.clientConnectivity.dto.UserDto;
import io.t11.clientConnectivity.model.Portfolio;
import io.t11.clientConnectivity.model.User;

import io.t11.clientConnectivity.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    //@CrossOrigin
    public User registerNewUser(@RequestBody UserDto userDto){

       // return user;
        System.out.println(userDto);
        return userService.createNewUser(userDto);  // so You Know something must be done here
    }

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.returnAllUsers();
    }

    @GetMapping("/portfolio/{id}")
    public Portfolio getUsersPortfolio(@PathVariable Long id){
        User user = userService.findUserById(id);
        return userService.getUserPortfolio(user);
    }

    public String loginUser(){
        return ""; // so You Know something must be done here
    }

}
