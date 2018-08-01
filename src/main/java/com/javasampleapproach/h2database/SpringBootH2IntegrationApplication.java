package com.javasampleapproach.h2database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SpringBootH2IntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootH2IntegrationApplication.class, args);
	}
}
