package de.ollie.cube.gui.vaadin.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

/**
 * A grid of buttons.
 *
 * @author ollie (15.08.2021) (overtook from carp-core)
 */
public class ButtonGrid extends VerticalLayout {

	public ButtonGrid(int buttonsPerRow, Button... buttons) {
		getStyle().set("-moz-border-radius", "4px");
		getStyle().set("-webkit-border-radius", "4px");
		getStyle().set("border-radius", "4px");
		getStyle().set("border", "1px solid gray");
		getStyle()
				.set(
						"box-shadow",
						"10px 10px 20px #e4e4e4, -10px 10px 20px #e4e4e4, -10px -10px 20px #e4e4e4, 10px -10px 20px #e4e4e4");
		List<Button> buttonList = new ArrayList<>(Arrays.asList(buttons));
		while (!buttonList.isEmpty()) {
			add(createRow(buttonsPerRow, buttonList));
		}
	}

	private HorizontalLayout createRow(int buttonsPerRow, List<Button> buttonList) {
		HorizontalLayout row = new HorizontalLayout();
		row.setMargin(false);
		row.setWidthFull();
		while (buttonsPerRow > 0 && !buttonList.isEmpty()) {
			Button button = buttonList.get(0);
			if (button == null) {
				Label label = new Label("");
				label.setWidthFull();
				row.add(label);
			} else {
				row.add(button);
			}
			buttonList.remove(button);
			buttonsPerRow--;
		}
		while (buttonsPerRow > 0) {
			Label label = new Label("");
			label.setWidthFull();
			row.add(label);
			buttonsPerRow--;
		}
		return row;
	}

}