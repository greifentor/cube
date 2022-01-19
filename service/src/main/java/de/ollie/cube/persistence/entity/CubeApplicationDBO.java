package de.ollie.cube.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A DBO for cube_applications.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
@Entity(name = "CubeApplication")
@Table(name = "CUBE_APPLICATION")
public class CubeApplicationDBO {

	@Id
	@Column(name = "ID", nullable = false)
	private long id;
	@Column(name = "GLOBAL_ID", nullable = false)
	private String globalId;
	@Column(name = "NAME", nullable = false)
	private String name;

}