package de.ollie.cube.gui.vaadin.component;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * A grid of buttons.
 *
 * @author ollie (15.08.2021) (overtook from carp-core)
 */
public class ButtonGrid extends VerticalLayout {

	public ButtonGrid(Button... buttons) {
		getStyle().set("-moz-border-radius", "4px");
		getStyle().set("-webkit-border-radius", "4px");
		getStyle().set("border-radius", "4px");
		getStyle().set("border", "1px solid gray");
		getStyle()
				.set(
						"box-shadow",
						"10px 10px 20px #e4e4e4, -10px 10px 20px #e4e4e4, -10px -10px 20px #e4e4e4, 10px -10px 20px #e4e4e4");
		FormLayout layout = new FormLayout(buttons);
		layout
				.setResponsiveSteps(
						new ResponsiveStep("0px", 1),
						new ResponsiveStep("960px", 2),
						new ResponsiveStep("1440px", 3),
						new ResponsiveStep("1920px", 4));
		add(layout);
	}

}