package de.ollie.cube.core.service.impl;

import javax.inject.Named;

@Named
public class LetterGenerator {

	public String getLetter() {
		return "" + ((char) (Math.random() * 10 + 48));
	}

}
