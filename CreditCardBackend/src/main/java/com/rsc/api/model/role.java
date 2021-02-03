package com.rsc.api.model;

import org.springframework.stereotype.Component;

@Component
public class role 
{
  
  private String role;


  public role()
  {
	  
  }
  
  public String getRole()
  {
	return role;
  }

  public role(String role) {
	super();
	this.role = role;
}


  public  void setRole(String role)
  {
	this.role = role;
  }
  
}
