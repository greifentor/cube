package de.ollie.cube.core.service.impl;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import de.ollie.cube.core.model.Page;
import de.ollie.cube.core.model.PageParameters;
import de.ollie.cube.core.model.UserApplication;
import de.ollie.cube.core.service.port.persistence.UserApplicationPersistencePort;
import de.ollie.cube.core.service.UserApplicationService;
import lombok.Generated;

/**
 * A generated service interface implementation for UserApplication management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public abstract class UserApplicationGeneratedServiceImpl implements UserApplicationService {

	@Inject
	protected UserApplicationPersistencePort persistencePort;

	@Override
	public UserApplication create(UserApplication model) {
		return persistencePort.create(model);
	}

	@Override
	public List<UserApplication> findAll() {
		return persistencePort.findAll();
	}

	@Override
	public Page<UserApplication> findAll(PageParameters pageParameters) {
		return persistencePort.findAll(pageParameters);
	}

	@Override
	public Optional<UserApplication> findById(Long application) {
		return persistencePort.findById(application);
	}

	@Override
	public UserApplication update(UserApplication model) {
		return persistencePort.update(model);
	}

	@Override
	public void delete(UserApplication model) {
		persistencePort.delete(model);
	}

}