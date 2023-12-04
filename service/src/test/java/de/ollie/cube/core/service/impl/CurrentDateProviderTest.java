package de.ollie.cube.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CurrentDateProviderTest {

	@InjectMocks
	private CurrentDateProvider unitUnderTest;

	@Nested
	class TestsOfMethod_now {

		@Test
		void returnsALocalDateObject() {
			assertNotNull(unitUnderTest.now());
		}
	}
}
