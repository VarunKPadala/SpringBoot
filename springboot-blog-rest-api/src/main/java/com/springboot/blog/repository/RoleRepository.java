package com.springboot.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	// Finds the role based on the rolename. Used to assign role to a new user
	Optional<Role> findByName(String name);

}
