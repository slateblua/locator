package com.slateblua.locator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LocatorApplication {

	public static void main (String[] args) {
		SpringApplication.run(LocatorApplication.class, args);
	}
}
