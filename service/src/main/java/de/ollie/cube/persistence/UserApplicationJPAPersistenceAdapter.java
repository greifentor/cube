package de.ollie.cube.persistence;

import java.util.List;

import javax.inject.Named;

import de.ollie.cube.core.model.UserApplication;

/**
 * A JPA persistence adapter for user_applications.
 */
@Named
public class UserApplicationJPAPersistenceAdapter extends UserApplicationGeneratedJPAPersistenceAdapter {

	@Override
	public List<UserApplication> findAllByUserId(Long userId) {
		return converter.toModel(repository.findAllByUser(userId));
	}

}