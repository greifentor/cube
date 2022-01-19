package de.ollie.cube.gui.vaadin.component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import lombok.extern.slf4j.Slf4j;

/**
 * A utilitiy class for popup dialogs.
 *
 * @author ollie (21.12.2020)
 */
@Slf4j
public class PopupDialog {

	public static void showError(String message) {
		log.debug("showError: {}", message);
//		Notification notification = new Notification(message, 5000, Position.MIDDLE);
//		notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
//		notification.open();
		Button buttonConfirm = new Button("Ok");
		Label labelMessage = new Label(message);
		VerticalLayout layout = new VerticalLayout();
		layout.add(labelMessage, buttonConfirm);
		layout.getStyle() //
				.set("color", "red") //
		;
		layout.setHorizontalComponentAlignment(Alignment.END, buttonConfirm);
		layout.setSizeFull();
		Dialog dialog = new Dialog(layout);
		buttonConfirm.addClickListener(event -> dialog.close());
		dialog.open();
	}

}