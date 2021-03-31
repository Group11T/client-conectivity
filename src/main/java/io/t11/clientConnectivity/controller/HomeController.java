package io.t11.clientConnectivity.controller;

import io.t11.clientConnectivity.dao.UserRepository;
import io.t11.clientConnectivity.model.JwtRequest;
import io.t11.clientConnectivity.model.JwtResponse;
import io.t11.clientConnectivity.model.User;
import io.t11.clientConnectivity.service.UserService;
import io.t11.clientConnectivity.utility.JWTUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        return "Welcome to successful login";
    }

    @PostMapping("/authenticate")
    //@CrossOrigin
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
        System.out.println(jwtRequest.getUsername());
        System.out.println(jwtRequest.getPassword());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(

                            //jwtRequest.getEmailAddress(),
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()

                    )
            );
        } catch (BadCredentialsException e){
            throw  new Exception("Invalid Credentials", e);
        }

        final UserDetails userDetails
                = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token = jwtUtility.generateToken(userDetails);

        return new JwtResponse(token);
    }

    @GetMapping("/try")
    public User fetchUser(){
        return userRepository.findById(4L).get();
    }
}
