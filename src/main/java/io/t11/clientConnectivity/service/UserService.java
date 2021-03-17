package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.error.UserAlreadyExistException;
import io.t11.clientConnectivity.model.User;
import io.t11.clientConnectivity.dao.UserRepository;
import io.t11.clientConnectivity.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    public User createNewUser(UserDto userDto){
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmail());
        }
       User user = new User();
       user.setFirstName(userDto.getFirstName());
       user.setLastName(userDto.getLastName());
       user.setEmail(userDto.getEmail());
       user.setPassword(userDto.getPassword());
       user.setDOB(userDto.getDOB());
       return userRepository.save(user);
    }

    public String authenticateUSer(){
        return ""; // so You Know something must be done here
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }
}