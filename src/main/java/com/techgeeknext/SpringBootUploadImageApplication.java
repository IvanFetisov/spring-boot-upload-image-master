package com.techgeeknext;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SpringBootUploadImageApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUploadImageApplication.class, args);
	}
}
