package de.ollie.cube.core.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.inject.Named;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import de.ollie.cube.core.service.configuration.JWTServiceConfiguration;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class JWTServiceImpl {

	private final JWTServiceConfiguration configuration;

	public String createJWT(String userName, String userToken, String userGlobalId, String applicationName,
			LocalDateTime endOfValidity, List<String> applicationRights) {
		Algorithm algorithm = Algorithm.HMAC512(configuration.getSecret());
		return JWT
				.create()
				.withClaim("userToken", userToken)
				.withArrayClaim("applicationRights", applicationRights.toArray(new String[applicationRights.size()]))
				.withClaim("userGlobalId", userGlobalId)
				.withClaim("endOfValidity", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(endOfValidity))
				.withClaim("userName", userName)
				.withClaim("applicationName", applicationName)
				.sign(algorithm);
	}

}
