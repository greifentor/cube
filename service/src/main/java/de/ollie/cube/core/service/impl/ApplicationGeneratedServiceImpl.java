package de.ollie.cube.core.service.impl;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import de.ollie.cube.core.model.Page;
import de.ollie.cube.core.model.PageParameters;
import de.ollie.cube.core.model.Application;
import de.ollie.cube.core.service.port.persistence.ApplicationPersistencePort;
import de.ollie.cube.core.service.ApplicationService;
import lombok.Generated;

/**
 * A generated service interface implementation for Application management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public abstract class ApplicationGeneratedServiceImpl implements ApplicationService {

	@Inject
	protected ApplicationPersistencePort persistencePort;

	@Override
	public Application create(Application model) {
		return persistencePort.create(model);
	}

	@Override
	public List<Application> findAll() {
		return persistencePort.findAll();
	}

	@Override
	public Page<Application> findAll(PageParameters pageParameters) {
		return persistencePort.findAll(pageParameters);
	}

	@Override
	public Optional<Application> findById(Long id) {
		return persistencePort.findById(id);
	}

	@Override
	public Application update(Application model) {
		return persistencePort.update(model);
	}

	@Override
	public void delete(Application model) {
		persistencePort.delete(model);
	}

}