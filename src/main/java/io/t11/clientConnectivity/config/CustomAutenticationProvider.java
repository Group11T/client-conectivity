package io.t11.clientConnectivity.config;

import io.t11.clientConnectivity.dao.UserRepository;
import io.t11.clientConnectivity.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAutenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        final User user = userRepository.findByEmailAddress(auth.getName());
        if(user == null){
            throw new BadCredentialsException("Invalid User name or password");
        }
        final Authentication result = super.authenticate(auth);

        return new UsernamePasswordAuthenticationToken(user,result.getCredentials(),result.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
