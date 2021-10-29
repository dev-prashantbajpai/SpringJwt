package com.springjwt.springjwt.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        if (userName.equals("Prashant"))
        {
            return new User("Prashant", "Prashant123", new ArrayList<>());
        }
        else{
            throw new UsernameNotFoundException("User not found !!");
        }

    }
}
