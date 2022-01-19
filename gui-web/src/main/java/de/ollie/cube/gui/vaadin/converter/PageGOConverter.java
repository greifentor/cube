package de.ollie.cube.gui.vaadin.converter;

import java.util.stream.Collectors;

import de.ollie.cube.core.model.Page;
import de.ollie.cube.gui.vaadin.go.converter.PageGO;

import lombok.AllArgsConstructor;
import lombok.Generated;

/**
 * A class to convert a service page to a GUI web layer page object.
 *
 * @param <GO>    The type of the GO's which are representing model objects in the GUI web layer.
 * @param <MODEL> The type of the service layer model class.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@AllArgsConstructor
@Generated
public class PageGOConverter<GO, MODEL> {

	private final ToGOConverter<GO, MODEL> toGOConverter;

	public PageGO<GO> toGO(Page<MODEL> page) {
		if (page == null) {
			return null;
		}
		return new PageGO<GO>()
				.setEntries(page.getEntries().stream().map(toGOConverter::toGO).collect(Collectors.toList()))
				.setEntriesPerPage(page.getEntriesPerPage())
				.setEntriesTotal(page.getEntriesTotal());
	}

}