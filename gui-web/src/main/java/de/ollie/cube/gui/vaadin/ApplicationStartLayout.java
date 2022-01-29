package de.ollie.cube.gui.vaadin;

import javax.annotation.PostConstruct;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;

import de.ollie.cube.core.model.UserAuthorizationSO;
import de.ollie.cube.core.service.AppConfiguration;
import de.ollie.cube.core.service.UserAuthorizationService;
import de.ollie.cube.core.service.localization.ResourceManager;
import de.ollie.cube.gui.SessionOwner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * The GUI entry point for the Cube application.
 *
 * @author ollie (30.12.2021)
 */
@Route(ApplicationStartLayout.URL)
@PreserveOnRefresh
@VaadinSessionScope
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@RequiredArgsConstructor
@Slf4j
public class ApplicationStartLayout extends VerticalLayout implements SessionOwner {

	public static final String URL = "cube";

	private final AppConfiguration appConfiguration;
	private final ResourceManager resourceManager;
	private final SessionData sessionData;
	private final UserAuthorizationService userAuthorizationService;

	@PostConstruct
	void postConstruct() {
		setMargin(false);
		setWidthFull();
		add(
				new UserLoginView(
						appConfiguration,
						null,
						resourceManager,
						this,
						userAuthorizationService,
						sessionData.getId()) {
					@Override
					public void loggedIn(UserAuthorizationSO userAuthorization) {
						loggedInToApplication(userAuthorization);
					}
				});
	}

	private void loggedInToApplication(UserAuthorizationSO userAuthorization) {
		sessionData.setUserAuthorization(userAuthorization);
		log.info("user '{}' has logged in.", sessionData.getUserAuthorization().getName());
		getUI().ifPresent(ui -> ui.navigate(MainMenuLayout.URL));
	}

	@Override
	public UserAuthorizationSO getUserAuthorization() {
		return sessionData.getUserAuthorization();
	}

	@Override
	public void setUserAuthorization(UserAuthorizationSO userAuthorization) {
		sessionData.setUserAuthorization(userAuthorization);
	}

}