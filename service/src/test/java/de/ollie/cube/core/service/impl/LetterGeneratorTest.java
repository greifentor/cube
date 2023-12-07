package de.ollie.cube.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LetterGeneratorTest {

	@InjectMocks
	private LetterGenerator unitUnderTest;

	@Nested
	class TestsOfMethod_getLetter {

		@Test
		void returnsAnUpperCaseLetter() {
			String s = "0123456789";
			for (int i = 0; i < 1000; i++) {
				String letter = unitUnderTest.getLetter();
				s = s.replace(letter, "");
				assertEquals(1, letter.length());
				assertTrue((letter.charAt(0) >= '0') && ((letter.charAt(0) <= '9')), letter + " is not valid");
			}
			assertTrue(s.isEmpty(), "there are letter not created: " + s);
		}

	}

}
