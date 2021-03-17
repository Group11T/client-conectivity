package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.User;
import io.t11.clientConnectivity.dao.UserRepository;
import io.t11.clientConnectivity.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createNewUser(UserDto userDto){
       User user = new User();
       user.setFirstName(userDto.getFirstName());
       user.setLastName(userDto.getLastName());
       user.setEmailAddress(userDto.getEmailAddress());
       user.setPassword(userDto.getPassword());
       user.setDOB(userDto.getDOB());

       return userRepository.save(user);
    }

    public String authenticateUSer(){
        return ""; // so You Know something must be done here
    }

}