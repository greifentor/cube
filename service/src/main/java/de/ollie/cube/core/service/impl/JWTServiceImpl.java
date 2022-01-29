package de.ollie.cube.core.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.inject.Named;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import de.ollie.cube.core.service.JWTService;
import de.ollie.cube.core.service.configuration.JWTServiceConfiguration;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {

	private final JWTServiceConfiguration configuration;

	@Override
	public String createJWT(String userName, String userToken, String userGlobalId, String applicationName,
			LocalDateTime loginDate, List<String> applicationRights) {
		Algorithm algorithm = Algorithm.HMAC512(configuration.getSecret());
		return JWT
				.create()
				.withClaim("userToken", userToken)
				.withArrayClaim("applicationRights", applicationRights.toArray(new String[applicationRights.size()]))
				.withClaim("userGlobalId", userGlobalId)
				.withClaim("loginDate", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(loginDate))
				.withClaim("userName", userName)
				.withClaim("applicationName", applicationName)
				.sign(algorithm);
	}

}
