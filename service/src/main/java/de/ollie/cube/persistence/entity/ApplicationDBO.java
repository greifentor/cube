package de.ollie.cube.persistence.entity;

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
 * A DBO for applications.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
@Entity(name = "Application")
@Table(name = "APPLICATION")
public class ApplicationDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private long id;
	@Column(name = "BASE_URL", nullable = false)
	private String baseUrl;
	@Column(name = "GLOBAL_ID", nullable = false)
	private String globalId;
	@Column(name = "NAME", nullable = false)
	private String name;

}