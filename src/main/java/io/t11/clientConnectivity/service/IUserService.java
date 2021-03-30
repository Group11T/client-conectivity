package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.dto.UserDto;
import io.t11.clientConnectivity.error.UserAlreadyExistException;
import io.t11.clientConnectivity.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    User createNewUser(UserDto userDto) throws UserAlreadyExistException;

    double getUserBalance(User user);

    User findUserByEmail(String email);

    Optional<User> getUserById(Long id);

    List<User> getAllUsers();
}
