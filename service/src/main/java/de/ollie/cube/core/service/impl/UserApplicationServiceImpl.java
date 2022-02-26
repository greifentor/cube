package de.ollie.cube.core.service.impl;

import java.util.List;

import javax.inject.Named;

import de.ollie.cube.core.model.UserApplication;

/**
 * A service interface implementation for UserApplication management.
 */
@Named
public class UserApplicationServiceImpl extends UserApplicationGeneratedServiceImpl {

	@Override
	public List<UserApplication> findAllByUserId(Long userId) {
		return persistencePort.findAllByUserId(userId);
	}
}