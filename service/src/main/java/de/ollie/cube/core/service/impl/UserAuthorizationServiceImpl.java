package de.ollie.cube.core.service.impl;

import java.util.List;
import java.util.Optional;

import javax.inject.Named;

import de.ollie.cube.core.model.UserAuthorizationSO;
import de.ollie.cube.core.model.UserLoginIdSO;
import de.ollie.cube.core.service.UserAuthorizationService;

@Named
public class UserAuthorizationServiceImpl implements UserAuthorizationService {
	private static final UserAuthorizationSO[] USERS =
			new UserAuthorizationSO[] {
					new UserAuthorizationSO(
							new UserLoginIdSO("OTFLZT-0000000001"),
							"ollie",
							"42coolio",
							"OLI"),
					new UserAuthorizationSO(
							new UserLoginIdSO("TEST-USER"),
							"test-user",
							"geheim",
							"TU") };

	@Override
	public Optional<UserAuthorizationSO> authenticate(String userName, String password) {
		return List.of(USERS).stream().filter((user) -> {
			return user.getName().equals(userName) && user.getPassword().equals(password);
		}).findFirst();
	}

	@Override
	public Optional<UserAuthorizationSO> getUserAuthorization(UserLoginIdSO userLoginId) {
		return List.of(USERS).stream().filter((user) -> {
			return user.getUserLoginId().equals(userLoginId);
		}).findFirst();
	}
}