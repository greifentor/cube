package de.ollie.cube.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import de.ollie.cube.core.service.configuration.JWTServiceConfiguration;

@SpringBootTest
class JWTServiceConfigurationTest {

	@Inject
	private JWTServiceConfiguration unitUnderTest;

	@Test
	void returnsTheCorrectValueFor_secret() {
		assertEquals("the-secret", unitUnderTest.getSecret());
	}

}