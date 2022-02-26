package de.ollie.cube.persistence.converter;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import lombok.Generated;

import de.ollie.cube.persistence.entity.UserApplicationDBO;
import de.ollie.cube.core.model.UserApplication;

/**
 * A DBO converter for user_applications.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class UserApplicationDBOConverter implements ToModelConverter<UserApplication, UserApplicationDBO> {

	public UserApplicationDBO toDBO(UserApplication model) {
		if (model == null) {
			return null;
		}
		return new UserApplicationDBO()
				.setApplication(model.getApplication())
				.setUser(model.getUser());
	}

	@Override
	public UserApplication toModel(UserApplicationDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new UserApplication()
				.setApplication(dbo.getApplication())
				.setUser(dbo.getUser());
	}

	@Override
	public List<UserApplication> toModel(List<UserApplicationDBO> dbos) {
		if (dbos == null) {
			return null;
		}
		return dbos.stream().map(this::toModel).collect(Collectors.toList());
	}

}