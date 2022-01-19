package de.ollie.cube.gui.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EventTypeTest {

	private static final String NAME = "name";

	@DisplayName("Tests of constructor 'EventType(String)'.")
	@Nested
	class TestOfConstructor_String {

		@DisplayName("Creates a new event type instance with the passed name.")
		@Test
		void passValidName_ReturnsANewIntance() {
			// Prepare
			String name = NAME;
			// Run
			EventType returned = new EventType(name);
			// Check
			assertEquals(name, returned.getName());
		}

		@DisplayName("Throws an exception if an EventType with the passed name already exists.")
		@Test
		void passAnAlreadyExistingName_ThrowsAnException() {
			// Prepare
			String name = NAME + 1;
			new EventType(name);
			// Run & Check
			assertThrows(IllegalArgumentException.class, () -> new EventType(name));
		}

		@DisplayName("Throws an exception if an empty string is passed.")
		@Test
		void passAnEmptyString_ThrowsAnException() {
			assertThrows(IllegalArgumentException.class, () -> new EventType(""));
		}

		@DisplayName("Throws an exception if a null value is passed.")
		@Test
		void passANullValue_ThrowsAnException() {
			assertThrows(NullPointerException.class, () -> new EventType(null));
		}

	}

}