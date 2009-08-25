/**
 * 
 */
package books.client.view;

import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

public final class BookPanelImpl extends HorizontalPanel implements BookPanel {
	
	private Image upButton;
	private Image removeButton;
	private Label label;

	public BookPanelImpl(String title) {
		this.setStylePrimaryName("bookPanel");
		label = new Label(title);
		label.setStylePrimaryName("bookTitle");

		this.add(label);

		upButton = new Image("icons/arrow_up.png");
		upButton.setStylePrimaryName("upButton");
		this.add(upButton);
		this.setCellWidth(upButton, "35px");
		
		removeButton = new Image("icons/cross.png");
		removeButton.setStylePrimaryName("removeButton");
		this.add(removeButton);		
		this.setCellWidth(removeButton, "35px");

	}

	public HasClickHandlers getUpButton() {
		return upButton;
	}

	public HasClickHandlers getRemoveButton() {
		return removeButton;
	}

	public HasText getLabel() {
		return label;
	}
	

}