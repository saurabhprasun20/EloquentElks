package com.airbnb.eloquentelksbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EloquentElksBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EloquentElksBackendApplication.class, args);
	}
	
}
