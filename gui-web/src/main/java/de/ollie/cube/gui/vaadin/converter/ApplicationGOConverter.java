package de.ollie.cube.gui.vaadin.converter;

import javax.inject.Named;

import lombok.Generated;

import de.ollie.cube.gui.vaadin.go.ApplicationGO;
import de.ollie.cube.core.model.Application;

/**
 * A GO converter for applications.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class ApplicationGOConverter implements ToGOConverter<ApplicationGO, Application> {

	@Override
	public ApplicationGO toGO(Application model) {
		if (model == null) {
			return null;
		}
		return new ApplicationGO()
				.setId(model.getId())
				.setGlobalId(model.getGlobalId())
				.setName(model.getName());
	}

	public Application toModel(ApplicationGO go) {
		if (go == null) {
			return null;
		}
		return new Application()
				.setId(go.getId())
				.setGlobalId(go.getGlobalId())
				.setName(go.getName());
	}

}