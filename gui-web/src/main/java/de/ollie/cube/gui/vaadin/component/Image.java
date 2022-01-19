package de.ollie.cube.gui.vaadin.component;

import com.vaadin.flow.server.StreamResource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import lombok.Generated;

/**
 * A component for a file based image.
 *
 * GENERATED CODE !!! DO NOT CHANGE !!!
 */
@Generated
public class Image extends com.vaadin.flow.component.html.Image {

	public Image(File file) {
		this(file, "alt text");
	}

	public Image(File file, String altText) {
		super(new StreamResource(file.getName(), () -> {
			try {
				return new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			return null;
		}), altText);
	}

}