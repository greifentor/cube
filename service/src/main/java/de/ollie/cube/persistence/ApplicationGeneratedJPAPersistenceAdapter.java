package de.ollie.cube.persistence;

import static de.ollie.cube.util.Check.ensure;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import de.ollie.cube.core.model.Application;
import de.ollie.cube.core.model.Page;
import de.ollie.cube.core.model.PageParameters;
import de.ollie.cube.core.service.exception.NotNullConstraintViolationException;
import de.ollie.cube.core.service.port.persistence.ApplicationPersistencePort;
import de.ollie.cube.persistence.converter.ApplicationDBOConverter;
import de.ollie.cube.persistence.converter.PageConverter;
import de.ollie.cube.persistence.converter.PageParametersToPageableConverter;
import de.ollie.cube.persistence.entity.ApplicationDBO;
import de.ollie.cube.persistence.repository.ApplicationDBORepository;
import jakarta.annotation.PostConstruct;
import lombok.Generated;

/**
 * A generated JPA persistence adapter for applications.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public abstract class ApplicationGeneratedJPAPersistenceAdapter implements ApplicationPersistencePort {

	@Inject
	protected ApplicationDBOConverter converter;
	@Inject
	protected ApplicationDBORepository repository;

	@Inject
	protected PageParametersToPageableConverter pageParametersToPageableConverter;

	protected PageConverter<Application, ApplicationDBO> pageConverter;

	@PostConstruct
	public void postConstruct() {
		pageConverter = new PageConverter<>(converter);
	}

	@Override
	public Application create(Application model) {
		model.setId(-1);
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	@Override
	public List<Application> findAll() {
		return converter.toModel(repository.findAll());
	}

	@Override
	public Page<Application> findAll(PageParameters pageParameters) {
		return pageConverter.convert(repository.findAll(pageParametersToPageableConverter.convert(pageParameters)));
	}

	@Override
	public Optional<Application> findById(Long id) {
		return repository.findById(id).map(dbo -> converter.toModel(dbo));
	}

	@Override
	public Application update(Application model) {
		ensure(
				model.getBaseUrl() != null,
				() -> new NotNullConstraintViolationException(
						"Application field baseUrl cannot be null.",
						"Application",
						"baseUrl"));
		ensure(
				model.getGlobalId() != null,
				() -> new NotNullConstraintViolationException(
						"Application field globalId cannot be null.",
						"Application",
						"globalId"));
		ensure(
				model.getName() != null,
				() -> new NotNullConstraintViolationException(
						"Application field name cannot be null.",
						"Application",
						"name"));
		return converter.toModel(repository.save(converter.toDBO(model)));
	}

	@Override
	public void delete(Application model) {
		repository.deleteById(model.getId());
	}

}