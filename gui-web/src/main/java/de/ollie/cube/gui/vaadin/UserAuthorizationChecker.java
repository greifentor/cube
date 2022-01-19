package de.ollie.cube.gui.vaadin;

import com.vaadin.flow.router.BeforeEnterEvent;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * A base vertical layout for view with session data.
 *
 * @author ollie (19.09.2021)
 */
@UtilityClass
@Slf4j
public class UserAuthorizationChecker {

	public void forwardToLoginOnNoUserSetForSession(SessionData sessionData, BeforeEnterEvent beforeEnterEvent) {
		if (sessionData.getUserName() == null) {
			log.info("no authorization forwarted to login page.");
			beforeEnterEvent.forwardTo(ApplicationStartLayout.URL);
		}
	}

}