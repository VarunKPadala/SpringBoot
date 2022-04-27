package com.springboot.blog.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.blog.entity.Role;
import com.springboot.blog.entity.User;
import com.springboot.blog.repository.UserRepository;

@Service
/*
 * Custom User Details Service class implements User Details Service interface
 * which loads user-specific data. It is used throughout the framework as a user
 * DAO and is the strategy used by the DaoAuthenticationProvider. The interface
 * requires only one read-only method, which simplifies support for new
 * data-access strategies.
 */
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	/*
	 * Locates the user based on the parameter passed i.e., here username or email
	 */
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		System.out.println(
				"\n\n############### Inside the overridden CustomUserDetailsService.loadUserByUsername() method ###############\n");

		/*
		 * find the user in database based on the user name or email entered If the user
		 * doesn't exist, then it throws an exception
		 */
		User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with Username or Email: " + usernameOrEmail));

		System.out.println("\n\n###############\n UserName: " + user.getEmail() + "\n Password: " + user.getPassword()
				+ "\n UsernameOrEmail: " + usernameOrEmail + "\n user: " + user + "\n###############\n");

		/*
		 * Sets the User with the authorities that should be granted to the caller if
		 * they presented the correct user name and password and the user is enabled
		 */
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));

	}

	/*
	 * User defined method, takes set of roles and map them to
	 * SimpleGrantedAuthority
	 */
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
		System.out.println(
				"\n\n############### Inside the CustomUserDetailsService.mapRolesToAuthorities() method ###############\n\n");
		System.out.println("\n###############\n\n");
		roles.forEach(role -> System.out.println(" role Id: " + role.getId() + "\n role name: " + role.getName()));
		System.out.println("\n\n###############\n\n");
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}