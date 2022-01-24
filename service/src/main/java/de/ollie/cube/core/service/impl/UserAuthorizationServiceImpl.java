package de.ollie.cube.core.service.impl;

import java.security.InvalidKeyException;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.ollie.cube.core.model.User;
import de.ollie.cube.core.model.UserAuthorizationSO;
import de.ollie.cube.core.service.UserAuthorizationService;
import de.ollie.cube.core.service.UserService;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class UserAuthorizationServiceImpl implements UserAuthorizationService {

	private static final Logger LOGGER = LogManager.getLogger(UserAuthorizationServiceImpl.class);

	private final PasswordEncoder passwordEncoder;
	private final UserService userService;

	@Override
	public Optional<UserAuthorizationSO> authenticate(String userName, String password) {
		return userService
				.findAll()
				.stream()
				.filter(user -> isUserNameMatching(user, userName) && isPasswordMatching(user, password))
				.findFirst()
				.map(
						user -> new UserAuthorizationSO(
								user.getGlobalId(),
								user.getName(),
								user.getPassword(),
								user.getToken(),
								user.getId()));
	}

	private boolean isUserNameMatching(User user, String userName) {
		return user.getName().equalsIgnoreCase(userName);
	}

	private boolean isPasswordMatching(User user, String password) {
		try {
			return "".equals(user.getPassword()) || user.getPassword().equals(passwordEncoder.encode(password));
		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
			LOGGER.error("error while encoding password: " + e.getMessage(), e);
		}
		return false;
	}

}