package com.emiperez.hizk.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HizkSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HizkSpringApplication.class, args);
	}

}
