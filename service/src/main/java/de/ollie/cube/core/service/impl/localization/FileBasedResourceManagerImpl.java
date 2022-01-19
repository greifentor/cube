package de.ollie.cube.core.service.impl.localization;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.ollie.cube.core.model.localization.LocalizationSO;
import de.ollie.cube.core.service.AppConfiguration;
import de.ollie.cube.core.service.localization.ResourceManager;

/**
 * An implementation for a file based resource manager.
 *
 * @author ollie (31.12.2020) (overtook from carp-core)
 */
@Component
public class FileBasedResourceManagerImpl implements ResourceManager {

	private static final Logger logger = LogManager.getLogger(FileBasedResourceManagerImpl.class);

	private Map<LocalizationSO, Properties> resources = new HashMap<>();

	@Autowired
	private AppConfiguration appConfiguration;
	@Autowired
	private FileBasedResourceManagerConfiguration configuration;

	@PostConstruct
	private void postConstruct() {
		logger.info("reading resources from: " + appConfiguration.getVersion());
		for (LocalizationSO localization : LocalizationSO.values()) {
			String fileName = configuration.getResourceFileName(localization).isEmpty()
					? "classpath:localization/" + configuration.getFileNamePrefix() + "_resources_"
							+ localization.name().toLowerCase() + ".properties"
					: configuration.getResourceFileName(localization);
			Properties properties = new Properties();
			logger.info("reading resources from: " + fileName);
			if (fileName.startsWith("classpath:")) {
				try {
					properties
							.load(getClass().getClassLoader().getResourceAsStream(fileName.replace("classpath:", "")));
				} catch (IOException ioe) {
					throw new IllegalStateException(
							"Resource not found '" + fileName + "' for localization: " + localization);
				} catch (Exception e) {
					throw new IllegalStateException(
							"Something went wrong while reading '" + fileName + "' for localization: " + localization);
				}
			} else {
				if (!new File(fileName).exists()) {
					throw new IllegalStateException(
							"Resource file not found '" + fileName + "' for localization: " + localization);
				}
				try {
					properties.load(new FileReader(fileName));
				} catch (IOException ioe) {
					throw new IllegalStateException(
							"Resource file not readable '" + fileName + "' for localization: " + localization);
				}
			}
			resources.put(localization, properties);
		}
	}

	@Override
	public String getLocalizedString(String resourceId) {
		return getLocalizedString(resourceId, LocalizationSO.DE);
	}

	@Override
	public String getLocalizedString(String resourceId, LocalizationSO localization) {
		Properties properties = resources.get(localization);
		if (properties == null) {
			return resourceId;
		}
		return properties.getProperty(resourceId, resourceId);
	}

}