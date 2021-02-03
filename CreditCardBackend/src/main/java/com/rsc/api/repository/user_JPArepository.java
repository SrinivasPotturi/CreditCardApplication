package com.rsc.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rsc.api.model.user_DaoAuthenticationProvider;



@Repository
public interface user_JPArepository extends JpaRepository<user_DaoAuthenticationProvider, Long>  
{
    user_DaoAuthenticationProvider findByUsername(String username);
}
