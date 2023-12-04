package de.ollie.cube.gui.vaadin;

import org.springframework.stereotype.Component;

import com.vaadin.flow.spring.annotation.VaadinSessionScope;

import de.ollie.cube.core.model.UserAuthorizationSO;
import de.ollie.cube.core.model.localization.LocalizationSO;
import de.ollie.cube.gui.vaadin.go.SessionIdGO;
import jakarta.annotation.PostConstruct;
import lombok.Data;

/**
 * An object to hold data during the session.
 *
 * @author ollie (18.09.2021)
 */
@Component
@VaadinSessionScope
@Data
public class SessionData {

	private static int counter = 0;

	private UserAuthorizationSO userAuthorization;
	private SessionIdGO id;
	private LocalizationSO localization = LocalizationSO.DE;

	@PostConstruct
	void postConstruct() {
		id = new SessionIdGO("SWCM-Session-" + ++counter);
	}

	public String getUserName() {
		return userAuthorization != null ? userAuthorization.getName() : null;
	}

}