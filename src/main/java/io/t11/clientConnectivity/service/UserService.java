package io.t11.clientConnectivity.service;

import com.sun.istack.NotNull;
import io.t11.clientConnectivity.model.User;
import io.t11.clientConnectivity.dao.UserRepository;
import io.t11.clientConnectivity.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createNewUser(@NotNull User user){
       //User user = new User();

      // user.setFirstName(userDto.getFirstName());
//       user.setLastName(userDto.getLastName());
//       user.setEmailAddress(userDto.getEmailAddress());
//       user.setPassword(userDto.getPassword());
//       user.setDOB(userDto.getDOB());

       //return user;
       return userRepository.save(user);
    }

    public List<User> returnAllUsers(){

        return userRepository.findAll();

    }

    public String authenticateUSer(){
        return ""; // so You Know something must be done here
    }

}