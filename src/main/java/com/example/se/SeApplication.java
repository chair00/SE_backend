package com.example.se;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //BaseEntity 위해
@SpringBootApplication
public class SeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeApplication.class, args);
	}

}
