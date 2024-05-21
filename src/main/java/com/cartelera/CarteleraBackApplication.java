package com.cartelera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CarteleraBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarteleraBackApplication.class, args);
	}

}
