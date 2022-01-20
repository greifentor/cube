package de.ollie.cube.gui.vaadin;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyDownEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

import de.ollie.cube.core.model.UserAuthorizationSO;
import de.ollie.cube.core.service.AppConfiguration;
import de.ollie.cube.core.service.UserAuthorizationService;
import de.ollie.cube.core.service.localization.ResourceManager;
import de.ollie.cube.gui.SessionOwner;
import de.ollie.cube.gui.event.Event;
import de.ollie.cube.gui.event.EventManager;
import de.ollie.cube.gui.event.EventType;
import de.ollie.cube.gui.vaadin.component.PopupDialog;
import de.ollie.cube.gui.vaadin.go.SessionIdGO;

/**
 * A user login view.
 *
 * @author ollie (21.12.2020) (overtook from carp-core)
 */
public class UserLoginView extends VerticalLayout implements ComponentEventListener<KeyDownEvent> {

	private Button buttonLogin;
	private PasswordField passwordFieldPassword;
	private TextField textFieldUserName;
	private SessionIdGO sessionId;

	private final transient AppConfiguration appConfiguration;
	private final transient EventManager eventManager;
	private final transient ResourceManager resourceManager;
	private final transient UserAuthorizationService userAuthorizationService;
	private final transient SessionOwner sessionOwner;

	public UserLoginView(
			AppConfiguration appConfiguration,
			EventManager eventManager,
			ResourceManager resourceManager,
			SessionOwner sessionOwner,
			UserAuthorizationService userAuthorizationService,
			SessionIdGO sessionId) {
		super();
		this.appConfiguration = appConfiguration;
		this.eventManager = eventManager;
		this.resourceManager = resourceManager;
		this.sessionId = sessionId;
		this.sessionOwner = sessionOwner;
		this.userAuthorizationService = userAuthorizationService;
		buttonLogin = new Button(resourceManager.getLocalizedString("UserLoginView.buttons.login.label"));
		buttonLogin.addClickListener(event -> tryLogin());
		buttonLogin.setWidthFull();
		passwordFieldPassword = new PasswordField(resourceManager.getLocalizedString("UserLoginView.password.label"));
		passwordFieldPassword.setWidthFull();
		passwordFieldPassword.addKeyDownListener(this);
		textFieldUserName = new TextField(resourceManager.getLocalizedString("UserLoginView.userName.label"));
		textFieldUserName.setWidthFull();
		textFieldUserName.setAutofocus(true);
		textFieldUserName.addKeyDownListener(this);
		setWidthFull();
		setMargin(false);
		Label labelVersion = new Label(appConfiguration.getName() + " (" + appConfiguration.getVersion() + ")");
		labelVersion.setHeight("300px");
		add(textFieldUserName, passwordFieldPassword, buttonLogin, labelVersion);
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
					textFieldUserName.focus();
				});
	}

	@Override
	public void onComponentEvent(KeyDownEvent event) {
		if (event.getKey().getKeys().equals(Key.ENTER.getKeys())) {
			if (event.getSource() == passwordFieldPassword) {
				tryLogin();
			} else if (event.getSource() == textFieldUserName) {
				passwordFieldPassword.focus();
			}
		}
	}

	public void loggedIn(UserAuthorizationSO userAuthorization) {
	}

}