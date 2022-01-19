package de.ollie.cube.gui.vaadin.converter;

import javax.inject.Named;

import lombok.Generated;

import de.ollie.cube.gui.vaadin.go.UserGO;
import de.ollie.cube.core.model.User;

/**
 * A GO converter for users.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class UserGOConverter implements ToGOConverter<UserGO, User> {

	@Override
	public UserGO toGO(User model) {
		if (model == null) {
			return null;
		}
		return new UserGO()
				.setId(model.getId())
				.setAdmin(model.isAdmin())
				.setGlobalId(model.getGlobalId())
				.setLastLoginTimestamp(model.getLastLoginTimestamp())
				.setName(model.getName())
				.setPassword(model.getPassword())
				.setToken(model.getToken());
	}

	public User toModel(UserGO go) {
		if (go == null) {
			return null;
		}
		return new User()
				.setId(go.getId())
				.setAdmin(go.isAdmin())
				.setGlobalId(go.getGlobalId())
				.setLastLoginTimestamp(go.getLastLoginTimestamp())
				.setName(go.getName())
				.setPassword(go.getPassword())
				.setToken(go.getToken());
	}

}