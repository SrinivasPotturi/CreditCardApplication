package com.rsc.api.model;

public class AuthenticationResponse 
{
   public String token;

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

public AuthenticationResponse(String token) {
	super();
	this.token = token;
}
   
public AuthenticationResponse()
{
	
}
   
}