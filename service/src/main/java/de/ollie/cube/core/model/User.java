package de.ollie.cube.core.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A model for users.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
public class User {

	private long id;
	private boolean admin;
	private String globalId;
	private LocalDateTime lastLoginTimestamp;
	private String name;
	private String password;
	private String token;

}