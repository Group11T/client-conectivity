package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.dto.UserDto;
import io.t11.clientConnectivity.error.UserAlreadyExistException;
import io.t11.clientConnectivity.model.User;

public interface IUserService {

    User createNewUser(UserDto userDto) throws UserAlreadyExistException;
}
