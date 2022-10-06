package com.workshop.carautionsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class CarAutionSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarAutionSystemApplication.class, args);
	}

}
