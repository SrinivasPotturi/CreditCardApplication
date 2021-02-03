package com.rsc.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.rsc.api.model.usercreditcarddetails;

@Repository
public interface Fetchcreditdetails  extends JpaRepository<usercreditcarddetails, Integer>
{
       List <usercreditcarddetails> findAll();
       List <usercreditcarddetails> findByUsername(String item);
       List <usercreditcarddetails> findById(long val);
}
