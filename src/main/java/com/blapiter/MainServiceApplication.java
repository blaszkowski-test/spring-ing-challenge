package com.blapiter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.blapiter" })
public class MainServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainServiceApplication.class, args);
	}

}
