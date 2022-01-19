package de.ollie.cube.persistence.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A DBO for users.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
@Entity(name = "User")
@Table(name = "USER")
public class UserDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private long id;
	@Column(name = "ADMIN", nullable = false)
	private boolean admin;
	@Column(name = "GLOBAL_ID")
	private String globalId;
	@Column(name = "LAST_LOGIN_TIMESTAMP", nullable = false)
	private LocalDateTime lastLoginTimestamp;
	@Column(name = "NAME", nullable = false)
	private String name;
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	@Column(name = "TOKEN", nullable = false)
	private String token;

}