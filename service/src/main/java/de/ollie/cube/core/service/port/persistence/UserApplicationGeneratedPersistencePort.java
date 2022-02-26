package de.ollie.cube.core.service.port.persistence;

import java.util.List;
import java.util.Optional;

import de.ollie.cube.core.model.Page;
import de.ollie.cube.core.model.PageParameters;
import de.ollie.cube.core.model.UserApplication;
import lombok.Generated;

/**
 * A generated persistence port interface for UserApplication CRUD operations.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface UserApplicationGeneratedPersistencePort {

	UserApplication create(UserApplication model);

	List<UserApplication> findAll();

	Page<UserApplication> findAll(PageParameters pageParameters);

	Optional<UserApplication> findById(Long application);

	UserApplication update(UserApplication model);

	void delete(UserApplication model);

}