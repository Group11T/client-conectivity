package io.t11.clientConnectivity.config;

import io.t11.clientConnectivity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/users/register*","/authenticate*").permitAll();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        final CustomAutenticationProvider authPorvider = new CustomAutenticationProvider();
        authPorvider.setUserDetailsService(userService);
        authPorvider.setPasswordEncoder(encoder());
        return authPorvider;

    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }
}
