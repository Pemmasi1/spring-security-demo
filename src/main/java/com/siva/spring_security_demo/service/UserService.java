package com.siva.spring_security_demo.service;

import com.siva.spring_security_demo.dto.UserDto;
import com.siva.spring_security_demo.model.User;
import com.siva.spring_security_demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	public void saveUser(UserDto user) {
		User user1 = new User();
		user1.setName(user.getName());
		user1.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user1);
	}

	public String verify(UserDto user) {
		Authentication authentication = authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword()));

		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(user.getName());
		}
		throw new UsernameNotFoundException(" user not validated");
	}

}
