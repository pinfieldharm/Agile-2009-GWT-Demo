/**
 * 
 */
package mq.client.view;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public final class BookPanel extends FlowPanel {
	
	private Image topButton;
	private Image removeButton;
	private Label label;

	public BookPanel(String title) {
		this.setStylePrimaryName("bookPanel");
		label = new Label(title);
		this.add(label);
		topButton = new Image("icons/arrow_up.png");
		this.add(topButton);
		removeButton = new Image("icons/cross.png");
		this.add(removeButton);
	}

	public Image getTopButton() {
		return topButton;
	}

	public Image getRemoveButton() {
		return removeButton;
	}

	public Label getLabel() {
		return label;
	}
	
	

}