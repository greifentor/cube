package de.ollie.cube.core.service.port.persistence;

import java.util.List;
import java.util.Optional;

import de.ollie.cube.core.model.Page;
import de.ollie.cube.core.model.PageParameters;
import de.ollie.cube.core.model.Application;
import lombok.Generated;

/**
 * A generated persistence port interface for Application CRUD operations.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface ApplicationGeneratedPersistencePort {

	Application create(Application model);

	List<Application> findAll();

	Page<Application> findAll(PageParameters pageParameters);

	Optional<Application> findById(Long id);

	Application update(Application model);

	void delete(Application model);

}