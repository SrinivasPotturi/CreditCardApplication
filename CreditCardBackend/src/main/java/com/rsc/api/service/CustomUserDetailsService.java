package com.rsc.api.service;

import java.util.Arrays;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rsc.api.model.Admin_DaoAuthenticationProvider;
import com.rsc.api.model.Registration_details;
import com.rsc.api.model.creditcarddetails;

//import com.rsc.api.model.Registration_details;
//import com.rsc.api.model.creditcarddetails;

import com.rsc.api.model.role;
import com.rsc.api.model.user_DaoAuthenticationProvider;
import com.rsc.api.model.usercreditcarddetails;
import com.rsc.api.repository.Admin_JPArepository;
import com.rsc.api.repository.Fetchcreditdetails;
import com.rsc.api.repository.user_JPArepository;




@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService
{
	
	 @Autowired
	 private user_JPArepository userDao;
	 
	 @Autowired
	 private Admin_JPArepository adminDao;
	 
	 @Autowired
	 private Fetchcreditdetails details;
	   
	 
	 
	 @Autowired
	 private role r;

	 @Autowired
	 private PasswordEncoder bcryptEncoder;
	
	 @Autowired
     private SessionFactory sessionFactory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		// TODO Auto-generated method stub
		

		String r1=r.getRole();
		if(r1.equals("ROLE_USER"))
		{
			List<SimpleGrantedAuthority> roles =null;
			
			user_DaoAuthenticationProvider  user = userDao.findByUsername(username);
			
			if (user != null) {
				roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
				return new User(user.getUsername(), user.getPassword(), roles);
			}
			throw new UsernameNotFoundException("User not found with the "+username);	
		}
		else
		{
			 List<SimpleGrantedAuthority> roles =null;
				
				Admin_DaoAuthenticationProvider  user = adminDao.findByUsername(username);
				
				if (user != null) {
					roles = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));
					return new User(user.getUsername(), user.getPassword(), roles);
				}
				throw new UsernameNotFoundException("User not found with the "+username);
			
		}
		
		
	}
	
	public user_DaoAuthenticationProvider saveuser(Registration_details user)
	{
		Session session = sessionFactory.getCurrentSession();
		user_DaoAuthenticationProvider newUser = new user_DaoAuthenticationProvider();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
		session.save(newUser);
		return newUser;
	}
	
	public Admin_DaoAuthenticationProvider saveadmin(Registration_details user)
	{
		Session session = sessionFactory.getCurrentSession();
		Admin_DaoAuthenticationProvider newUser = new Admin_DaoAuthenticationProvider();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setRole(user.getRole());
		session.save(newUser);
		return newUser;
	}
	
	public usercreditcarddetails update(creditcarddetails data)
	{
		Session session = sessionFactory.getCurrentSession();
		usercreditcarddetails n = new usercreditcarddetails();
		n.setUsername(data.getUsername());
		n.setEmail(data.getEmail());
		n.setAccount(data.getAccount());
		n.setAddress(data.getAddress());
		n.setCity(data.getCity());
		n.setZip(data.getZip());
		n.setState(data.getState());
		session.save(n);
		return n;
	}
	

	
	@Transactional
	public List<usercreditcarddetails> get(String item)
	{
		  System.out.println(item);
		  System.out.println(item.isEmpty());
		  if(item.isEmpty())
			  return details.findAll();
		  return details.findByUsername(item);  
		  
	}
	
	
	
	@Transactional
	public List<usercreditcarddetails> getbyid(long item)
	{
		 
		  return details.findById(item);  
		  
	}
	
	
	
	
	
	
     
     
    
}
