package de.ollie.cube.gui.vaadin;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

import de.ollie.cube.core.model.Application;
import de.ollie.cube.core.model.User;
import de.ollie.cube.core.model.localization.LocalizationSO;
import de.ollie.cube.core.service.ApplicationService;
import de.ollie.cube.core.service.JWTService;
import de.ollie.cube.core.service.UserService;
import de.ollie.cube.core.service.impl.PasswordEncoder;
import de.ollie.cube.core.service.localization.ResourceManager;
import de.ollie.cube.gui.CubeConfiguration;
import de.ollie.cube.gui.vaadin.OwnUserDataDialog.OwnUserDataDialogObserver;
import de.ollie.cube.gui.vaadin.component.Button;
import de.ollie.cube.gui.vaadin.component.ButtonFactory;
import de.ollie.cube.gui.vaadin.component.ButtonGrid;
import de.ollie.cube.gui.vaadin.component.HeaderLayout;
import de.ollie.cube.gui.vaadin.component.HeaderLayout.HeaderLayoutMode;
import lombok.RequiredArgsConstructor;

@Route(MainMenuLayout.URL)
@RequiredArgsConstructor
public class MainMenuLayout extends VerticalLayout
		implements BeforeEnterObserver, HasUrlParameter<String>, OwnUserDataDialogObserver {

	public static final String URL = "cube/menu";

	private static final Logger LOGGER = LogManager.getLogger(MainMenuLayout.class);

	private final ApplicationService applicationService;
	private final CubeConfiguration cubeConfiguration;
	private final JWTService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final ResourceManager resourceManager;
	private final SessionData sessionData;
	private final UserService userService;

	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
		LOGGER.debug("setParameter");
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		UserAuthorizationChecker.forwardToLoginOnNoUserSetForSession(sessionData, beforeEnterEvent);
		LOGGER.info("created");
		List<Button> buttons =
				applicationService
						.findAllApplicationsByUserId(sessionData.getUserAuthorization().getUserId())
						.stream()
						.map(application -> {
							Button button = ButtonFactory.createButton(application.getName());
							button.addClickListener(event -> switchToApplication(application));
							button.setWidthFull();
							return button;
						})
						.collect(Collectors.toList());
		Button buttonUserOwnData =
				ButtonFactory
						.createButton(
								resourceManager.getLocalizedString("main-menu.button.ownData.text", LocalizationSO.DE));
		buttonUserOwnData.addClickListener(event -> updateUserOwnData());
		buttonUserOwnData.setWidth("20%");
		VerticalLayout buttonLayout = new VerticalLayout();
		buttonLayout.setMargin(false);
		buttonLayout.setWidthFull();
		ButtonGrid buttonGrid = new ButtonGrid(buttons.toArray(new Button[buttons.size()]));
		buttonGrid.setMargin(false);
		buttonGrid.setWidthFull();
		// buttonLayout.add(buttonGrid);
		setWidthFull();
		setMargin(false);
		getStyle().set("background-image", "url(Cube-Background.png)");
		getStyle().set("background-repeat", "no-repeat");
		getStyle().set("background-position", "center center");
		add(
				new HeaderLayout(
						ButtonFactory.createLogoutButton(resourceManager, this::getUI, sessionData, LOGGER),
						buttonUserOwnData,
						resourceManager.getLocalizedString("commons.header.main-menu.label", LocalizationSO.DE),
						HeaderLayoutMode.PLAIN),
				buttonGrid);
		LOGGER.info("main menu view opened for user '{}'.", sessionData.getUserName());
	}

	private void switchToApplication(Application application) {
		String url =
				application.getBaseUrl() + "?jwt="
						+ this.jwtService
								.createJWT(
										this.sessionData.getUserAuthorization().getName(),
										this.sessionData.getUserAuthorization().getToken(),
										this.sessionData.getUserAuthorization().getGlobalId(),
										application.getGlobalId(),
										LocalDateTime.now().plusMinutes(1L),
										Arrays.asList("right1", "right2"));
		LOGGER.info("calling: " + url);
		this.getUI().ifPresent((ui) -> {
			ui.getPage().open(url, application.getName());
		});
	}

	private void updateUserOwnData() {
		userService
				.findById(sessionData.getUserAuthorization().getUserId())
				.ifPresent(
						user -> new OwnUserDataDialog(
								user,
								this,
								resourceManager,
								sessionData,
								passwordEncoder,
								userService).open());
	}

	@Override
	public void userChanged(User user) {
		LOGGER.info("user password changed for user: " + user.getName() + " (" + user.getId() + ")");
	}

}
