package de.ollie.cube.persistence.converter;

import javax.inject.Named;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import de.ollie.cube.core.model.PageParameters;

import lombok.Generated;

/**
 * A converter to create a Pageable from a PageParameters object.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
@Named
public class PageParametersToPageableConverter {

	public Pageable convert(PageParameters pageParameters) {
		if (pageParameters == null) {
			return null;
		}
		return PageRequest.of(pageParameters.getPageNumber(), pageParameters.getEntriesPerPage());
	}

}