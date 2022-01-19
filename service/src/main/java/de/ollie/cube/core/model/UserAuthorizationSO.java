package de.ollie.cube.core.model;

import lombok.Value;

@Value
public class UserAuthorizationSO {

	private UserLoginIdSO userLoginId;
	private String name;
	private String password;
	private String token;

}