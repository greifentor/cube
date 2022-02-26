package de.ollie.cube.core.service.port.persistence;

import java.util.List;

import de.ollie.cube.core.model.UserApplication;

/**
 * A persistence port interface for UserApplication CRUD operations.
 */
public interface UserApplicationPersistencePort extends UserApplicationGeneratedPersistencePort {

	List<UserApplication> findAllByUserId(Long userId);

}