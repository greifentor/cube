package de.ollie.cube.gui.vaadin;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyDownEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

import de.ollie.cube.core.model.UserAuthorizationSO;
import de.ollie.cube.core.service.AppConfiguration;
import de.ollie.cube.core.service.TANService;
import de.ollie.cube.core.service.UserAuthorizationService;
import de.ollie.cube.core.service.localization.ResourceManager;
import de.ollie.cube.gui.SessionOwner;
import de.ollie.cube.gui.event.Event;
import de.ollie.cube.gui.event.EventManager;
import de.ollie.cube.gui.event.EventType;
import de.ollie.cube.gui.vaadin.component.ButtonFactory;
import de.ollie.cube.gui.vaadin.component.Label;
import de.ollie.cube.gui.vaadin.component.PopupDialog;
import de.ollie.cube.gui.vaadin.go.SessionIdGO;

/**
 * A user login view.
 *
 * @author ollie (21.12.2020) (overtook from carp-core)
 */
public class UserLoginView extends VerticalLayout {

	private Button buttonLogin;
	private Label labelVersion;
	private PasswordField passwordFieldPassword;
	private String tanCode;
	private TextField textFieldUserName;
	private TextField textFieldTAN;
	private SessionIdGO sessionId;

	private final transient AppConfiguration appConfiguration;
	private final transient EventManager eventManager;
	private final transient ResourceManager resourceManager;
	private final transient UserAuthorizationService userAuthorizationService;
	private final transient SessionOwner sessionOwner;
	private final transient TANService tanService;

	public UserLoginView(AppConfiguration appConfiguration, EventManager eventManager, ResourceManager resourceManager,
			SessionOwner sessionOwner, UserAuthorizationService userAuthorizationService, SessionIdGO sessionId,
			TANService tanService) {
		super();
		this.appConfiguration = appConfiguration;
		this.eventManager = eventManager;
		this.resourceManager = resourceManager;
		this.sessionId = sessionId;
		this.sessionOwner = sessionOwner;
		this.userAuthorizationService = userAuthorizationService;
		this.tanService = tanService;
		buttonLogin =
				ButtonFactory.createButton(resourceManager.getLocalizedString("UserLoginView.buttons.login.label"));
		buttonLogin.addClickListener(event -> tryLogin());
		buttonLogin.setWidthFull();
		passwordFieldPassword = new PasswordField(resourceManager.getLocalizedString("UserLoginView.password.label"));
		passwordFieldPassword.setWidthFull();
		passwordFieldPassword.addKeyDownListener(e -> onComponentEvent(e));
		textFieldTAN = new TextField(resourceManager.getLocalizedString("UserLoginView.tan.label"));
		textFieldTAN.setWidthFull();
		textFieldTAN.addKeyDownListener(e -> onComponentEvent(e));
		textFieldUserName = new TextField(resourceManager.getLocalizedString("UserLoginView.userName.label"));
		textFieldUserName.setWidthFull();
		textFieldUserName.setAutofocus(true);
		textFieldUserName.addKeyDownListener(e -> onComponentEvent(e));
		setWidthFull();
		setMargin(false);
		getStyle().set("background-image", "url(Cube-Background.png)");
		getStyle().set("background-repeat", "no-repeat");
		getStyle().set("background-position", "center center");
		getStyle().set("-moz-border-radius", "4px");
		getStyle().set("-webkit-border-radius", "4px");
		getStyle().set("border-radius", "4px");
		getStyle().set("border", "1px solid gray");
		getStyle()
				.set(
						"box-shadow",
						"10px 10px 20px #e4e4e4, -10px 10px 20px #e4e4e4, -10px -10px 20px #e4e4e4, 10px -10px 20px #e4e4e4");
		labelVersion = new Label("");
		setNewTanCode();
		add(textFieldUserName, passwordFieldPassword, textFieldTAN, buttonLogin, labelVersion);
	}

	private void tryLogin() {
		userAuthorizationService
				.authenticate(textFieldUserName.getValue(), passwordFieldPassword.getValue())
				.ifPresentOrElse(userAuthorization -> {
					sessionOwner.setUserAuthorization(userAuthorization);
					if (eventManager != null) {
						eventManager
								.fireEvent(
										new Event(userAuthorization.getUserId(), EventType.LOGGED_IN)
												.setParameter("SessionId", sessionId));
					}
					loggedIn(userAuthorization);
				}, () -> {
					PopupDialog
							.showError(resourceManager.getLocalizedString("UserLoginView.Errors.InvalidLogin.label"));
					passwordFieldPassword.setValue("");
					textFieldTAN.setValue("");
					textFieldUserName.focus();
				});
	}

	public void onComponentEvent(KeyDownEvent event) {
		if (event.getKey().getKeys().equals(Key.ENTER.getKeys())) {
			if (event.getSource() == passwordFieldPassword) {
				textFieldTAN.focus();
			} else if (event.getSource() == textFieldTAN) {
				if (tanService.checkTAN(textFieldTAN.getValue(), tanCode, textFieldUserName.getValue())) {
					tryLogin();
				} else {
					PopupDialog
							.showError(resourceManager.getLocalizedString("UserLoginView.Errors.InvalidLogin.label"));
					passwordFieldPassword.setValue("");
					textFieldTAN.setValue("");
					textFieldUserName.focus();
					setNewTanCode();
				}
			} else if (event.getSource() == textFieldUserName) {
				passwordFieldPassword.focus();
			}
		}
	}

	private void setNewTanCode() {
		tanCode = tanService.createTANCode();
		labelVersion.setText(appConfiguration.getName() + " (" + appConfiguration.getVersion() + " - " + tanCode + ")");
	}

	public void loggedIn(UserAuthorizationSO userAuthorization) {
	}

}