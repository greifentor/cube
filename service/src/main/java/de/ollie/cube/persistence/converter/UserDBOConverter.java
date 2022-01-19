package de.ollie.cube.persistence.converter;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import lombok.Generated;

import de.ollie.cube.persistence.entity.UserDBO;
import de.ollie.cube.core.model.User;

/**
 * A DBO converter for users.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class UserDBOConverter implements ToModelConverter<User, UserDBO> {

	public UserDBO toDBO(User model) {
		if (model == null) {
			return null;
		}
		return new UserDBO()
				.setId(model.getId())
				.setAdmin(model.isAdmin())
				.setGlobalId(model.getGlobalId())
				.setLastLoginTimestamp(model.getLastLoginTimestamp())
				.setName(model.getName())
				.setPassword(model.getPassword())
				.setToken(model.getToken());
	}

	@Override
	public User toModel(UserDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new User()
				.setId(dbo.getId())
				.setAdmin(dbo.isAdmin())
				.setGlobalId(dbo.getGlobalId())
				.setLastLoginTimestamp(dbo.getLastLoginTimestamp())
				.setName(dbo.getName())
				.setPassword(dbo.getPassword())
				.setToken(dbo.getToken());
	}

	@Override
	public List<User> toModel(List<UserDBO> dbos) {
		if (dbos == null) {
			return null;
		}
		return dbos.stream().map(this::toModel).collect(Collectors.toList());
	}

}