package com.ty.qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class QaApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(QaApplication.class, args);
	}

}
