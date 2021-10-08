package com.hr.igz.VineApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.hr.igz.VineApp.security.WebSecurityConfig;

@SpringBootApplication
@ComponentScan("com.hr.igz.VineApp")
public class VineAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VineAppApplication.class, args);
	}

}
