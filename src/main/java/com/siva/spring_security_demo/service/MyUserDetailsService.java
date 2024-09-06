package com.siva.spring_security_demo.service;

import com.siva.spring_security_demo.model.User;
import com.siva.spring_security_demo.model.UserPrinciple;
import com.siva.spring_security_demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByName(username);
        if (Objects.isNull(user)) {
            log.info("incorrect user details not authorized:");
            throw new UsernameNotFoundException(" user not found");
        }

        log.info("User Authenticated");
        return new UserPrinciple(user);
    }
}
