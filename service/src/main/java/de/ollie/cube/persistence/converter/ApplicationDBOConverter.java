package de.ollie.cube.persistence.converter;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import lombok.Generated;

import de.ollie.cube.persistence.entity.ApplicationDBO;
import de.ollie.cube.core.model.Application;

/**
 * A DBO converter for applications.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class ApplicationDBOConverter implements ToModelConverter<Application, ApplicationDBO> {

	public ApplicationDBO toDBO(Application model) {
		if (model == null) {
			return null;
		}
		return new ApplicationDBO()
				.setId(model.getId())
				.setBaseUrl(model.getBaseUrl())
				.setGlobalId(model.getGlobalId())
				.setName(model.getName());
	}

	@Override
	public Application toModel(ApplicationDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new Application()
				.setId(dbo.getId())
				.setBaseUrl(dbo.getBaseUrl())
				.setGlobalId(dbo.getGlobalId())
				.setName(dbo.getName());
	}

	@Override
	public List<Application> toModel(List<ApplicationDBO> dbos) {
		if (dbos == null) {
			return null;
		}
		return dbos.stream().map(this::toModel).collect(Collectors.toList());
	}

}