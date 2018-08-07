package com.helon.jwtclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class JwtClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtClientApplication.class, args);
	}
}
