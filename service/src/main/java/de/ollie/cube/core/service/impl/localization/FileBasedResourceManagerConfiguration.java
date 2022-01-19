package de.ollie.cube.core.service.impl.localization;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import de.ollie.cube.core.model.localization.LocalizationSO;
import lombok.Getter;

/**
 * A Configuration for the file based resource manager,
 *
 * @author ollie (20.08.2021) (overtook from carp-core)
 */
@Configuration
@Getter
public class FileBasedResourceManagerConfiguration {

	@Value("${localization.resource.file.name.prefix:cube}")
	private String fileNamePrefix;

	@Value("${localization.resource.file.name.de:}")
	private String resourceFileNameDE;

	public String getResourceFileName(LocalizationSO localization) {
		switch (localization) {
		case DE:
			return resourceFileNameDE;
		default:
			return "";
		}
	}

}