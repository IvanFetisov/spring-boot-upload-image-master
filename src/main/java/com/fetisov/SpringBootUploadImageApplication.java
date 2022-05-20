package com.fetisov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
public class SpringBootUploadImageApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootUploadImageApplication.class, args);
	}
}
