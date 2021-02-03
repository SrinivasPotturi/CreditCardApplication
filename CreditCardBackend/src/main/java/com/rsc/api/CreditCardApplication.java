package com.rsc.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
@EnableJpaRepositories
@EnableAutoConfiguration(exclude=HibernateJpaAutoConfiguration.class)
@Configuration
public class CreditCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardApplication.class, args);
	}
	
	 @Bean
	 public WebMvcConfigurer corsConfigurer()
	 {
		   return new WebMvcConfigurer()
				   {
			         @Override
			         public void addCorsMappings(CorsRegistry registry)
			         {
			        	registry.addMapping("/*")
			        	.allowCredentials(true)
			        	    .allowedMethods("*")
			        	    .allowedHeaders("*")
			        	    .allowedOrigins("*"); 
			         }
				   };
		  
	   }


}
