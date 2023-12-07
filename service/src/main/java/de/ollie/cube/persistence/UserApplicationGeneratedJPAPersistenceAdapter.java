package de.ollie.cube.persistence;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import de.ollie.cube.core.model.Page;
import de.ollie.cube.core.model.PageParameters;
import de.ollie.cube.core.model.UserApplication;
import de.ollie.cube.core.service.port.persistence.UserApplicationPersistencePort;
import de.ollie.cube.persistence.converter.PageConverter;
import de.ollie.cube.persistence.converter.PageParametersToPageableConverter;
import de.ollie.cube.persistence.converter.UserApplicationDBOConverter;
import de.ollie.cube.persistence.entity.UserApplicationDBO;
import de.ollie.cube.persistence.repository.UserApplicationDBORepository;
import jakarta.annotation.PostConstruct;
import lombok.Generated;

/**
 * A generated JPA persistence adapter for user_applications.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public abstract class UserApplicationGeneratedJPAPersistenceAdapter implements UserApplicationPersistencePort {

	@Inject
	protected UserApplicationDBOConverter converter;
	@Inject
	protected UserApplicationDBORepository repository;

	@Inject
	protected PageParametersToPageableConverter pageParametersToPageableConverter;

	protected PageConverter<UserApplication, UserApplicationDBO> pageConverter;

	@PostConstruct
	public void postConstruct() {
		pageConverter = new PageConverter<>(converter);
	}

	@Override
	public UserApplication create(UserApplication model) {
		model.setApplication(-1);
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	@Override
	public List<UserApplication> findAll() {
		return converter.toModel(repository.findAll());
	}

	@Override
	public Page<UserApplication> findAll(PageParameters pageParameters) {
		return pageConverter.convert(repository.findAll(pageParametersToPageableConverter.convert(pageParameters)));
	}

	@Override
	public Optional<UserApplication> findById(Long application) {
		return repository.findById(application).map(dbo -> converter.toModel(dbo));
	}

	@Override
	public UserApplication update(UserApplication model) {
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	@Override
	public void delete(UserApplication model) {
		repository.deleteById(model.getApplication());
	}

}