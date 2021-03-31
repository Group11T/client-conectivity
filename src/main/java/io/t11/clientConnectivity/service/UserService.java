package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.dao.UserRepository;
import io.t11.clientConnectivity.dto.UserDto;
import io.t11.clientConnectivity.error.UserAlreadyExistException;
import io.t11.clientConnectivity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createNewUser(UserDto userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getEmailAddress())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmailAddress());
        }
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmailAddress(userDto.getEmailAddress());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setDOB(userDto.getDOB());

        return userRepository.save(user);
    }

    @Override
    public double getUserBalance(User user) {
        return user.getBalance();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmailAddress(email);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @Override
    public User subtractFromUserBalance(User user, double amount) {
        double newBalance = user.getBalance() - amount;
        user.setBalance(newBalance);
        return userRepository.save( user );
    }

    private boolean emailExists(final String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress) != null;
    }
}

