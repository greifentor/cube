package de.ollie.cube.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import de.ollie.cube.core.service.configuration.JWTServiceConfiguration;

@ExtendWith(MockitoExtension.class)
public class JWTServiceImplTest {

	@Mock
	private JWTServiceConfiguration configuration;

	@InjectMocks
	private JWTServiceImpl unitUnderTest;

	@Nested
	class TestsOfMethod_createJWT_String_String_LocalDateTime {

		@Test
		void passValidData_returnsAValidJWT() {
			// Prepare
			String expected =
					"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJ1c2VyVG9rZW4iOiJUVSIsImFwcGxpY2F0aW9uUmlnaHRzIjpbInJpZ2h0MSIsInJpZ2h0MiJdLCJ1c2VyR2xvYmFsSWQiOiJURVNULVVTRVIiLCJlbmRPZlZhbGlkaXR5IjoiMjAyMi0xMi0zMSAyMzo1OTo1OSIsInVzZXJOYW1lIjoidGVzdC11c2VyIiwiYXBwbGljYXRpb25OYW1lIjoidGVzdC1hcHAifQ.ztETOdRl1cuDZEOW3716EhDcm3M1LD9Rq2RPkfeSRr3OslB1QC0zXbTPVoo4Iqy_dhIX2R9wgMo7SS8r842pqw";
			String applicationName = "test-app";
			String userGlobalId = "TEST-USER";
			String userName = "test-user";
			String userToken = "TU";
			String right1 = "right1";
			String right2 = "right2";
			LocalDateTime endOfValidity = LocalDateTime.of(2022, 12, 31, 23, 59, 59);
			when(configuration.getSecret()).thenReturn("the-secret");
			// Run
			String returned =
					unitUnderTest
							.createJWT(
									userName,
									userToken,
									userGlobalId,
									applicationName,
									endOfValidity,
									List.of(right1, right2));
			// Check
			assertEquals(expected, returned);
		}

	}

}