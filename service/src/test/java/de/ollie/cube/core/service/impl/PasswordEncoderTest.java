package de.ollie.cube.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class PasswordEncoderTest {

	@InjectMocks
	private PasswordEncoder unitUnderTest;

	@Test
	void decodesEncodedPassword() throws Exception {
		ReflectionTestUtils.setField(unitUnderTest, "keyStr", "1234567890123456");
		String password = "password";
		unitUnderTest.postConstruct();
		String encoded = unitUnderTest.encode(password);
		assertNotEquals(password, encoded);
		String decoded = unitUnderTest.decode(encoded);
		assertEquals(decoded, password);
	}

}