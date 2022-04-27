package com.springboot.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entity.User;

//Reposirtory for User Entity
public interface UserRepository extends JpaRepository<User, Long> {

	// Finds the User by email entered
	Optional<User> findByEmail(String email);

	// Finds User by User name or email entered. Used in Login Page
	Optional<User> findByUsernameOrEmail(String username, String email);

	// finds User by User name
	Optional<User> findByUsername(String username);

	// Checks that User already exists in the database based on User name entered
	// while sign up to check for duplicate entries
	Boolean existsByUsername(String username);

	// Checks that User already exists in the database based on email entered
	// while sign up to check for duplicate entries
	Boolean existsByEmail(String email);

}
