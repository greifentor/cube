package de.ollie.cube.core.service;

import java.util.List;
import java.util.Optional;

import de.ollie.cube.core.model.Page;
import de.ollie.cube.core.model.PageParameters;
import de.ollie.cube.core.model.Application;
import lombok.Generated;

/**
 * A generated service interface for Application management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface ApplicationGeneratedService {

	Application create(Application model);

	List<Application> findAll();

	Page<Application> findAll(PageParameters pageParameters);

	Optional<Application> findById(Long id);

	Application update(Application model);

	void delete(Application model);

}