package com.rsc.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rsc.api.model.Admin_DaoAuthenticationProvider;


@Repository
public interface Admin_JPArepository extends JpaRepository<Admin_DaoAuthenticationProvider, Long> 
{
	Admin_DaoAuthenticationProvider findByUsername(String username);
}
