package io.t11.clientConnectivity.service;

import io.t11.clientConnectivity.dao.UserRepository;
import io.t11.clientConnectivity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;


public class SecurityUserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        try {
            final User user = userRepository.findByEmailAddress(emailAddress);
            if (user == null) {
                throw new UsernameNotFoundException("No user found with username: " + emailAddress);
            }

            return new org.springframework.security.core.userdetails.User(user.getEmailAddress(), user.getPassword(), new ArrayList<>());
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }



//        return new org.springframework.security.core.userdetails.User("admin","password",new ArrayList<>());
    }
}
