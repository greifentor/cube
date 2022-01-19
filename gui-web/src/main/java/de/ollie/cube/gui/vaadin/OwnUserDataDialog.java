package de.ollie.cube.gui.vaadin;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;

import de.ollie.cube.core.model.User;
import de.ollie.cube.core.model.localization.LocalizationSO;
import de.ollie.cube.core.service.UserService;
import de.ollie.cube.core.service.impl.PasswordEncoder;
import de.ollie.cube.core.service.localization.ResourceManager;
import de.ollie.cube.gui.vaadin.component.ButtonFactory;
import de.ollie.cube.gui.vaadin.component.PopupDialog;

public class OwnUserDataDialog extends Dialog {

	private static final Logger LOGGER = LogManager.getLogger(OwnUserDataDialog.class);

	public interface OwnUserDataDialogObserver {
		void userChanged(User user);
	}

	private PasswordEncoder passwordEncoder;
	private PasswordField passwordFieldNew;
	private PasswordField passwordFieldNewRepeat;
	private PasswordField passwordFieldOld;
	private ResourceManager resourceManager;
	private UserService userService;

	public OwnUserDataDialog(User user, OwnUserDataDialogObserver observer, ResourceManager resourceManager,
			SessionData sessionData, PasswordEncoder passwordEncoder, UserService userService) {
		this.passwordEncoder = passwordEncoder;
		this.resourceManager = resourceManager;
		this.userService = userService;
		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(false);
		passwordFieldNew =
				new PasswordField(
						resourceManager
								.getLocalizedString(
										"OwnUserDataDialog.passwordFieldNew.label",
										sessionData.getLocalization()));
		passwordFieldNew.setWidthFull();
		passwordFieldNewRepeat =
				new PasswordField(
						resourceManager
								.getLocalizedString(
										"OwnUserDataDialog.passwordFieldNewRepeat.label",
										sessionData.getLocalization()));
		passwordFieldNewRepeat.setWidthFull();
		passwordFieldOld =
				new PasswordField(
						resourceManager
								.getLocalizedString(
										"OwnUserDataDialog.passwordFieldOld.label",
										sessionData.getLocalization()));
		passwordFieldOld.setWidthFull();
		Button buttonCancel = ButtonFactory.createCancelButton(resourceManager, event -> cancelDialog(), sessionData);
		buttonCancel.setWidthFull();
		Button buttonOk =
				ButtonFactory.createOkButton(resourceManager, event -> submitUser(user, observer), sessionData);
		buttonOk.setWidthFull();
		layout.add(passwordFieldOld, passwordFieldNew, passwordFieldNewRepeat, buttonCancel, buttonOk);
		add(layout);
	}

	private void cancelDialog() {
		close();
	}

	private void submitUser(User user, OwnUserDataDialogObserver observer) {
		try {
			if (isOldPasswordCorrect(user.getPassword()) && isNewPasswordEqualsItsRepeat()) {
				user.setPassword(passwordEncoder.encode(passwordFieldNew.getValue()));
				userService.update(user);
				close();
				observer.userChanged(user);
			} else if (!isOldPasswordCorrect(user.getPassword())) {
				System.out
						.println(
								">" + passwordFieldOld.getValue() + "-" + user.getPassword() + "<>"
										+ passwordEncoder.encode(passwordFieldOld.getValue()));
				PopupDialog
						.showError(
								resourceManager
										.getLocalizedString(
												"OwnUserDataDialog.Errors.PasswordNotAccepted.label",
												LocalizationSO.DE));
			} else if (!isNewPasswordEqualsItsRepeat()) {
				PopupDialog
						.showError(
								resourceManager
										.getLocalizedString(
												"OwnUserDataDialog.Errors.NewPasswordDiffersWithRepeat.label",
												LocalizationSO.DE));
			}
		} catch (Exception e) {
			LOGGER.error("error while submitting user: " + e.getMessage());
		}
	}

	private boolean isOldPasswordCorrect(String password) throws Exception {
		return password.isEmpty() || password.equals(passwordEncoder.encode(passwordFieldOld.getValue()));
	}

	private boolean isNewPasswordEqualsItsRepeat() {
		return passwordFieldNew.getValue().equals(passwordFieldNewRepeat.getValue());
	}

}