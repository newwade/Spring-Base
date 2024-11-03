package com.projecto.baseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableCaching
@EnableMethodSecurity(securedEnabled = true)
public class BaseapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseapiApplication.class, args);
	}

}
