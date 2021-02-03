package com.rsc.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.rsc.api.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class Springsecurityconfiguration2 extends WebSecurityConfigurerAdapter
{
  
	@Autowired
	CustomUserDetailsService userDetailsService;
	

	
	@Autowired
	Unauthorized_401_Configuration  unauthorizedHandler;

	@Autowired
	OncePerRequestConfiguration  OncePerRequestfilter;
	
	  @Bean
	   public PasswordEncoder passwordEncoder()
		{
			return new BCryptPasswordEncoder();
		}
		
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception
		{
			 auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());	 
		}
		
		
		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
		
		@Override
		public void configure(HttpSecurity http) throws Exception
		{
			http.cors();
		    http.csrf().disable().authorizeRequests().antMatchers("/customers","/store").hasAnyRole("USER").
		    antMatchers("/admins","/details","/details_id").hasAnyRole("ADMIN").
		    antMatchers("/authenticateuser","/userregister","/adminregister").
			permitAll().antMatchers(HttpMethod.OPTIONS, "/**").
			permitAll().anyRequest().authenticated()
			.and().exceptionHandling()
	        .authenticationEntryPoint(unauthorizedHandler).and().
			// make sure we use stateless session; session won't be used to
			// store user's state.
			sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
			addFilterBefore(OncePerRequestfilter, UsernamePasswordAuthenticationFilter.class);
		  	

//	 		Add a filter to validate the tokens with every request
			
		}

	

}
