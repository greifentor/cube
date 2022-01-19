package de.ollie.cube.core.service.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import de.ollie.cube.core.service.AppConfiguration;

/**
 * A configuration for the application.
 *
 * @author ollie (29.12.2020) (overtook from carp-core)
 */
@Configuration
public class AppConfigurationImpl implements AppConfiguration {

	@Value("${app.version}")
	private String appVersion;

	@Override
	public String getName() {
		return "CUBE";
	}

	@Override
	public String getVersion() {
		return appVersion;
	}

}