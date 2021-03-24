package io.t11.clientConnectivity.service;

import com.sun.istack.NotNull;
import io.t11.clientConnectivity.dao.PortfolioRepository;
import io.t11.clientConnectivity.dao.UserRepository;
import io.t11.clientConnectivity.dto.UserDto;
import io.t11.clientConnectivity.error.UserAlreadyExistException;
import io.t11.clientConnectivity.model.Portfolio;
import io.t11.clientConnectivity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PortfolioRepository portfolioRepository;


    @Override
    public User createNewUser(UserDto userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getEmailAddress())) {
            throw new UserAlreadyExistException("There is an account with that email address: " + userDto.getEmailAddress());
        }
        User user = new User();
        Portfolio portfolio = new Portfolio("portfolio1");

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmailAddress(userDto.getEmailAddress());
        user.setPassword(userDto.getPassword());
        user.setDOB(userDto.getDOB());

//        user.setPortfolio(portfolio);
        System.out.println(userDto);

        portfolioRepository.save(portfolio);
        return userRepository.save(user);
    }

    @Override
    public Portfolio getUserPortfolio(User user) {
        return new Portfolio("k");
    }

    @Override
    public List<User> returnAllUsers(){
        return userRepository.findAll();

    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).get();
    }

//    public String authenticateUSer(){
//        return ""; // so You Know something must be done here
//    }

    private boolean emailExists(final String emailAddress) {
        return userRepository.findByEmailAddress(emailAddress) != null;
    }
}

