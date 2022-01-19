package de.ollie.cube.gui.vaadin.component;

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
		layout.getStyle().set("border", "2px solid gray");
		layout
				.getStyle()
				.set(
						"box-shadow",
						"10px 10px 20px #e4e4e4, -10px 10px 20px #e4e4e4, -10px -10px 20px #e4e4e4, 10px -10px 20px #e4e4e4");
	}

	private HorizontalLayout getInnerLayout(Button buttonBack, Button buttonLogout, Button buttonUserData,
			String labelText) {
		HorizontalLayout headerInner = new HorizontalLayout();
		headerInner.setWidthFull();
		headerInner.setMargin(false);
		Label label = new Label(labelText);
		label.setWidthFull();
		label.getStyle().set("display", "flex");
		label.getStyle().set("align-items", "center");
		label.getStyle().set("font-weight", "bold");
		buttonLogout.setWidth("15%");
		if (buttonBack == null) {
			headerInner.add((buttonUserData != null ? buttonUserData : new Label("")), label, buttonLogout);
		} else {
			buttonBack.setWidth("15%");
			headerInner.add((buttonUserData != null ? buttonUserData : new Label("")), label, buttonLogout, buttonBack);
		}
		return headerInner;
	}
}
