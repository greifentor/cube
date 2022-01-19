package de.ollie.cube.gui.vaadin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

import de.ollie.cube.core.model.localization.LocalizationSO;
import de.ollie.cube.core.service.JWTService;
import de.ollie.cube.core.service.localization.ResourceManager;
import de.ollie.cube.gui.CubeConfiguration;
import de.ollie.cube.gui.vaadin.component.Button;
import de.ollie.cube.gui.vaadin.component.ButtonFactory;
import de.ollie.cube.gui.vaadin.component.ButtonGrid;
import de.ollie.cube.gui.vaadin.component.HeaderLayout;
import de.ollie.cube.gui.vaadin.component.HeaderLayout.HeaderLayoutMode;
import lombok.RequiredArgsConstructor;

@Route(MainMenuLayout.URL)
@RequiredArgsConstructor
public class MainMenuLayout extends VerticalLayout implements BeforeEnterObserver, HasUrlParameter<String> {

	public static final String URL = "cube/menu";

	private static final Logger LOGGER = LogManager.getLogger(MainMenuLayout.class);

	private final CubeConfiguration cubeConfiguration;
	private final JWTService jwtService;
	private final ResourceManager resourceManager;
	private final SessionData sessionData;

	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
		LOGGER.debug("setParameter");
	}

	@Override
	public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
		UserAuthorizationChecker.forwardToLoginOnNoUserSetForSession(sessionData, beforeEnterEvent);
		LOGGER.info("created");
		Button buttonShoppingList =
				ButtonFactory
						.createButton(
								resourceManager
										.getLocalizedString("main-menu.button.test-app.text", LocalizationSO.DE));
		buttonShoppingList.addClickListener(event -> switchToShoppingList());
		buttonShoppingList.setWidthFull();
		Button buttonUserOwnData =
				ButtonFactory
						.createButton(
								resourceManager.getLocalizedString("main-menu.button.ownData.text", LocalizationSO.DE));
		buttonUserOwnData.addClickListener(event -> updateUserOwnData());
		buttonUserOwnData.setWidthFull();
		ButtonGrid buttonGrid = new ButtonGrid(5, buttonShoppingList);
		buttonGrid.setMargin(false);
		buttonGrid.setWidthFull();
		setWidthFull();
		setMargin(false);
		add(
				new HeaderLayout(
						ButtonFactory.createLogoutButton(resourceManager, this::getUI, sessionData, LOGGER),
						buttonUserOwnData,
						resourceManager.getLocalizedString("commons.header.main-menu.label", LocalizationSO.DE),
						HeaderLayoutMode.WRAPPED),
				buttonGrid);
		LOGGER.info("main menu view opened for user '{}'.", sessionData.getUserName());
	}

	private void switchToShoppingList() {
		getUI().ifPresent(ui -> ui.getPage().open(cubeConfiguration.getUrlShoppinglist() + "/shopping-list?jwt=" + jwtService.createJWT(, URL, URL, "shopping-list", null, List.of("SHOPPING-LIST-USER"))));
	}

	private void updateUserOwnData() {

	}

}
