package de.ollie.cube.core.service;

import java.util.List;

import de.ollie.cube.core.model.UserApplication;

/**
 * A service interface for UserApplication management.
 */
public interface UserApplicationService extends UserApplicationGeneratedService {

	List<UserApplication> findAllByUserId(Long userId);

}