package de.ollie.cube.gui.vaadin.go;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

/**
 * A container for users data in the GUI layer.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Accessors(chain = true)
@Data
@Generated
public class UserGO {

	private long id;
	private boolean admin;
	private String globalId;
	private LocalDateTime lastLoginTimestamp;
	private String name;
	private String password;
	private String token;

}