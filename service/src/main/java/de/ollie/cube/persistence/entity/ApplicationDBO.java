package de.ollie.cube.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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