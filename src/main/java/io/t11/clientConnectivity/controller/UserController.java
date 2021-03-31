package io.t11.clientConnectivity.controller;

import io.t11.clientConnectivity.dto.UserDto;
import io.t11.clientConnectivity.model.User;
import io.t11.clientConnectivity.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private static Logger logger = LoggerFactory.getLogger((UserController.class));

    @Autowired
    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/register")
    public User registerNewUser(@RequestBody UserDto userDto){

        logger.info("Registering new user");
        return userService.createNewUser(userDto);
    }

    @GetMapping("/users/balance")
    public double checkUserBalance(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        final User user = userService.findUserByEmail(userDetails.getUsername());

        return userService.getUserBalance(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

}
