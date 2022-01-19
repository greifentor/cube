package de.ollie.cube.gui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories("de.ollie.cube.persistence.repository")
@ComponentScan("de.ollie")
@EntityScan("de.ollie.cube.persistence.entity")
public class CubeWebApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(CubeWebApplication.class, args);
	}

}