package de.ollie.cube.core.service.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class JWTServiceConfiguration {

	@Value("${jwt.service.secret}")
	private String secret;

}