package de.ollie.cube.core.service;

import java.util.Optional;

import de.ollie.cube.core.model.UserAuthorizationSO;
import de.ollie.cube.core.model.UserLoginIdSO;

/**
 * An interface for the user login and authentication.
 *
 * @author ollie (21.12.2020) (overtook from carp-core)
 */
public interface UserAuthorizationService {

	/**
	 * Returns an optional with the user authorization data for the passed password and user name.
	 * 
	 * @param userName The user name in the system.
	 * @param password The password to authenticate the user.
	 * @return An optional with the user authorization data.
	 */
	Optional<UserAuthorizationSO> authenticate(String userName, String password);

	/**
	 * Returns an optional with the user authorization data for the passed user login id.
	 * 
	 * @param userLoginId The user login id which the user authorization is to return for.
	 * @return An optional with the user authorization data.
	 */
	Optional<UserAuthorizationSO> getUserAuthorization(UserLoginIdSO userLoginId);

}