package com.nbohn.pundit.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.nbohn.pundit.rest"})
public class PunditRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PunditRestApplication.class, args);
	}
}
