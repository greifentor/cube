package de.ollie.cube.core.model;

import lombok.Value;

@Value
public class UserAuthorizationSO {

	private String globalId;
	private String name;
	private String password;
	private String token;
	private long userId;

}