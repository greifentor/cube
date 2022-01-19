package de.ollie.cube.core.service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ollie (04.01.2022)
 */
public interface JWTService {

	/**
	 * Creates a new authentication JWT.
	 * 
	 * @param userName          The name of the user.
	 * @param userToken         The token of the user.
	 * @param userGlobalId      The global Id of the user.
	 * @param applicationName   The name of the application which the token is created for.
	 * @param endOfValidity     The end of the validity of the JWT.
	 * @param applicationRights The rights of the user in the application with the passed name.
	 */
	String createJWT(String userName, String userToken, String userGlobalId, String applicationName,
			LocalDateTime endOfValidity,
			List<String> applicationRights);

}