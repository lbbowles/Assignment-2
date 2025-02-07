package com.csc340.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Assignment2Application {


	public static void main(String[] args) {
		SpringApplication.run(Assignment2Application.class, args);
	}

	//Just need to forward this stuff probably via import so that it loads the function that is currently in RestApi

}

