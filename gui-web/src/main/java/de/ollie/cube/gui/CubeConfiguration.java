package de.ollie.cube.gui;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
@Getter
public class CubeConfiguration {

	@Value("${urls.shoppinglist}")
	private String urlShoppinglist;

}
