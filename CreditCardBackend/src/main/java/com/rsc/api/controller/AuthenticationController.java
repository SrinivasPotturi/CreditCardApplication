package com.rsc.api.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rsc.api.model.AuthenticationDetails;
import com.rsc.api.model.AuthenticationResponse;
import com.rsc.api.model.Registration_details;
import com.rsc.api.model.creditcarddetails;

import com.rsc.api.model.role;
import com.rsc.api.service.CustomUserDetailsService;
import com.rsc.api.service.JwtUtil;





@RestController
public class AuthenticationController 
{
   @Autowired
   private AuthenticationManager authenticationManager;
   
   @Autowired
   private CustomUserDetailsService userDetailsService;
   
   
   @Autowired
   private role a;
   
   
   @Autowired
   private JwtUtil jwtUtil;
   
   
   
   @RequestMapping(value = "/authenticateuser", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationTokenUser(@RequestBody AuthenticationDetails authenticationRequest)
			throws Exception {
		try {
			
		    a.setRole(authenticationRequest.getRole());
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
   
  
   
   @RequestMapping(value = "/userregister", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody Registration_details user) throws Exception 
    {
		return ResponseEntity.ok(userDetailsService.saveuser(user));
	}
   
   @RequestMapping(value = "/adminregister", method = RequestMethod.POST)
  	public ResponseEntity<?> saveAdmin(@RequestBody Registration_details user) throws Exception 
      {
  		return ResponseEntity.ok(userDetailsService.saveadmin(user));
  	}
   
   @RequestMapping(value = "/customers", method = RequestMethod.GET)
 	public ResponseEntity<?> nameuser() throws Exception 
     {
 		return ResponseEntity.ok(true);
 	}
   
   @RequestMapping(value = "/admins", method = RequestMethod.GET)
	public ResponseEntity<?> nameadmin() throws Exception 
    {
		return ResponseEntity.ok(true);
	}

  
   @RequestMapping(value = "/store", method = RequestMethod.POST)
 	public ResponseEntity<?> s(@RequestBody creditcarddetails user) throws Exception 
     {
 		return ResponseEntity.ok(userDetailsService.update(user));
 	}
   
   @RequestMapping(value = "/details", method = RequestMethod.GET)
  	public ResponseEntity<?> n(@RequestParam("data") String item) throws Exception 
      {
  		return ResponseEntity.ok(userDetailsService.get(item));
  	  }
   
   @RequestMapping(value = "/details_id", method = RequestMethod.GET)
 	public ResponseEntity<?> n1(@RequestParam("data") long item) throws Exception 
     {
 		return ResponseEntity.ok(userDetailsService.getbyid(item));
 	  }
   
   
 
   
   
}
   

