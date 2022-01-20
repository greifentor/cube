package de.ollie.cube.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.cube.core.model.User;
import de.ollie.cube.core.model.UserAuthorizationSO;
import de.ollie.cube.core.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserAuthorizationServiceImplTest {

	private static final String GLOBAL_ID = "global id";
	private static final String PASSWORD = "password";
	private static final String PASSWORD_CODED = "password coded";
	private static final String TOKEN = "token";
	private static final String USER_NAME = "UserName";

	private static final List<User> USERS =
			List
					.of(
							new User().setName(USER_NAME + 0).setPassword(PASSWORD + 0),
							new User()
									.setGlobalId(GLOBAL_ID)
									.setName(USER_NAME)
									.setPassword(PASSWORD_CODED)
									.setToken(TOKEN),
							new User().setName(USER_NAME + 1).setPassword(PASSWORD + 1));

	@Mock
	private PasswordEncoder passwordEncoder;
	@Mock
	private UserService userService;

	@InjectMocks
	private UserAuthorizationServiceImpl unitUnderTest;

	@Nested
	class TestsOfMethod_authenticate_String_String {

		@Test
		void passUserServiceHasNoUsers_returnsAnEmptyOptional() {
			// Prepare
			when(userService.findAll()).thenReturn(List.of());
			// Run & Check
			assertEquals(Optional.empty(), unitUnderTest.authenticate(USER_NAME, PASSWORD));
		}

		@Test
		void passANotMatchingUserName_returnsAnEmptyOptional() {
			// Prepare
			when(userService.findAll()).thenReturn(USERS);
			// Run & Check
			assertEquals(Optional.empty(), unitUnderTest.authenticate(";op", PASSWORD));
		}

		@Test
		void passANotMatchingPassword_returnsAnEmptyOptional() {
			// Prepare
			when(userService.findAll()).thenReturn(USERS);
			// Run & Check
			assertEquals(Optional.empty(), unitUnderTest.authenticate(USER_NAME, ";op"));
		}

		@Test
		void passAMatchingUserNameAndPassword_returnsAFilledOptional() throws Exception {
			// Prepare
			when(userService.findAll()).thenReturn(USERS);
			when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD_CODED);
			// Run
			Optional<UserAuthorizationSO> returned = unitUnderTest.authenticate(USER_NAME, PASSWORD);
			// Check
			assertTrue(returned.isPresent());
		}

		@Nested
		class CaseInsenitivity {

			@Test
			void passAMatchingUserNameUpperCaseAndPassword_returnsAFilledOptional() throws Exception {
				// Prepare
				when(userService.findAll()).thenReturn(USERS);
				when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD_CODED);
				// Run
				Optional<UserAuthorizationSO> returned = unitUnderTest.authenticate(USER_NAME.toUpperCase(), PASSWORD);
				// Check
				assertTrue(returned.isPresent());
			}

			@Test
			void passAMatchingUserNameLowerCaseAndPassword_returnsAFilledOptional() throws Exception {
				// Prepare
				when(userService.findAll()).thenReturn(USERS);
				when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD_CODED);
				// Run
				Optional<UserAuthorizationSO> returned = unitUnderTest.authenticate(USER_NAME.toLowerCase(), PASSWORD);
				// Check
				assertTrue(returned.isPresent());
			}

		}

		@Test
		void passAMatchingUserNameAndPassword_returnsAnOptionalWithTheGlobalId() throws Exception {
			// Prepare
			when(userService.findAll()).thenReturn(USERS);
			when(passwordEncoder.encode(PASSWORD)).thenReturn(PASSWORD_CODED);
			// Run
			Optional<UserAuthorizationSO> returned = unitUnderTest.authenticate(USER_NAME, PASSWORD);
			// Check
			assertEquals(GLOBAL_ID, returned.get().getGlobalId());
		}

	}

}