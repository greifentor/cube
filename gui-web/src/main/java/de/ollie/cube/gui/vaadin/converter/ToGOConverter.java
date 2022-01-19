package de.ollie.cube.gui.vaadin.converter;

import lombok.Generated;

/**
 * An interface for to GO converters.
 *
 * @param <GO>    The type of the GO's.
 * @param <MODEL> The type of the models.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface ToGOConverter<GO, MODEL> {

	GO toGO(MODEL model);

}