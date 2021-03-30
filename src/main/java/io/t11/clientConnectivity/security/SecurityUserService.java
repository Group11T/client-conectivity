package io.t11.clientConnectivity.security;

import io.t11.clientConnectivity.dao.UserRepository;
import io.t11.clientConnectivity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
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
    }
}
