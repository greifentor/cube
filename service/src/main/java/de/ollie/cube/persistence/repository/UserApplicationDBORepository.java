package de.ollie.cube.persistence.repository;

import java.util.List;

import de.ollie.cube.persistence.entity.UserApplicationDBO;

public interface UserApplicationDBORepository extends UserApplicationGeneratedDBORepository {

	List<UserApplicationDBO> findAllByUser(Long user);

}