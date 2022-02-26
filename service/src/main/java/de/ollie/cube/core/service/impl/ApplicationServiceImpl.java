package de.ollie.cube.core.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import de.ollie.cube.core.model.Application;
import de.ollie.cube.core.service.UserApplicationService;
import lombok.RequiredArgsConstructor;

/**
 * A service interface implementation for Application management.
 */
@Named
@RequiredArgsConstructor
public class ApplicationServiceImpl extends ApplicationGeneratedServiceImpl {

	private final UserApplicationService userApplicationService;

	@Override
	public List<Application> findAllApplicationsByUserId(Long userId) {
		return userApplicationService
				.findAllByUserId(userId)
				.stream()
				.map(userApplication -> persistencePort.findById(userApplication.getApplication()).orElse(null))
				.collect(Collectors.toList());
	}

}