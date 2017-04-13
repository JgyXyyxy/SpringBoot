package com.sdu.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by J on 2017/2/26.
 */

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//    @Bean
//    UserDetailsService customUserServiceImpl(){
//        return new CustomUserServiceImpl();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .failureUrl("/login?error")
//                    .permitAll()
//                .and()
//                .logout()
//                .permitAll();
                .antMatchers("/")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
//        auth.userDetailsService(customUserServiceImpl());
    }

}

