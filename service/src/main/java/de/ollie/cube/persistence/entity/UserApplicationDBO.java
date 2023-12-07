package de.ollie.cube.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A DBO for user_applications.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
@Entity(name = "UserApplication")
@Table(name = "USER_APPLICATION")
public class UserApplicationDBO {

	@Id
	@Column(name = "APPLICATION", nullable = false)
	private long application;
	// @Id
	@Column(name = "USER", nullable = false)
	private long user;

}