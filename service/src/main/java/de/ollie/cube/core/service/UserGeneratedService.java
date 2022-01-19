package de.ollie.cube.core.service;

import java.util.List;
import java.util.Optional;

import de.ollie.cube.core.model.Page;
import de.ollie.cube.core.model.PageParameters;
import de.ollie.cube.core.model.User;
import lombok.Generated;

/**
 * A generated service interface for User management.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public interface UserGeneratedService {

	User create(User model);

	List<User> findAll();

	Page<User> findAll(PageParameters pageParameters);

	Optional<User> findById(Long id);

	User update(User model);

	void delete(User model);

}