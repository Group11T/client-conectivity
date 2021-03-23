package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.dto.UserDto;
import io.t11.clientConnectivity.error.UserAlreadyExistException;
import io.t11.clientConnectivity.model.Portfolio;
import io.t11.clientConnectivity.model.User;

import java.util.List;

public interface IUserService {
    User createNewUser(UserDto userDto) throws UserAlreadyExistException;
    Portfolio getUserPortfolio(User user);
    List<User> returnAllUsers();
    User findUserById(Long id);
}
