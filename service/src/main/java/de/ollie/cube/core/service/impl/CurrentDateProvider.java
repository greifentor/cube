package de.ollie.cube.core.service.impl;

import java.time.LocalDate;

import javax.inject.Named;

@Named
public class CurrentDateProvider {

	public LocalDate now() {
		return LocalDate.now();
	}

}
