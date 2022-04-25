package com.springboot.blog.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.entity.Role;
import com.springboot.blog.entity.User;
import com.springboot.blog.payload.LoginDto;
import com.springboot.blog.payload.SignUpDto;
import com.springboot.blog.repository.RoleRepository;
import com.springboot.blog.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/signin")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
		System.out.println("\n\n############### Inside the AuthController.authenticateUser() method ###############\n");

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new ResponseEntity<>("User Signed-in Successfully!", HttpStatus.OK);
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
		System.out.println("\n\n############### Inside the AuthController.registerUser() method ###############\n");

		// add check for username exists in the database
		if (userRepository.existsByUsername(signUpDto.getUsername())) {
			System.out.println("\n\n############### Username is already used. So cannot signup! ###############\n");
			return new ResponseEntity<>("Username is already used!", HttpStatus.BAD_REQUEST);
		}

		// add check for email exists in the database
		if (userRepository.existsByEmail(signUpDto.getEmail())) {
			return new ResponseEntity<>("Email is already registered!", HttpStatus.BAD_REQUEST);
		}

		System.out.println("\n\n############### UsernameOrEmail doesn't exist. Good go with signup ###############\n");

		// create user object, set details from dto to entity, encode the password
		// before setting
		User user = new User();
		user.setName(signUpDto.getName());
		user.setUsername(signUpDto.getUsername());
		user.setEmail(signUpDto.getEmail());
		user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

		System.out.println("\n\n############### " + user + " ###############\n");

		// Added a role to the user
		Role roles = roleRepository.findByName("ROLE_ADMIN").get();
		user.setRoles(Collections.singleton(roles));

		System.out.println("\n\n############### role id: " + roles.getId() + "\t role name: " + roles.getName()
				+ " ###############\n");

		// save the user top the database
		userRepository.save(user);

		System.out.println("\n\n############### User Registration is Successfull! ###############\n");

		return new ResponseEntity<>("User Registered Successfully!", HttpStatus.OK);
	}

}