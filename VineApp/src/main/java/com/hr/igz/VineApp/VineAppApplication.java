package com.hr.igz.VineApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.hr.igz.VineApp")
public class VineAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VineAppApplication.class, args);
	}

}
