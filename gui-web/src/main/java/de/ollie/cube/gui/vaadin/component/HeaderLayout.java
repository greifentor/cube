package de.ollie.cube.gui.vaadin.component;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

/**
 * A layout for the page headers.
 *
 * @author ollie (20.09.2021)
 */
public class HeaderLayout extends HorizontalLayout {

	public enum HeaderLayoutMode {
		PLAIN,
		WRAPPED;
	}

	public HeaderLayout(Button buttonLogout, Button buttonUserData, String labelText, HeaderLayoutMode mode) {
		this(null, buttonLogout, buttonUserData, labelText, mode);
	}

	public HeaderLayout(Button buttonBack, Button buttonLogout, Button buttonUserData, String labelText,
			HeaderLayoutMode mode) {
		if (mode == HeaderLayoutMode.WRAPPED) {
			HorizontalLayout wrapper = new HorizontalLayout();
			prepareLayout(wrapper);
			wrapper.add(getInnerLayout(buttonBack, buttonLogout, buttonUserData, labelText));
			setWidthFull();
			setPadding(true);
			add(wrapper);
		} else {
			prepareLayout(this);
			add(getInnerLayout(buttonBack, buttonLogout, buttonUserData, labelText));
		}
	}

	private void prepareLayout(HorizontalLayout layout) {
		layout.setWidthFull();
		layout.setPadding(true);
		layout.getStyle().set("-moz-border-radius", "4px");
		layout.getStyle().set("-webkit-border-radius", "4px");
		layout.getStyle().set("border-radius", "4px");
		layout.getStyle().set("border", "1px solid gray");
		layout
				.getStyle()
				.set(
						"box-shadow",
						"10px 10px 20px #e4e4e4, -10px 10px 20px #e4e4e4, -10px -10px 20px #e4e4e4, 10px -10px 20px #e4e4e4");
	}

	private HorizontalLayout getInnerLayout(Button buttonBack, Button buttonLogout, Button buttonUserData,
			String labelText) {
		FormLayout headerInner = new FormLayout();
		headerInner.setWidthFull();
		headerInner
				.setResponsiveSteps(
						new ResponsiveStep("0px", 1),
						new ResponsiveStep("960px", 2),
						new ResponsiveStep("1440px", 3),
						new ResponsiveStep("1920px", 4));
		Label label = new Label(labelText);
		label.setWidthFull();
		label.getStyle().set("display", "flex");
		label.getStyle().set("align-items", "center");
		label.getStyle().set("font-weight", "bold");
		if (buttonBack == null) {
			headerInner.add(label, (buttonUserData != null ? buttonUserData : new Label("")), buttonLogout);
		} else {
			headerInner.add(label, (buttonUserData != null ? buttonUserData : new Label("")), buttonLogout, buttonBack);
		}
		headerInner.setColspan(label, 2);
		HorizontalLayout layout = new HorizontalLayout(headerInner);
		layout.setWidthFull();
		layout.setMargin(false);
		return layout;
	}
}
