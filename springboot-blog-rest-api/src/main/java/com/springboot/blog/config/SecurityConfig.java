package com.springboot.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.springboot.blog.security.CustomUserDetailsService;
import com.springboot.blog.security.JwtAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	// To utilize User-Specific data for AuthenticationManagerBuilder
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint authenticationEntryPoint;

	@Bean
	// To encode the password
	PasswordEncoder passwordEncoder() {
		System.out.println(
				"\n\n############### Entered the overridden SecurityConfig.passwordEncoder() method ###############");
		return new BCryptPasswordEncoder();
	}

	@Override
	// We override this method to configure the HttpSecurity as we wish
	protected void configure(HttpSecurity http) throws Exception {

		System.out.println(
				"\n\n############### Entered the overridden SecurityConfig.configure(HttpSecurity) method ###############");

		http.csrf().disable().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/api/**").permitAll().antMatchers("/api/auth/**").permitAll().anyRequest()
				.authenticated()/* .and().httpBasic() */;

		System.out.println(
				"\n\n############### Exiting the overridden SecurityConfig.configure(HttpSecurity) method ###############\n\n");

	}

	@Override
	// To enable authentication using the details from Custom User Details
	// Service and an encoded password
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		System.out.println(
				"\n\n############### Entered the overridden SecurityConfig.configure(AuthenticationManagerBuilder) method ###############");

		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

		System.out.println(
				"\n\n############### Exiting the overridden SecurityConfig.configure(AuthenticationManagerBuilder) method ###############");

	}

	@Override
	@Bean
	// Overridden this method to expose the AuthenticationManager from
	// configure(AuthenticationManagerBuilder) to be exposed as a Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		System.out.println(
				"\n\n############### Inside the overridden SecurityConfig.authenticationManagerBean() method ###############\n");
		return super.authenticationManagerBean();
	}

//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//
//		UserDetails varun = User.builder().username("varun").password(passwordEncoder().encode("password"))
//				.roles("USER").build();
//		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
//				.build();
//
//		return new InMemoryUserDetailsManager(varun, admin);
//	}
}
