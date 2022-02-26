package de.ollie.cube.core.service;

import java.util.List;

import de.ollie.cube.core.model.Application;

/**
 * A service interface for Application management.
 */
public interface ApplicationService extends ApplicationGeneratedService {

	List<Application> findAllApplicationsByUserId(Long userId);

}